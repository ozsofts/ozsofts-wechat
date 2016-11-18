package net.ozsofts.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.comm.WechatCommUtil;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;
import net.ozsofts.wechat.utils.AesException;
import net.ozsofts.wechat.utils.SHA1;
import net.ozsofts.wechat.utils.WXBizMsgCrypt;

public class WechatConfigServiceImpl implements WechatConfigService {
	private static Logger log = LoggerFactory.getLogger(WechatConfigServiceImpl.class);

	public final static String ACCESS_TOKEN = "access_token";
	public final static String EXPIRES_IN = "expires_in";

	/** 系统分配给微信接口的标识 */
	private String sysId;

	private String appId;
	private String secret;

	private String encryptType;
	private String encodingAesKey;
	private String token;

	private String accessToken;
	private long updatedTime;

	@Autowired
	private WechatConfigManager wechatConfigManager;

	private CountDownLatch latch = new CountDownLatch(1);

	public WechatConfigServiceImpl() {
	}

	public WechatConfigServiceImpl(String sysId, String appId, String secret, String encryptType, String encodingAesKey, String token) {
		this.sysId = sysId;
		this.appId = appId;
		this.secret = secret;
		this.encryptType = encryptType;
		this.encodingAesKey = encodingAesKey;
		this.token = token;
	}

	public void initialize() {
		log.debug("sysId:{}  appId:{}", sysId, appId);

		// 注册管理服务
		wechatConfigManager.register(sysId, this);

		// 启用处理线程
		new RefreshThread().start();
	}

	@Override
	public String getAccessToken() {
		try {
			latch.await();
		} catch (InterruptedException e) {
			return "";
		}

		return accessToken;
	}

	/**
	 * <p>
	 * 在设置微信参数时需要通过这样的处理进行验证
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param echoStr
	 * @param verifyEncryptType
	 * @param signature
	 * @return
	 */
	@Override
	public String verifyUrl(String timestamp, String nonce, String echoStr, String verifyEncryptType, String signature) {
		String returnStr = "";
		try {
			if (StringUtils.isNotBlank(verifyEncryptType) && Constants.ENCRYPT_TYPE_AES.equalsIgnoreCase(verifyEncryptType)) {
				WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
				returnStr = msgCrypt.verifyUrl(signature, timestamp, nonce, echoStr);
			} else {
				if (verifySignature(signature, timestamp, nonce)) {
					returnStr = echoStr;
				}
			}
		} catch (AesException e) {
			log.error("在验证消息的签名时出现错误!", e);
		}
		return returnStr;
	}

	@Override
	public String handleRequestMessage(String timestamp, String nonce, String requestEncryptType, String signature, String postData)
			throws Exception {
		String returnStr = "";
		try {
			if (requestEncryptType != null && !encryptType.equalsIgnoreCase(requestEncryptType)) {
				throw new Exception("配置的加密方式[" + encryptType + "]与上送[" + requestEncryptType + "]不匹配，请检查配置!");
			}

			if (Constants.ENCRYPT_TYPE_AES.equalsIgnoreCase(requestEncryptType)) {
				WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
				returnStr = msgCrypt.decryptMsg(signature, timestamp, nonce, postData);
			} else {
				if (verifySignature(signature, timestamp, nonce)) {
					returnStr = postData;
				}
			}
		} catch (AesException e) {
			log.error("在验证消息的签名时出现错误!", e);
		}

		return returnStr;
	}

	@Override
	public String handleResponseMessage(String timestamp, String nonce, String responseData) throws Exception {
		if (Constants.ENCRYPT_TYPE_RAW.equalsIgnoreCase(encryptType))
			return responseData;

		return encryptMsg(responseData, timestamp, nonce);
	}

	private boolean verifySignature(String signature, String timestamp, String nonce) throws AesException {
		return signature.equals(getSignature(timestamp, nonce));
	}

	public String getSignature(String timestamp, String nonce) throws AesException {
		return SHA1.getSHA1(token, timestamp, nonce, "");
	}

	public String encryptMsg(String data, String timestamp, String nonce) throws Exception {
		WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(token, encodingAesKey, appId);
		return msgCrypt.encryptMsg(data, timestamp, nonce);
	}

	public void setWechatConfigManager(WechatConfigManager wechatConfigManager) {
		this.wechatConfigManager = wechatConfigManager;
	}

	/**
	 * <p>
	 * 调用微信的服务刷新access token
	 */
	private void refreshToken() {
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put(Constants.PARA_GRANT_TYPE, "client_credential");
			params.put(Constants.PARA_APP_ID, appId);
			params.put(Constants.PARA_SECRET, secret);

			JSONObject json = WechatCommUtil.get("/cgi-bin/token", params);

			accessToken = json.getString(Constants.PARA_ACCESS_TOKEN);
			int expired = json.getIntValue(Constants.PARA_EXPIRES_IN);
			updatedTime = System.currentTimeMillis() + expired * 1000; // 转化为毫秒

			StringBuilder sb = new StringBuilder();
			sb.append("[").append(appId).append("]");
			sb.append("当前最新的 [access-token:").append(accessToken).append("]");
			sb.append("  将在[").append(expired).append("ms]后进行更新!");
			log.info(sb.toString());
		} catch (Exception ex) {
			log.error("[refresh token] get weixin token error!", ex);
		}
	}

	/**
	 * <p>
	 * 对access token进行刷新，每隔一分钟启动一次，在token失效前5分钟开始做token的刷新
	 */
	class RefreshThread extends Thread {
		private boolean initialized = false;

		public RefreshThread() {
			setName("[TokenService:Thread]");
		}

		public void run() {
			while (true) {
				// 刷新AccessToken
				refreshToken();

				try {
					if (!initialized) {
						initialized = true;
						latch.countDown();
					}
					TimeUnit.MINUTES.sleep(5l);
				} catch (Exception ex) {
				}
			}
		}
	}
}

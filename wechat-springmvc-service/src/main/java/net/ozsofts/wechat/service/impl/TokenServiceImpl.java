package net.ozsofts.wechat.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.manager.WxAccountManager;
import net.ozsofts.wechat.functions.TokenFunction;
import net.ozsofts.wechat.service.TokenData;
import net.ozsofts.wechat.service.TokenService;

public class TokenServiceImpl implements TokenService {
	private static Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

	public final static String ACCESS_TOKEN = "access_token";
	public final static String EXPIRES_IN = "expires_in";

	private Map<String, TokenData> tokenDataMap = new HashMap<String, TokenData>();

	private CountDownLatch latch = new CountDownLatch(1);

	@Autowired
	private WxAccountManager wxAccountManager;
	@Autowired
	private TokenFunction tokenFunction;

	public void initialize() {
		// 启用处理线程
		new RefreshThread().start();
	}

	public String getAccessToken(String systemId) {
		try {
			System.out.println("await token!");
			latch.await();
			System.out.println("through token!");
		} catch (InterruptedException e) {
			return "";
		}

		TokenData tokenData = tokenDataMap.get(systemId);
		if (tokenData == null) {
			return "";
		}

		return tokenData.getAccessToken();
	}

	/**
	 * <p>
	 * 调用微信的服务刷新access token
	 */
	public void refreshToken(WxAccount WxAccount, TokenData tokenData) {
		try {
			Map<String, Object> tokenMessages = tokenFunction.getToken(WxAccount.getAppId(), WxAccount.getSecret());
			tokenData.setAccessToken((String) tokenMessages.get(ACCESS_TOKEN));
			int expired = (Integer) tokenMessages.get(EXPIRES_IN);
			tokenData.setUpdatedTime(System.currentTimeMillis() + expired * 1000); // 转化为毫秒

			StringBuilder sb = new StringBuilder();
			sb.append("[").append(WxAccount.getSystemId()).append("]");
			sb.append("当前最新的 [access-token:").append(tokenData.getAccessToken()).append("]");
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
				List<WxAccount> WxAccountList = wxAccountManager.getAllWxAccount();
				for (WxAccount wxAccount : WxAccountList) {
					if (!wxAccount.getIsEnabled()) {
						tokenDataMap.remove(wxAccount.getSystemId());
						continue;
					}

					TokenData tokenData = tokenDataMap.get(wxAccount.getSystemId());
					if (tokenData == null) {
						tokenData = new TokenData();
						tokenData.setSystemId(wxAccount.getSystemId());
						tokenDataMap.put(wxAccount.getSystemId(), tokenData);
					} else {
						long currTime = System.currentTimeMillis();
						if (tokenData.getUpdatedTime() - currTime > 300000) {
							// 如果原来的token还有五分钟过期才需要刷新一次
							continue;
						}
					}

					// 刷新AccessToken
					refreshToken(wxAccount, tokenData);
				}

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

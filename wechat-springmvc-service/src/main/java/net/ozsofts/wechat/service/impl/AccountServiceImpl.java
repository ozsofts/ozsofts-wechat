package net.ozsofts.wechat.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.entity.WxGroup;
import net.ozsofts.wechat.core.entity.WxUser;
import net.ozsofts.wechat.core.manager.WxAccountManager;
import net.ozsofts.wechat.core.manager.WxGroupManager;
import net.ozsofts.wechat.core.manager.WxUserManager;
import net.ozsofts.wechat.functions.GroupFunction;
import net.ozsofts.wechat.functions.UserFunction;
import net.ozsofts.wechat.service.AccountService;
import net.ozsofts.wechat.service.TokenService;
import net.ozsofts.wechat.utils.AesException;
import net.ozsofts.wechat.utils.SHA1;
import net.ozsofts.wechat.utils.WXBizMsgCrypt;

public class AccountServiceImpl implements AccountService {
	private static Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

	@Autowired
	private WxAccountManager wxAccountManager;
	@Autowired
	private WxGroupManager wxGroupManager;
	@Autowired
	private WxUserManager wxUserManager;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserFunction userFunction;
	@Autowired
	private GroupFunction groupFunction;

	/**
	 * <p>
	 * 对公众号进行初始化，具体的处理是读取公众号所有的群组信息和用户信息
	 * 
	 * @param systemId
	 *            公众号标识
	 * @throws Exception
	 */
	@Override
	public void initAccount(String systemId) throws Exception {
		WxAccount account = wxAccountManager.getAccountBySystemId(systemId);
		if (account == null) {
			// TODO 公众号不存在
		}
		if (account.getIsInit()) {
			// TODO 已经初始化，不需要再进行初始化
		}

		String token = tokenService.getAccessToken(systemId);
		if (StringUtils.isBlank(token)) {
			// TODO 公众号的AccessToken还未初始化，不能进行后续处理
		}

		// 首先取得所有群组信息并保存到数据库中
		List<WxGroup> groupList = groupFunction.getAllGroups(token);
		for (WxGroup wxgroup : groupList) {
			wxgroup.setAccountId(account.getId());
			wxGroupManager.addWxGroup(wxgroup);
		}

		// 先读取所有用户的open_id信息
		List<String> openIdList = userFunction.getOpenIds(token);
		for (String openId : openIdList) {
			WxUser wxuser = userFunction.getUserInfo(token, openId);
			wxuser.setAccountId(account.getId());
			wxUserManager.addWxUser(wxuser);
		}

		// 更新公众号的初始状态
		account.setIsInit(true);
		wxAccountManager.updateWxAccount(account);
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
	public String verifyUrl(WxAccount account, String timestamp, String nonce, String echoStr, String verifyEncryptType, String signature) {
		String returnStr = "";
		try {
			if (StringUtils.isNotBlank(verifyEncryptType) && Constants.ENCRYPT_TYPE_AES.equalsIgnoreCase(verifyEncryptType)) {
				WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(account.getToken(), account.getEncodingAesKey(), account.getAppId());
				returnStr = msgCrypt.verifyUrl(signature, timestamp, nonce, echoStr);
			} else {
				if (verifySignature(account, signature, timestamp, nonce)) {
					returnStr = echoStr;
				}
			}
		} catch (AesException e) {
			log.error("在验证消息的签名时出现错误!", e);
		}
		return returnStr;
	}

	@Override
	public String handleRequestMessage(WxAccount account, String timestamp, String nonce, String requestEncryptType, String signature,
			String postData) throws Exception {
		String returnStr = "";
		try {
			if (requestEncryptType != null && !account.getEncryptType().equalsIgnoreCase(requestEncryptType)) {
				throw new Exception(
						"[" + account.getSystemId() + "] 配置的加密方式[" + account.getEncryptType() + "]与上送[" + requestEncryptType + "]不匹配，请检查配置!");
			}

			if (Constants.ENCRYPT_TYPE_AES.equalsIgnoreCase(requestEncryptType)) {
				WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(account.getToken(), account.getEncodingAesKey(), account.getAppId());
				returnStr = msgCrypt.decryptMsg(signature, timestamp, nonce, postData);
			} else {
				if (verifySignature(account, signature, timestamp, nonce)) {
					returnStr = postData;
				}
			}
		} catch (AesException e) {
			log.error("[" + account.getSystemId() + "]在验证消息的签名时出现错误!", e);
		}

		return returnStr;
	}

	@Override
	public String handleResponseMessage(WxAccount account, String timestamp, String nonce, String responseData) throws Exception {
		if (Constants.ENCRYPT_TYPE_RAW.equalsIgnoreCase(account.getEncryptType()))
			return responseData;

		WXBizMsgCrypt msgCrypt = new WXBizMsgCrypt(account.getToken(), account.getEncodingAesKey(), account.getAppId());
		return msgCrypt.encryptMsg(responseData, timestamp, nonce);
	}

	private boolean verifySignature(WxAccount account, String signature, String timestamp, String nonce) throws AesException {
		return signature.equals(SHA1.getSHA1(account.getToken(), timestamp, nonce, ""));
	}
}

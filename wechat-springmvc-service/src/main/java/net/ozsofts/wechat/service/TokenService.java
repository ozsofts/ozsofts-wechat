package net.ozsofts.wechat.service;

import net.ozsofts.wechat.core.entity.WxAccount;

public interface TokenService {
	public final static String ACCESS_TOKEN = "access_token";
	public final static String EXPIRES_IN = "expires_in";

	/**
	 * <p>
	 * 取得访问令牌
	 * 
	 * @param systemId
	 * @return
	 */
	public String getAccessToken(String systemId);

	/**
	 * <p>
	 * 调用微信的服务刷新access token
	 */
	public void refreshToken(WxAccount WxAccount, TokenData tokenData);
}

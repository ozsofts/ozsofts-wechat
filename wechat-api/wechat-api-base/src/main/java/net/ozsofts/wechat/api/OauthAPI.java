package net.ozsofts.wechat.api;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.enums.OauthScope;
import net.ozsofts.wechat.api.type.response.UserInfo;

/**
 * 网页授权API
 *
 * @author jack
 */
public interface OauthAPI {

	/**
	 * 生成回调url，这个结果要求用户在微信中打开，即可获得token，并指向redirectUrl
	 *
	 * @param redirectUrl
	 *            用户自己设置的回调地址
	 * @param scope
	 *            授权作用域
	 * @param state
	 *            用户自带参数
	 * @return 回调url，用户在微信中打开即可开始授权
	 */
	public String getOauthPageUrl(String redirectUrl, OauthScope scope, String state) throws WechatException;

	/**
	 * 用户同意授权后在回调url中会得到code，调用此方法用code换token以及openid，所以如果仅仅是授权openid，到这步就结束了
	 *
	 * @param code
	 *            授权后得到的code
	 * @return token对象
	 */
	public String getToken(String code) throws WechatException; // TODO 返回的数据需要细化

	/**
	 * 获取用户详细信息
	 *
	 * @param token
	 *            token
	 * @param openid
	 *            用户openid
	 * @return 用户信息对象
	 */
	public UserInfo getUserInfo(String token, String openid) throws WechatException;
}

package net.ozsofts.wechat.api;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.type.response.SignatureInfo;

/**
 * 微信js-sdk相关API
 * 
 * @author jack
 */
public interface JsAPI {
	/**
	 * 获取js-sdk所需的签名，简化逻辑 不太在意随机数和时间戳的场景，使用更加方便
	 * 
	 * @param url
	 *            当前网页的URL，不包含#及其后面部分
	 * @return 签名以及相关参数
	 */
	public SignatureInfo getSignature(String sysId,String url) throws WechatException;

	/**
	 * 获取js-sdk所需的签名，给调用者最大的自由度，控制生成签名的参数
	 * 
	 * @param nonceStr
	 *            随机字符串
	 * @param timestame
	 *            时间戳
	 * @param url
	 *            当前网页的URL，不包含#及其后面部分
	 * @return 签名以及相关参数
	 */
	public SignatureInfo getSignature(String sysId,String nonceStr, long timestame, String url) throws WechatException;
}

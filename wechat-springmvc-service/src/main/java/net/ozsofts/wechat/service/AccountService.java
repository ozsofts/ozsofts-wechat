package net.ozsofts.wechat.service;

import net.ozsofts.wechat.core.entity.WxAccount;

public interface AccountService {

	/**
	 * <p>
	 * 对公众号进行初始化，具体的处理是读取公众号所有的群组信息和用户信息
	 * 
	 * @param systemId
	 *            公众号标识
	 * @throws Exception
	 */
	public void initAccount(String systemId) throws Exception;

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
	public String verifyUrl(WxAccount account, String timestamp, String nonce, String echoStr, String verifyEncryptType, String signature);

	public String handleRequestMessage(WxAccount account, String timestamp, String nonce, String requestEncryptType, String signature,
			String postData) throws Exception;

	public String handleResponseMessage(WxAccount account, String timestamp, String nonce, String responseData) throws Exception;

}

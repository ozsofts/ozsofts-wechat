package net.ozsofts.wechat.service;

public interface WechatConfigService {
	public final static String ACCESS_TOKEN = "access_token";
	public final static String EXPIRES_IN = "expires_in";

	/**
	 * <p>
	 * 验证上行的URL是否合法
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param echoStr
	 * @param verifyEncryptType
	 * @param signature
	 * @return
	 */
	public String verifyUrl(String timestamp, String nonce, String echoStr, String verifyEncryptType, String signature);

	/**
	 * <p>
	 * 处理上行的消息
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param requestEncryptType
	 * @param signature
	 * @param postData
	 * @return
	 * @throws Exception
	 */
	public String handleRequestMessage(String timestamp, String nonce, String requestEncryptType, String signature, String postData) throws Exception;

	/**
	 * <p>
	 * 处理下行的消息
	 * 
	 * @param timestamp
	 * @param nonce
	 * @param responseData
	 * @return
	 * @throws Exception
	 */
	public String handleResponseMessage(String timestamp, String nonce, String responseData) throws Exception;

	/**
	 * <p>
	 * 取得访问令牌
	 * 
	 * @param systemId
	 * @return
	 */
	public String getAccessToken();

	/**
	 * <p>
	 * 取得明文的签名
	 * 
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws Exception
	 */
	public String getSignature(String timestamp, String nonce) throws Exception;

	/**
	 * <p>
	 * 对数据进行加密
	 * 
	 * @param data
	 * @param timestamp
	 * @param nonce
	 * @return
	 * @throws Exception
	 */
	public String encryptMsg(String data, String timestamp, String nonce) throws Exception;

	public void setWechatConfigManager(WechatConfigManager wechatConfigManager);
}

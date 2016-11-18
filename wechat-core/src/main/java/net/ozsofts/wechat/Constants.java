package net.ozsofts.wechat;

public final class Constants {
	public final static String PARA_SYS_ID = "system_id";
	/** 每一个请求的流水号 */
	public final static String PARA_REQUEST_TRACE = "request_trace";

	public final static String PARA_APP_ID = "appid";
	public final static String PARA_SECRET = "secret";
	public final static String PARA_ACCESS_TOKEN = "access_token";
	public final static String PARA_EXPIRES_IN = "expires_in";
	public final static String PARA_ERR_CODE = "errcode";
	public final static String PARA_ERR_MSG = "errmsg";
	public final static String PARA_GRANT_TYPE = "grant_type";

	public final static String PARA_TIMESTAMP = "timestamp";
	public final static String PARA_NONCE = "nonce";
	public final static String PARA_ENCRYPT_TYPE = "encrypt_type";
	public final static String PARA_MSG_SIGNATURE = "msg_signature";
	public final static String PARA_SIGNATURE = "signature";
	public final static String PARA_ECHOSTR = "echostr";

	//
	// 公众号类型
	//
	/** 订阅号标识 */
	public static final String ACCOUNT_TYPE_DY = "DY";
	/** 服务号标识 */
	public static final String ACCOUNT_TYPE_FW = "FW";

	//
	// 消息加解密类型定义
	//
	/** 不加密 */
	public static final String ENCRYPT_TYPE_RAW = "raw";
	/** AES加密 */
	public static final String ENCRYPT_TYPE_AES = "aes";

	//
	// 匹配类型
	//
	/** 正则 */
	public static final String MATCH_TYPE_REGEX = "regex";
	/** 以关键字开头 */
	public static final String MATCH_TYPE_STARTSWITH = "startsWith";
	/** 以关键字结尾 */
	public static final String MATCH_TYPE_ENDSWITH = "endsWith";
	/** 包含关键字 */
	public static final String MATCH_TYPE_CONTAINS = "contains";

}

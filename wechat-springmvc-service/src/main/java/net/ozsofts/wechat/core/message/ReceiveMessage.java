package net.ozsofts.wechat.core.message;

import java.util.Map;

import org.joda.time.DateTime;

/**
 * <p>
 * 接收到的消息
 * 
 * @author jack
 */
public class ReceiveMessage {
	//
	// 消息类型定义
	//
	/** 文本消息 */
	public static final String MSGTYPE_TEXT = "text";
	/** 图片消息 */
	public static final String MSGTYPE_IMAGE = "image";
	/** 语音消息 */
	public static final String MSGTYPE_VOICE = "voice";
	/** 视频消息 */
	public static final String MSGTYPE_VIDEO = "video";
	/** 小视频消息 */
	public static final String MSGTYPE_SHORTVIDEO = "shortvideo";
	/** 地理位置消息 */
	public static final String MSGTYPE_LOCATION = "location";
	/** 链接消息 */
	public static final String MSGTYPE_LINK = "link";
	/** 事件上送消息 */
	public static final String MSGTYPE_EVENT = "event";

	//
	// 上行消息的基本信息
	//
	/** 开发者微信号,即接收消息的公众号 */
	protected String toUserName;
	/** 发送方帐号（一个OpenID） */
	protected String fromUserName;
	/** 消息创建时间 （整型） */
	protected String createTime;
	/** 消息类型 */
	protected String msgType;
	/** 消息ID */
	protected String msgId;

	public void parse(Map<String, String> params) {
		this.toUserName = params.get("ToUserName");
		this.fromUserName = params.get("FromUserName");

		DateTime dt = new DateTime(Long.parseLong(params.get("CreateTime")) * 1000);
		this.createTime = dt.toString("yyyyMMddHHmmss");

		this.msgType = params.get("MsgType");
		this.msgId = params.get("MsgId");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(toUserName).append(" | ");
		sb.append(fromUserName).append(" | ");
		sb.append(createTime).append(" | ");
		sb.append(msgType).append(" | ");
		sb.append(msgId);
		return sb.toString();
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}

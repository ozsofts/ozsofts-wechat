package net.ozsofts.wechat.core.message;

import java.util.Map;
import java.util.TreeMap;

public class ResponseMessage {
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
	/** 音乐消息 */
	public static final String MSGTYPE_MUSIC = "music";
	/** 图文消息 */
	public static final String MSGTYPE_NEWS = "news";

	/** 接收方帐号（收到的OpenID） */
	protected String toUserName;
	/** 开发者微信号 */
	protected String fromUserName;
	/** 消息创建时间 （整型） */
	protected String createTime;
	/** 消息类型 */
	protected String msgType;

	/**
	 * <p>
	 * 组装成XML格式，子类需要对此进行细化
	 * 
	 * @return
	 */
	protected String makeXML() {
		return "";
	}

	protected void makeMap(Map<String, Object> data) {
	}

	public String toXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName><![CDATA[").append(toUserName).append("]]></ToUserName>");
		sb.append("<FromUserName><![CDATA[").append(fromUserName).append("]]></FromUserName>");
		sb.append("<CreateTime>").append(createTime).append("</CreateTime>");
		sb.append("<MsgType><![CDATA[").append(msgType).append("]]></MsgType>");
		sb.append(makeXML());
		sb.append("</xml>");
		return sb.toString();
	}

	public Map<String, Object> toMap() {
		Map<String, Object> data = new TreeMap<String, Object>();
		data.put("touser", this.toUserName);
		data.put("msgtype", this.msgType);

		makeMap(data);

		return data;
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
}

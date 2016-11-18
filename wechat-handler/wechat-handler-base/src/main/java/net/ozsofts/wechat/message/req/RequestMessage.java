package net.ozsofts.wechat.message.req;

import java.util.Map;

import org.joda.time.DateTime;

/**
 * <p>
 * 接收到的消息
 * 
 * @author jack
 */
public class RequestMessage {
	//
	// 上行消息的基本信息
	//
	/** 当前微信公众号对应的system_id */
	protected String sysId;
	/** 开发者微信号,即接收消息的公众号 */
	protected String toUserName;
	/** 发送方帐号（一个OpenID） */
	protected String fromUserName;
	/** 消息创建时间 （整型） */
	protected String createTime;
	/** 消息类型 */
	protected String msgType;

	public void parse(Map<String, String> params) {
		this.toUserName = params.get("ToUserName");
		this.fromUserName = params.get("FromUserName");

		DateTime dt = new DateTime(Long.parseLong(params.get("CreateTime")) * 1000); // TODO
		this.createTime = dt.toString("yyyyMMddHHmmss");

		this.msgType = params.get("MsgType");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(sysId).append("|");
		sb.append(toUserName).append("|");
		sb.append(fromUserName).append("|");
		sb.append(createTime).append("|");
		sb.append(msgType);
		return sb.toString();
	}

	public String getSysId() {
		return sysId;
	}

	public void setSysId(String sysId) {
		this.sysId = sysId;
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
}

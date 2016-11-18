package net.ozsofts.wechat.core.message;

import java.util.Map;

public class ReceiveNormalMessage extends ReceiveMessage {
	/** 消息id，64位整型 */
	protected String msgId;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.msgId = params.get("MsgId");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append(" | ");
		sb.append(msgId);
		return sb.toString();
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}

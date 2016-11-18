package net.ozsofts.wechat.message.req;

import java.util.Map;

public class EventMessage extends RequestMessage {
	/** 事件类型 */
	private String event;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.event = params.get("Event");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(event);
		return sb.toString();
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}

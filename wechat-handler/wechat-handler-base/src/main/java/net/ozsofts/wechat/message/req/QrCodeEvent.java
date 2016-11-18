package net.ozsofts.wechat.message.req;

import java.util.Map;

public final class QrCodeEvent extends EventMessage {

	private String eventKey;
	private String ticket;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.eventKey = params.get("EventKey");
		this.ticket = params.get("Ticket");
	}

	public String getEventKey() {
		return eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(eventKey).append("|");
		sb.append(ticket).append("|");
		return sb.toString();
	}
}

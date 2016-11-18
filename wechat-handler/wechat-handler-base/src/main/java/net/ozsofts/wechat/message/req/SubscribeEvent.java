package net.ozsofts.wechat.message.req;

import java.util.Map;

import net.ozsofts.core.utils.StringUtils;

public final class SubscribeEvent extends EventMessage {

	/** 事件KEY值，qrscene_为前缀，后面为二维码的参数值 */
	private String eventKey;
	/** 二维码的ticket，可用来换取二维码图片 */
	private String ticket;

	public void parse(Map<String, String> params) {
		super.parse(params);

		if (params.containsKey("EventKey")) {
			this.eventKey = params.get("EventKey");
		}
		if (params.containsKey("Ticket")) {
			this.ticket = params.get("Ticket");
		}
	}

	public String getEventKey() {
		return eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	@Override
	public String toString() {
		if (StringUtils.isBlank(eventKey)) {
			return super.toString();
		}

		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(eventKey).append("|");
		sb.append(ticket).append("|");
		return sb.toString();
	}
}

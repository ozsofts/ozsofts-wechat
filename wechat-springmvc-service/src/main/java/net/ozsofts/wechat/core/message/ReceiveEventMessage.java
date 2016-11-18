package net.ozsofts.wechat.core.message;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class ReceiveEventMessage extends ReceiveMessage {
	//
	// 事件定义
	//
	/** 关注事件 */
	public final static String EVENT_SUBSCRIBE = "subscribe";
	/** 取消关注事件 */
	public final static String EVENT_UNSUBSCRIBE = "unsubscribe";
	/** 扫描二维码推送 */
	public final static String EVENT_SCAN = "SCAN";
	/** 上报地理位置事件 */
	public final static String EVENT_LOCATION = "LOCATION";
	/** 点击菜单拉取消息时的事件推送 */
	public final static String EVENT_CLICK = "CLICK";
	/** 点击菜单跳转链接时的事件推送 */
	public final static String EVENT_VIEW = "VIEW";

	/** 事件类型 */
	private String event;
	/** 事件KEY值 */
	private String eventKey;
	/** 二维码的ticket，可用来换取二维码图片 */
	private String ticket;
	/** 地理位置纬度 */
	private String latitude;
	/** 地理位置经度 */
	private String longitude;
	/** 地理位置精度 */
	private String precision;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.event = params.get("Event");

		if (params.containsKey("EventKey")) {
			this.eventKey = params.get("EventKey");
		}
		if (params.containsKey("Ticket")) {
			this.eventKey = params.get("Ticket");
		}
		if (EVENT_LOCATION.equals(this.event)) {
			this.latitude = params.get("Latitude");
			this.longitude = params.get("Longitude");
			this.precision = params.get("Precision");
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append(" | ");
		sb.append(event);

		StringBuilder sbdata = new StringBuilder();
		if (StringUtils.isNotBlank(this.eventKey)) {
			sbdata.append(eventKey).append(" | ");
		}
		if (StringUtils.isNotBlank(this.ticket)) {
			sbdata.append(ticket).append(" | ");
		}
		if (EVENT_LOCATION.equals(this.event)) {
			sbdata.append(this.latitude);
			sbdata.append(this.longitude);
			sbdata.append(this.precision);
		}

		if (sbdata.length() > 0) {
			sb.append(" | ").append(sbdata.toString());
		}
		return sb.toString();
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}
}

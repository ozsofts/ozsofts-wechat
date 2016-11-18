package net.ozsofts.wechat.message.req;

import java.util.Map;

/**
 * <p>
 * LOCATION
 * 
 * @author jack
 *
 */
public final class LocationSelectEvent extends EventMessage {

	private String eventKey;

	private String locationX;
	private String locationY;
	private String scale;
	private String label;
	private String poiname;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.eventKey = params.get("EventKey");
		this.locationX = params.get("Location_X");
		this.locationY = params.get("Location_Y");
		this.scale = params.get("Scale");
		this.label = params.get("Label");
		this.poiname = params.get("Poiname");
	}

	public String getEventKey() {
		return eventKey;
	}

	public String getLocationX() {
		return locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public String getScale() {
		return scale;
	}

	public String getLabel() {
		return label;
	}

	public String getPoiname() {
		return poiname;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(eventKey).append("|");
		sb.append(locationX).append("|");
		sb.append(locationY).append("|");
		sb.append(scale).append("|");
		sb.append(label).append("|");
		sb.append(poiname);
		return sb.toString();
	}
}

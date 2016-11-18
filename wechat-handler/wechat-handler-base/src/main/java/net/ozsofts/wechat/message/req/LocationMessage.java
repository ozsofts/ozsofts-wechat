package net.ozsofts.wechat.message.req;

import java.util.Map;

public class LocationMessage extends NormalMessage {

	private String locationX;
	private String locationY;
	private String scale;
	private String label;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.locationX = params.get("Location_X");
		this.locationY = params.get("Location_Y");
		this.scale = params.get("Scale");
		this.label = params.get("Label");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(locationX).append("|");
		sb.append(locationY).append("|");
		sb.append(scale).append("|");
		sb.append(label);
		return sb.toString();
	}

	public String getLocationX() {
		return locationX;
	}

	public void setLocationX(String locationX) {
		this.locationX = locationX;
	}

	public String getLocationY() {
		return locationY;
	}

	public void setLocationY(String locationY) {
		this.locationY = locationY;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}

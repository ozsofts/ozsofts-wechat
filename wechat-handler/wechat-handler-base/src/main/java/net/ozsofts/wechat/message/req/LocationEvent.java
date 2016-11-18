package net.ozsofts.wechat.message.req;

import java.util.Map;

/**
 * <p>
 * LOCATION
 * 
 * @author jack
 *
 */
public final class LocationEvent extends EventMessage {

	private String latitude;
	private String longitude;
	private String precision;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.latitude = params.get("Latitude");
		this.longitude = params.get("Latitude");
		this.precision = params.get("Precision");
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(latitude).append("|");
		sb.append(longitude).append("|");
		sb.append(precision);
		return sb.toString();
	}
}

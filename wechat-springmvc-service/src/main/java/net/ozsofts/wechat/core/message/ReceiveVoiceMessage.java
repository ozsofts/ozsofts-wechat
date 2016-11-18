package net.ozsofts.wechat.core.message;

import java.util.Map;

public class ReceiveVoiceMessage extends ReceiveNormalMessage {
	private String mediaId;
	private String format;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.mediaId = params.get("MediaId");
		this.format = params.get("Format");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append(" | ");
		sb.append(mediaId).append(" | ");
		sb.append(format).append(" | ");
		return sb.toString();
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}
}

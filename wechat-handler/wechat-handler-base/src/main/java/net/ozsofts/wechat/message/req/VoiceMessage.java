package net.ozsofts.wechat.message.req;

import java.util.Map;

public class VoiceMessage extends NormalMessage {
	private String mediaId;
	private String format;
	private String recognition;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.mediaId = params.get("MediaId");
		this.format = params.get("Format");
		this.recognition = params.get("Recognition");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(mediaId).append("|");
		sb.append(format).append("|");
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

	public String getRecognition() {
		return recognition;
	}
}

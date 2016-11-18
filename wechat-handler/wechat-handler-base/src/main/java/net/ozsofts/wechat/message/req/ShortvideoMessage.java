package net.ozsofts.wechat.message.req;

import java.util.Map;

public class ShortvideoMessage extends NormalMessage {
	private String mediaId;
	private String thumbMediaId;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.mediaId = params.get("MediaId");
		this.thumbMediaId = params.get("ThumbMediaId");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(mediaId).append("|");
		sb.append(thumbMediaId).append("|");
		return sb.toString();
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}

package net.ozsofts.wechat.core.message;

import java.util.Map;

public class ReceiveImageMessage extends ReceiveNormalMessage {
	private String picUrl;
	private String mediaId;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.picUrl = params.get("PicUrl");
		this.mediaId = params.get("MediaId");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append(" | ");
		sb.append(mediaId).append(" | ");
		sb.append(picUrl).append(" | ");
		return sb.toString();
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}

package net.ozsofts.wechat.message.resp;

import java.util.HashMap;
import java.util.Map;

public class VideoMessage extends BaseMessage {
	private String mediaId;
	/** 缩略图的媒体ID */
	private String thumbMediaId;
	private String title;
	private String description;

	public VideoMessage() {
		super.msgType = ResponseType.MSGTYPE_VIDEO;
	}

	@Override
	protected String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Video>");
		sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>");
		sb.append("<Title><![CDATA[").append(title).append("]]></Title>");
		sb.append("<Description><![CDATA[").append(description).append("]]></Description>");
		sb.append("</Video>");
		return sb.toString();
	}

	@Override
	protected void makeMap(Map<String, Object> data) {
		Map<String, Object> cont = new HashMap<String, Object>();
		cont.put("media_id", mediaId);
		cont.put("thumb_media_id", thumbMediaId);
		cont.put("title", title);
		cont.put("desctiprion", description);

		data.put("video", cont);
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

package net.ozsofts.wechat.message.resp;

import java.util.HashMap;
import java.util.Map;

public class ImageMessage extends BaseMessage {
	private String mediaId;

	public ImageMessage() {
		super.msgType = ResponseType.MSGTYPE_IMAGE;
	}

	@Override
	protected String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Image>");
		sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>");
		sb.append("</Image>");
		return sb.toString();
	}

	@Override
	protected void makeMap(Map<String, Object> data) {
		Map<String, Object> cont = new HashMap<String, Object>();
		cont.put("media_id", mediaId);

		data.put("image", cont);
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}

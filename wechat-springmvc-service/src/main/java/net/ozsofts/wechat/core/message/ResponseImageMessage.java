package net.ozsofts.wechat.core.message;

import java.util.HashMap;
import java.util.Map;

public class ResponseImageMessage extends ResponseMessage {
	private String mediaId;

	public ResponseImageMessage() {
		super.msgType = ResponseMessage.MSGTYPE_IMAGE;
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

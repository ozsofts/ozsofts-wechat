package net.ozsofts.wechat.core.message;

import java.util.HashMap;
import java.util.Map;

public class ResponseVoiceMessage extends ResponseMessage {
	private String mediaId;

	public ResponseVoiceMessage() {
		super.msgType = ResponseMessage.MSGTYPE_VOICE;
	}

	@Override
	protected String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Voice>");
		sb.append("<MediaId><![CDATA[").append(mediaId).append("]]></MediaId>");
		sb.append("</Voice>");
		return sb.toString();
	}

	@Override
	protected void makeMap(Map<String, Object> data) {
		Map<String, Object> cont = new HashMap<String, Object>();
		cont.put("media_id", mediaId);

		data.put("voice", cont);
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}

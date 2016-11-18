package net.ozsofts.wechat.core.message;

import java.util.HashMap;
import java.util.Map;

public class ResponseTextMessage extends ResponseMessage {
	private String content;

	public ResponseTextMessage() {
		super.msgType = ResponseMessage.MSGTYPE_TEXT;
	}

	@Override
	protected String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Content><![CDATA[").append(content).append("]]></Content>");
		return sb.toString();
	}

	@Override
	protected void makeMap(Map<String, Object> data) {
		Map<String, Object> cont = new HashMap<String, Object>();
		cont.put("content", content);

		data.put("text", cont);
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}

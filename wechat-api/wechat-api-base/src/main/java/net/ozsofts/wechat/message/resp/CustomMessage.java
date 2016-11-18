package net.ozsofts.wechat.message.resp;

import java.util.HashMap;
import java.util.Map;

public class CustomMessage extends BaseMessage {

	private String kfAccount;

	public CustomMessage() {
		super.msgType = ResponseType.MSGTYPE_KF;
	}

	@Override
	protected String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<TransInfo>");
		sb.append("<TransInfo>").append(kfAccount).append("</TransInfo>");
		sb.append("</TransInfo>");
		return sb.toString();
	}

	@Override
	protected void makeMap(Map<String, Object> data) {
		Map<String, Object> cont = new HashMap<String, Object>();
		cont.put("KfAccount", kfAccount);

		data.put("TransInfo", cont);
	}

	public String getKfAccount() {
		return kfAccount;
	}

	public void setKfAccount(String kfAccount) {
		this.kfAccount = kfAccount;
	}
}

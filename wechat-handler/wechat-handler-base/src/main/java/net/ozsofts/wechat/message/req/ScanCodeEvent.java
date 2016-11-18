package net.ozsofts.wechat.message.req;

import java.util.Map;

/**
 * <p>
 * 适用于：
 * <ul>
 * <li>scancode_push：扫码推事件的事件推送</li>
 * <li>scancode_waitmsg：扫码推事件且弹出“消息接收中”提示框的事件推送</li>
 * </ul>
 * 
 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141016&token=&lang=zh_CN}
 * 
 * @author jack
 */
public final class ScanCodeEvent extends EventMessage {

	private String eventKey;
	private String scanType;
	private String scanResult;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.eventKey = params.get("EventKey");
		this.scanType = params.get("ScanType");
		this.scanResult = params.get("ScanResult");
	}

	public String getEventKey() {
		return eventKey;
	}

	public String getScanType() {
		return scanType;
	}

	public String getScanResult() {
		return scanResult;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(eventKey).append("|");
		sb.append(scanType).append("|");
		sb.append(scanResult);
		return sb.toString();
	}
}

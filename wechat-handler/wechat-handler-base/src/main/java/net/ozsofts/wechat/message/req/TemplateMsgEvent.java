package net.ozsofts.wechat.message.req;

import java.util.Map;

/**
 * <p>
 * 模板消息反馈接口，适用于
 * <ul>
 * <li>TEMPLATESENDJOBFINISH: 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知，发送到开发者中心中填写的服务器配置地址中。</li>
 * </ul>
 * 
 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1433751277&token=&lang=zh_CN}
 * 
 * @author jack
 */
public class TemplateMsgEvent extends EventMessage {

	private String msgId;
	private String status;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.msgId = params.get("MsgID");
		this.status = params.get("Status");
	}

	public String getMsgId() {
		return msgId;
	}

	public String getStatus() {
		return status;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(msgId).append("|");
		sb.append(status);
		return sb.toString();
	}
}

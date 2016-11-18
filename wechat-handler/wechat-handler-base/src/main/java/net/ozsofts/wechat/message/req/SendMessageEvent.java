package net.ozsofts.wechat.message.req;

import java.util.Map;

/**
 * <p>
 * 群发消息反馈接口，适用于
 * <ul>
 * <li>MASSSENDJOBFINISH: 由于群发任务提交后，群发任务可能在一定时间后才完成，因此，群发接口调用时，仅会给出群发任务是否提交成功的提示，若群发任务提交成功，则在群发任务结束时，会向开发者在公众平台填写的开发者URL（callback URL）推送事件。</li>
 * </ul>
 * 
 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140549&token=&lang=zh_CN}
 * 
 * @author jack
 */
public class SendMessageEvent extends EventMessage {

	/** 群发的消息ID */
	private String msgId;
	/** 群发消息状态 */
	private String status;
	/** 发送总数 */
	private Integer totalCount;
	/** 过滤后的数量 */
	private Integer filterCount;
	/** 发送成功数量 */
	private Integer sentCount;
	/** 发送失败数量 */
	private Integer errorCount;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.msgId = params.get("MsgID");
		this.status = params.get("Status");
		this.totalCount = Integer.parseInt(params.get("TotalCount"));
		this.filterCount = Integer.parseInt(params.get("FilterCount"));
		this.sentCount = Integer.parseInt(params.get("SentCount"));
		this.errorCount = Integer.parseInt(params.get("ErrorCount"));
	}

	public String getMsgId() {
		return msgId;
	}

	public String getStatus() {
		return status;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public Integer getFilterCount() {
		return filterCount;
	}

	public Integer getSentCount() {
		return sentCount;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(msgId).append("|");
		sb.append(status).append("|");
		sb.append(totalCount).append("|");
		sb.append(filterCount).append("|");
		sb.append(sentCount).append("|");
		sb.append(errorCount);
		return sb.toString();
	}
}

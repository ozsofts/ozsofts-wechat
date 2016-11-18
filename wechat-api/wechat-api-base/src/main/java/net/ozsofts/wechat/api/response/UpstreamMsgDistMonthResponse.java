package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.UpstreamMsgDistMonth;

@SuppressWarnings("serial")
public class UpstreamMsgDistMonthResponse implements Serializable {

	private List<UpstreamMsgDistMonth> list;

	public List<UpstreamMsgDistMonth> getList() {
		return list;
	}

	public void setList(List<UpstreamMsgDistMonth> list) {
		this.list = list;
	}
}

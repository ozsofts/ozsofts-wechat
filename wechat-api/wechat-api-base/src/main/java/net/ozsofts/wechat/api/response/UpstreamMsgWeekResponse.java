package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.UpstreamMsgWeek;

@SuppressWarnings("serial")
public class UpstreamMsgWeekResponse implements Serializable {

	private List<UpstreamMsgWeek> list;

	public List<UpstreamMsgWeek> getList() {
		return list;
	}

	public void setList(List<UpstreamMsgWeek> list) {
		this.list = list;
	}
}

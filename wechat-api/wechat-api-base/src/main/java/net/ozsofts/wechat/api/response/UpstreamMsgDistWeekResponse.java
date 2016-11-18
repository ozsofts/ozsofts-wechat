package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.UpstreamMsgDistWeek;

@SuppressWarnings("serial")
public class UpstreamMsgDistWeekResponse implements Serializable {

	private List<UpstreamMsgDistWeek> list;

	public List<UpstreamMsgDistWeek> getList() {
		return list;
	}

	public void setList(List<UpstreamMsgDistWeek> list) {
		this.list = list;
	}
}

package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.InterfaceSummaryHour;

@SuppressWarnings("serial")
public class InterfaceSummaryHourResponse implements Serializable {

	private List<InterfaceSummaryHour> list;

	public List<InterfaceSummaryHour> getList() {
		return list;
	}

	public void setList(List<InterfaceSummaryHour> list) {
		this.list = list;
	}
}

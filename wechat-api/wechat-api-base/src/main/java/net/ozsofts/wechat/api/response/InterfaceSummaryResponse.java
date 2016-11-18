package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.InterfaceSummary;

@SuppressWarnings("serial")
public class InterfaceSummaryResponse implements Serializable {

	private List<InterfaceSummary> list;

	public List<InterfaceSummary> getList() {
		return list;
	}

	public void setList(List<InterfaceSummary> list) {
		this.list = list;
	}
}

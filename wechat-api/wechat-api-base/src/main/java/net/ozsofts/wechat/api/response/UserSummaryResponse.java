package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.UserSummary;

@SuppressWarnings("serial")
public class UserSummaryResponse implements Serializable {

	private List<UserSummary> list;

	public List<UserSummary> getList() {
		return list;
	}

	public void setList(List<UserSummary> list) {
		this.list = list;
	}
}

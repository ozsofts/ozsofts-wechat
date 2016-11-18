package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.UserReadHour;

@SuppressWarnings("serial")
public class UserReadHourResponse implements Serializable {

	private List<UserReadHour> list;

	public List<UserReadHour> getList() {
		return list;
	}

	public void setList(List<UserReadHour> list) {
		this.list = list;
	}
}

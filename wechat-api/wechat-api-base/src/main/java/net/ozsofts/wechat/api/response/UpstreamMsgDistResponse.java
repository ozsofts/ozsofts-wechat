package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.UpstreamMsgDist;

@SuppressWarnings("serial")
public class UpstreamMsgDistResponse implements Serializable {

	private List<UpstreamMsgDist> list;

	public List<UpstreamMsgDist> getList() {
		return list;
	}

	public void setList(List<UpstreamMsgDist> list) {
		this.list = list;
	}
}

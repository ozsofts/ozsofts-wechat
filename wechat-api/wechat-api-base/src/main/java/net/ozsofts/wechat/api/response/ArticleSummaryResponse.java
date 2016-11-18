package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.ArticleSummary;

@SuppressWarnings("serial")
public class ArticleSummaryResponse implements Serializable {

	private List<ArticleSummary> list;

	public List<ArticleSummary> getList() {
		return list;
	}

	public void setList(List<ArticleSummary> list) {
		this.list = list;
	}
}

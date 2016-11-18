package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import net.ozsofts.wechat.api.type.ArticleTotal;

@SuppressWarnings("serial")
public class ArticleTotalResponse implements Serializable {

	private List<ArticleTotal> list;

	public List<ArticleTotal> getList() {
		return list;
	}

	public void setList(List<ArticleTotal> list) {
		this.list = list;
	}
}

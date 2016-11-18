package net.ozsofts.wechat.core.message;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ResponseNewsMessage extends ResponseMessage {
	private List<NewsItem> newsItems = new LinkedList<NewsItem>();

	public ResponseNewsMessage() {
		super.msgType = ResponseMessage.MSGTYPE_NEWS;
	}

	public void addNewsItem(NewsItem item) {
		newsItems.add(item);
	}

	@Override
	protected String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<ArticleCount>").append(newsItems.size()).append("</ArticleCount>");
		sb.append("<Articles>");
		for (NewsItem item : newsItems) {
			sb.append(item.makeXML());
		}
		sb.append("</Articles>");
		return sb.toString();
	}

	@Override
	protected void makeMap(Map<String, Object> data) {
		Map<String, Object> news = new TreeMap<String, Object>();

		List<Map<String, Object>> articles = new ArrayList<Map<String, Object>>();
		for (NewsItem item : newsItems) {
			Map<String, Object> article = new TreeMap<String, Object>();
			item.makeMap(article);
			articles.add(article);
		}
		news.put("articles", articles);

		data.put("news", news);
	}

}

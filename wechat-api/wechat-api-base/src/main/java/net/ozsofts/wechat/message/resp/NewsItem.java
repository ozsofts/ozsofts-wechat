package net.ozsofts.wechat.message.resp;

import java.util.Map;

public class NewsItem {
	/** 图文消息标题 */
	private String title;
	/** 图文消息描述 */
	private String description;
	/** 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200 */
	private String picUrl;
	/** 点击图文消息跳转链接 */
	private String url;

	public String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<item>");
		sb.append("<Title><![CDATA[").append(title).append("]]></Title>");
		sb.append("<Description><![CDATA[").append(description).append("]]></Description>");
		sb.append("<PicUrl><![CDATA[").append(picUrl).append("]]></PicUrl>");
		sb.append("<Url><![CDATA[").append(url).append("]]></Url>");
		sb.append("</item>");
		return sb.toString();
	}

	protected void makeMap(Map<String, Object> data) {
		data.put("title", title);
		data.put("description", description);
		data.put("url", url);
		data.put("picurl", picUrl);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

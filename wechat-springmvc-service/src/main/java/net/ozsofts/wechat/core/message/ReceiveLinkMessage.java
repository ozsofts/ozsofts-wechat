package net.ozsofts.wechat.core.message;

import java.util.Map;

public class ReceiveLinkMessage extends ReceiveNormalMessage {
	private String title;
	private String description;
	private String url;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.title = params.get("Title");
		this.description = params.get("Description");
		this.url = params.get("Url");
	}

	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append(" | ");
		sb.append(title).append(" | ");
		sb.append(description).append(" | ");
		sb.append(url).append(" | ");
		return sb.toString();
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

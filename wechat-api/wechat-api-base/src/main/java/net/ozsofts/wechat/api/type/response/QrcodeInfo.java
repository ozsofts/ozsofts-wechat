package net.ozsofts.wechat.api.type.response;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class QrcodeInfo implements Serializable {
	private static final long serialVersionUID = 2332989851990964453L;

	private String ticket;
	@JSONField(name = "expire_seconds")
	private Integer expireSeconds;
	private String url;

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public Integer getExpireSeconds() {
		return expireSeconds;
	}

	public void setExpireSeconds(Integer expireSeconds) {
		this.expireSeconds = expireSeconds;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}

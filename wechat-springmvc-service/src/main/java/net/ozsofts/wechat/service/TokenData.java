/**
 * Copyright (c) 2016, YYD All Rights Reserved.
 */
package net.ozsofts.wechat.service;

/**
 * description: <br/>
 * date: 2016年1月23日 下午4:30:14 <br/>
 *
 * @author jack
 */
public class TokenData {
	private String accessToken;
	private long updatedTime;
	private String systemId;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public long getUpdatedTime() {
		return updatedTime;
	}

	public void setUpdatedTime(long updatedTime) {
		this.updatedTime = updatedTime;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}
}

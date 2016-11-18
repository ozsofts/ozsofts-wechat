package net.ozsofts.wechat.core.entity;

import net.ozsofts.core.db.Entity;
import net.ozsofts.core.db.annotation.Id;

/**
 * Description : 实体类 WxXmlmessage <br>
 * <br>
 * Time : 2016/04/20 16:57
 */

public class WxXmlmessage extends Entity {

	@Id
	private Long id;
	private String xml;
	private Long accountId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getXml() {
		return xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

}

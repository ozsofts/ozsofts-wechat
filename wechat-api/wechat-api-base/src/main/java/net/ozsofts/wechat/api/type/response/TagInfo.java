package net.ozsofts.wechat.api.type.response;

import java.io.Serializable;

public class TagInfo implements Serializable {
	private static final long serialVersionUID = 908281562365239183L;

	/** 标签id，由微信分配 */
	private String id;
	/** 标签名，UTF8编码 */
	private String name;
	/** 此标签下粉丝数 */
	private int count;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

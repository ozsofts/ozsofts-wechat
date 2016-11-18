package net.ozsofts.wechat.api.type;

import java.io.Serializable;

/**
 * 模版参数
 */
public class TemplateParam implements Serializable {
	private static final long serialVersionUID = 2383215484512561438L;

	/** 值 */
	private String value;
	/** 颜色 */
	private String color;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

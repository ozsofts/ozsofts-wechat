package net.ozsofts.wechat.api.type;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
public class MenuMatchRule implements Serializable {
	//
	// 性别标识
	//
	public static final String SEX_MALE = "1";
	public static final String SEX_FEMALE = "2";

	//
	// 客户端平台标识
	//
	public static final String CLIENT_PLATFORM_IOS = "1";
	public static final String CLIENT_PLATFORM_ANDROID = "2";
	public static final String CLIENT_PLATFORM_OTHER = "2";

	/** 用户标签的id，可通过用户标签管理接口获取 */
	@JSONField(name = "tag_id")
	private String tagId = "";
	/** 性别：男（1）女（2），不填则不做匹配 */
	private String sex = "";
	/** 国家信息，是用户在微信中设置的地区，具体请参考地区信息表 */
	private String country = "";
	/** 省份信息，是用户在微信中设置的地区，具体请参考地区信息表 */
	private String province = "";
	/** 城市信息，是用户在微信中设置的地区，具体请参考地区信息表 */
	private String city = "";
	/** 客户端版本，当前只具体到系统型号：IOS(1), Android(2),Others(3)，不填则不做匹配 */
	@JSONField(name = "client_platform_type")
	private String clientPlatformTpe = "";
	/** 语言信息，是用户在微信中设置的语言 */
	private String language = "";

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getClientPlatformTpe() {
		return clientPlatformTpe;
	}

	public void setClientPlatformTpe(String clientPlatformTpe) {
		this.clientPlatformTpe = clientPlatformTpe;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}

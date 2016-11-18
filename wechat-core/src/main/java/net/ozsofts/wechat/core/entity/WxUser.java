package net.ozsofts.wechat.core.entity;

import java.net.URLDecoder;
import java.sql.Timestamp;

import net.ozsofts.core.db.Entity;
import net.ozsofts.core.db.annotation.Id;

/**
 * Description : 实体类 WxUser <br>
 * <br>
 * Time : 2016/01/22 15:10
 */

public class WxUser extends Entity {

	@Id
	private Long id;
	private String openId;
	private String unionId;
	private String nickName;
	private String sex;
	private String language;
	private String city;
	private String province;
	private String country;
	private String headImgUrl;
	private Boolean isSubscribe;
	private Byte userType = 0;
	private Boolean isStaff;
	private Boolean isVendor;
	private String subDate;
	private String subTime;
	private String unsubDate;
	private String unsubTime;
	private String remark;
	private String mobile;
	private String systemId;
	private Timestamp updateTime;
	private Timestamp createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getNickName() {
		return nickName;
	}

	public String getNickNameStr() {
		try {
			return URLDecoder.decode(nickName, "UTF-8");
		} catch (Exception ex) {
			return nickName;
		}
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public Boolean getIsSubscribe() {
		return isSubscribe;
	}

	public void setIsSubscribe(Boolean isSubscribe) {
		this.isSubscribe = isSubscribe;
	}

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public Boolean getIsStaff() {
		return isStaff;
	}

	public void setIsStaff(Boolean isStaff) {
		this.isStaff = isStaff;
	}

	public Boolean getIsVendor() {
		return isVendor;
	}

	public void setIsVendor(Boolean isVendor) {
		this.isVendor = isVendor;
	}

	public String getSubDate() {
		return subDate;
	}

	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}

	public String getSubTime() {
		return subTime;
	}

	public void setSubTime(String subTime) {
		this.subTime = subTime;
	}

	public String getUnsubDate() {
		return unsubDate;
	}

	public void setUnsubDate(String unsubDate) {
		this.unsubDate = unsubDate;
	}

	public String getUnsubTime() {
		return unsubTime;
	}

	public void setUnsubTime(String unsubTime) {
		this.unsubTime = unsubTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getSystemId() {
		return systemId;
	}

	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

}

package net.ozsofts.wechat.api.type.response;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class KFInfo implements Serializable {
	private static final long serialVersionUID = 1071724723946569332L;

	/** 邀请等待确认 */
	public static final String INVITE_STATUS_WAITING = "waiting";
	/** 邀请被拒绝 */
	public static final String INVITE_STATUS_REJECTED = "rejected";
	/** 邀请过期 */
	public static final String INVITE_STATUS_EXPIRED = "expired";

	/** 客服编号 */
	@JSONField(name = "kf_id")
	private String id;
	/** 完整客服帐号，格式为：帐号前缀@公众号微信号 */
	@JSONField(name = "kf_account")
	private String account;
	/** 客服昵称 */
	@JSONField(name = "kf_nick")
	private String nick;
	/** 客服头像 */
	@JSONField(name = "kf_headimgurl")
	private String headImgUrl;
	/** 如果客服帐号已绑定了客服人员微信号，则此处显示微信号 */
	@JSONField(name = "kf_wx")
	private String wx;
	/** 如果客服帐号尚未绑定微信号，但是已经发起了一个绑定邀请，则此处显示绑定邀请的微信号 */
	@JSONField(name = "invite_wx")
	private String inviteEx;
	/** 如果客服帐号尚未绑定微信号，但是已经发起过一个绑定邀请，邀请的过期时间，为unix 时间戳 */
	@JSONField(name = "invite_expire_time")
	private String inviteExpireTime;
	/** 邀请的状态，有等待确认“waiting”，被拒绝“rejected”，过期“expired” */
	@JSONField(name = "invite_status")
	private String inviteStatus;
	/** 客服在线状态，目前为：1、web 在线 */
	private Integer status;
	/** 客服当前正在接待的会话数 */
	@JSONField(name = "accepted_case")
	private Integer acceptedCase;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getHeadImgUrl() {
		return headImgUrl;
	}

	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}

	public String getWx() {
		return wx;
	}

	public void setWx(String wx) {
		this.wx = wx;
	}

	public String getInviteEx() {
		return inviteEx;
	}

	public void setInviteEx(String inviteEx) {
		this.inviteEx = inviteEx;
	}

	public String getInviteExpireTime() {
		return inviteExpireTime;
	}

	public void setInviteExpireTime(String inviteExpireTime) {
		this.inviteExpireTime = inviteExpireTime;
	}

	public String getInviteStatus() {
		return inviteStatus;
	}

	public void setInviteStatus(String inviteStatus) {
		this.inviteStatus = inviteStatus;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAcceptedCase() {
		return acceptedCase;
	}

	public void setAcceptedCase(Integer acceptedCase) {
		this.acceptedCase = acceptedCase;
	}
}

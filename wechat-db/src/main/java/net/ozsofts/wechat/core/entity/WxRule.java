package net.ozsofts.wechat.core.entity;

import java.sql.Timestamp;

import net.ozsofts.core.db.Entity;
import net.ozsofts.core.db.annotation.Id;

/**
 * Description : 实体类 WxRule <br>
 * <br>
 * Time : 2016/01/22 15:10
 */

public class WxRule extends Entity {
	//
	// 规则类型定义
	//
	/** 关注时自动回复 */
	public static final String RULE_TYPE_DEFAULT = "default";
	/** 无匹配消息时自动回复 */
	public static final String RULE_TYPE_AUTO = "auto";
	/** 关键字匹配 */
	public static final String RULE_TYPE_KEYWORD = "keyword";

	@Id
	private Long id;
	private String ruleName;
	private String ruleType;
	private String ruleValue;
	private String matchType;
	private Long respMessageId;
	private String refType;
	private Long refId;
	private Long accountId;
	private Timestamp updateTime;
	private Timestamp createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getRuleValue() {
		return ruleValue;
	}

	public void setRuleValue(String ruleValue) {
		this.ruleValue = ruleValue;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public Long getRespMessageId() {
		return respMessageId;
	}

	public void setRespMessageId(Long respMessageId) {
		this.respMessageId = respMessageId;
	}

	public String getRefType() {
		return refType;
	}

	public void setRefType(String refType) {
		this.refType = refType;
	}

	public Long getRefId() {
		return refId;
	}

	public void setRefId(Long refId) {
		this.refId = refId;
	}

	public Long getAccountId() {
		return accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
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

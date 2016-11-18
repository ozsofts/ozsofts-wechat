package net.ozsofts.wechat.core.manager;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxRule;

/**
 * Description : Manager接口 WxRuleManager <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

public interface WxRuleManager {

	public int addWxRule(WxRule wxRule);

	public int updateWxRule(WxRule wxRule);

	public int deleteWxRule(Long id);

	public int batchDeleteWxRule(Long[] ids);

	public WxRule getWxRule(Long id);

	public List<WxRule> getAllWxRule();

	public List<WxRule> findRules(long accountId, String ruleType, String refType, long refId);
}

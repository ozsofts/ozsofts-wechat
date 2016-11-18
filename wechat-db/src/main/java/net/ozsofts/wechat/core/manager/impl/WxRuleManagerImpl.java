package net.ozsofts.wechat.core.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxRule;
import net.ozsofts.wechat.core.manager.WxRuleManager;

/**
 * Description : Manager实现类 WxRuleManagerImpl <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

@Service
public class WxRuleManagerImpl implements WxRuleManager {

	@Resource(name = "wxRuleDao")
	private Dao<WxRule> wxRuleDao;

	@Override
	public int addWxRule(WxRule wxRule) {
		int rows = wxRuleDao.save(wxRule);
		return rows;
	}

	@Override
	public int updateWxRule(WxRule wxRule) {
		int rows = wxRuleDao.dynamicUpdate(wxRule);
		return rows;
	}

	@Override
	public int deleteWxRule(Long id) {
		int rows = wxRuleDao.delete(id);
		return rows;
	}

	@Override
	public int batchDeleteWxRule(Long[] ids) {
		int rows = wxRuleDao.batchDelete(ids);
		return rows;
	}

	@Override
	public WxRule getWxRule(Long id) {
		WxRule wxRule = wxRuleDao.get(id);
		return wxRule;
	}

	@Override
	public List<WxRule> getAllWxRule() {
		List<WxRule> list = wxRuleDao.findAll();
		return list;
	}

	public List<WxRule> findRules(long accountId, String ruleType, String refType, long refId) {
		List<Object> params = new ArrayList<Object>();
		params.add(accountId);
		params.add(StringUtils.isNotBlank(ruleType) ? ruleType : WxRule.RULE_TYPE_AUTO);
		params.add(StringUtils.isBlank(refType) ? "" : refType);

		StringBuilder sql = new StringBuilder("select * from tbl_wx_rules where account=? and ref_type=?");
		if (StringUtils.isNotBlank(refType) && refId > 0l) {
			sql.append(" and ref_id=?");
			params.add(refId);
		}
		return wxRuleDao.find(sql.toString(), params.toArray());
	}

}

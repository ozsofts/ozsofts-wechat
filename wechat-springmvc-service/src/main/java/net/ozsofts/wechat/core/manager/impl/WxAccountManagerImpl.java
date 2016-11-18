package net.ozsofts.wechat.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.manager.WxAccountManager;

/**
 * Description : Manager实现类 WxAccountManagerImpl <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

@Service
public class WxAccountManagerImpl implements WxAccountManager {

	@Resource(name = "wxAccountDao")
	private Dao<WxAccount> wxAccountDao;

	@Override
	public int addWxAccount(WxAccount wxAccount) {
		int rows = wxAccountDao.save(wxAccount);
		return rows;
	}

	@Override
	public int updateWxAccount(WxAccount wxAccount) {
		int rows = wxAccountDao.dynamicUpdate(wxAccount);
		return rows;
	}

	@Override
	public int deleteWxAccount(Long id) {
		int rows = wxAccountDao.delete(id);
		return rows;
	}

	@Override
	public int batchDeleteWxAccount(Long[] ids) {
		int rows = wxAccountDao.batchDelete(ids);
		return rows;
	}

	@Override
	public WxAccount getWxAccount(Long id) {
		WxAccount wxAccount = wxAccountDao.get(id);
		return wxAccount;
	}

	@Override
	public List<WxAccount> getAllWxAccount() {
		List<WxAccount> list = wxAccountDao.findAll();
		return list;
	}

	public WxAccount getAccountBySystemId(String systemId) {
		return wxAccountDao.findUniqueByProperty("system_id", systemId);
	}
}

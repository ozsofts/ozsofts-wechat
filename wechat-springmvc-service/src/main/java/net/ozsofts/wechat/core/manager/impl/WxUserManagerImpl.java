package net.ozsofts.wechat.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxUser;
import net.ozsofts.wechat.core.manager.WxUserManager;

/**
 * Description : Manager实现类 WxUserManagerImpl <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

@Service
public class WxUserManagerImpl implements WxUserManager {

	@Resource(name = "wxUserDao")
	private Dao<WxUser> wxUserDao;

	@Override
	public int addWxUser(WxUser wxUser) {
		int rows = wxUserDao.save(wxUser);
		return rows;
	}

	@Override
	public int updateWxUser(WxUser wxUser) {
		int rows = wxUserDao.dynamicUpdate(wxUser);
		return rows;
	}

	@Override
	public int deleteWxUser(Long id) {
		int rows = wxUserDao.delete(id);
		return rows;
	}

	@Override
	public int batchDeleteWxUser(Long[] ids) {
		int rows = wxUserDao.batchDelete(ids);
		return rows;
	}

	@Override
	public WxUser getWxUser(Long id) {
		WxUser wxUser = wxUserDao.get(id);
		return wxUser;
	}

	@Override
	public List<WxUser> getAllWxUser() {
		List<WxUser> list = wxUserDao.findAll();
		return list;
	}

	@Override
	public WxUser getUserByOpenId(String openId) {
		return wxUserDao.findUniqueByProperty("open_id", openId);
	}

}

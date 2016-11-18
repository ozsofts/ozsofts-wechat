package net.ozsofts.wechat.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxGroup;
import net.ozsofts.wechat.core.manager.WxGroupManager;

/**
 * Description	 : Manager实现类 WxGroupManagerImpl
 * <br><br>Time	 : 2016/01/22 15:12
 */

@Service
public class WxGroupManagerImpl implements WxGroupManager {

	@Resource(name = "wxGroupDao")
	private Dao<WxGroup> wxGroupDao;

	@Override
	public int addWxGroup(WxGroup wxGroup) {
		int rows = wxGroupDao.save(wxGroup);
		return rows;
	}

	@Override
	public int updateWxGroup(WxGroup wxGroup) {
		int rows = wxGroupDao.dynamicUpdate(wxGroup);
		return rows;
	}

	@Override
	public int deleteWxGroup(Long id) {
		int rows = wxGroupDao.delete(id);
		return rows;
	}

	@Override
	public int batchDeleteWxGroup(Long[] ids) {
		int rows = wxGroupDao.batchDelete(ids);
		return rows;
	}

	@Override
	public WxGroup getWxGroup(Long id) {
		WxGroup wxGroup = wxGroupDao.get(id);
		return wxGroup;
	}

	@Override
	public List<WxGroup> getAllWxGroup() {
		List<WxGroup> list = wxGroupDao.findAll();
		return list;
	}

}


package net.ozsofts.wechat.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxXmlmessage;
import net.ozsofts.wechat.core.manager.WxXmlmessageManager;

/**
 * Description : Manager实现类 WxXmlmessageManagerImpl <br>
 * <br>
 * Time : 2016/04/20 16:58
 */

@Service
public class WxXmlmessageManagerImpl implements WxXmlmessageManager {

	@Resource(name = "wxXmlmessageDao")
	private Dao<WxXmlmessage> wxXmlmessageDao;

	@Override
	public int addWxXmlmessage(WxXmlmessage wxXmlmessage) {
		int rows = wxXmlmessageDao.dynamicSave(wxXmlmessage);
		return rows;
	}

	@Override
	public int updateWxXmlmessage(WxXmlmessage wxXmlmessage) {
		int rows = wxXmlmessageDao.dynamicUpdate(wxXmlmessage);
		return rows;
	}

	@Override
	public int deleteWxXmlmessage(Long id) {
		int rows = wxXmlmessageDao.delete(id);
		return rows;
	}

	@Override
	public int batchDeleteWxXmlmessage(Long[] ids) {
		int rows = wxXmlmessageDao.batchDelete(ids);
		return rows;
	}

	@Override
	public WxXmlmessage getWxXmlmessage(Long id) {
		WxXmlmessage wxXmlmessage = wxXmlmessageDao.get(id);
		return wxXmlmessage;
	}

	@Override
	public List<WxXmlmessage> getAllWxXmlmessage() {
		List<WxXmlmessage> list = wxXmlmessageDao.findAll();
		return list;
	}

}

package net.ozsofts.wechat.core.manager;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxGroup;

/**
 * Description	 : Manager接口 WxGroupManager
 * <br><br>Time	 : 2016/01/22 15:12
 */

public interface WxGroupManager {

	public int addWxGroup(WxGroup wxGroup);

	public int updateWxGroup(WxGroup wxGroup);

	public int deleteWxGroup(Long id);

	public int batchDeleteWxGroup(Long[] ids);

	public WxGroup getWxGroup(Long id);

	public List<WxGroup> getAllWxGroup();

}


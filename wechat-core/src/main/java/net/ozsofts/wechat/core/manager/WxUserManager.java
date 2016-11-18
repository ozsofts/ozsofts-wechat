package net.ozsofts.wechat.core.manager;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxUser;

/**
 * Description : Manager接口 WxUserManager <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

public interface WxUserManager {

	public int addWxUser(WxUser wxUser);

	public int updateWxUser(WxUser wxUser);

	public int deleteWxUser(Long id);

	public int batchDeleteWxUser(Long[] ids);

	public WxUser getWxUser(Long id);

	public List<WxUser> getAllWxUser();

	public WxUser getUserByOpenId(String openId);
}

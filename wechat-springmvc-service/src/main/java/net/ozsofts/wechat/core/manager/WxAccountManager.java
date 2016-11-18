package net.ozsofts.wechat.core.manager;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxAccount;

/**
 * Description : Manager接口 WxAccountManager <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

public interface WxAccountManager {

	public int addWxAccount(WxAccount wxAccount);

	public int updateWxAccount(WxAccount wxAccount);

	public int deleteWxAccount(Long id);

	public int batchDeleteWxAccount(Long[] ids);

	public WxAccount getWxAccount(Long id);

	public List<WxAccount> getAllWxAccount();

	/**
	 * <p>
	 * 根据系统ID取得微信账号信息
	 * 
	 * @param systemId
	 * @return
	 */
	public WxAccount getAccountBySystemId(String systemId);
}

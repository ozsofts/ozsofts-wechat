package net.ozsofts.wechat.api;

import java.util.List;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.type.MenuContainer;
import net.ozsofts.wechat.api.type.MenuInfo;
import net.ozsofts.wechat.api.type.MenuMatchRule;

/**
 * 菜单相关
 * 
 * @author jack
 */
public interface MenuAPI {

	/**
	 * 创建菜单
	 *
	 * <p>
	 * 创建普通菜单 {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141013&token=&lang=zh_CN}
	 * <p>
	 * 个性化菜单管理 {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1455782296&token=&lang=zh_CN}
	 * 
	 * @param menu
	 *            菜单对象
	 * @param 修改化菜单条件
	 * @return 在创建个性化菜单时，成功时返回menuId，如果不是个性化，成功时返回空字符串
	 */
	public String create(String sysId, List<MenuInfo> menuList, MenuMatchRule rule) throws WechatException;

	/**
	 * 获取所有菜单
	 *
	 * @return 菜单列表对象
	 */
	public List<MenuContainer> get(String sysId) throws WechatException;

	/**
	 * 删除所有菜单，包括个性化菜单
	 *
	 * @param menuId
	 *            在指定menuId时，只删除对应的个性化菜单
	 * @return 调用结果
	 */
	public void delete(String sysId, String menuId) throws WechatException;

	/**
	 * <p>
	 * 检查userId(String sysId,粉丝的OpenID，或是粉丝的微信号)
	 * 
	 * @param userId
	 * @return
	 */
	public List<MenuInfo> tryMatch(String sysId, String userId) throws WechatException;
}

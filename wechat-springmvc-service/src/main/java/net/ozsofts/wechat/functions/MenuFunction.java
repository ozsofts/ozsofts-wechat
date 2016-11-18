package net.ozsofts.wechat.functions;

public interface MenuFunction {
	/**
	 * 创建新的菜单
	 * 
	 * @param menuInfos
	 */
	public void create(String token, MenuInfo[] menuInfos) throws Exception;

	/**
	 * <p>
	 * 取得当前的微信菜单信息
	 */
	public MenuInfo[] get(String token) throws Exception;

	/**
	 * <p>
	 * 删除菜单。注意，是全部删除。
	 */
	public void delete(String token) throws Exception;
}

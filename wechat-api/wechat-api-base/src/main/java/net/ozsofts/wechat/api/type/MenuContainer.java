package net.ozsofts.wechat.api.type;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 保存一组菜单的容器对象。
 * 
 * @author Jack
 */
@SuppressWarnings("serial")
public class MenuContainer implements Serializable {

	/** 当前菜单的标识，如果为空表示上普通菜单 */
	private String menuId;
	/** 当前菜单的列表 */
	private List<MenuInfo> menList;
	/** 对于个性化菜单保存相关 */
	private MenuMatchRule menuRule;

	public MenuContainer(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public List<MenuInfo> getMenList() {
		return menList;
	}

	public void setMenList(List<MenuInfo> menList) {
		this.menList = menList;
	}

	public MenuMatchRule getMenuRule() {
		return menuRule;
	}

	public void setMenuRule(MenuMatchRule menuRule) {
		this.menuRule = menuRule;
	}
}

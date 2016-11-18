package net.ozsofts.wechat.functions.impl;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.functions.MenuFunction;
import net.ozsofts.wechat.functions.MenuInfo;
import net.ozsofts.wechat.service.WechatCommService;

public class MenuFunctionImpl implements MenuFunction {
	@Autowired
	private WechatCommService wechatCommService;

	/**
	 * 创建新的菜单
	 * 
	 * @param menuInfos
	 */
	public void create(String token, MenuInfo[] menuInfos) throws Exception {
		Map<String, Object> menuMap = new TreeMap<String, Object>();
		List<Map<String, Object>> menuList = new LinkedList<Map<String, Object>>();
		for (MenuInfo menu : menuInfos) {
			menuList.add(menu.toMap());
		}
		menuMap.put("button", menuList);

		wechatCommService.post("/cgi-bin/menu/create", token, null, menuMap);
	}

	/**
	 * <p>
	 * 取得当前的微信菜单信息
	 */
	public MenuInfo[] get(String token) throws Exception {
		JSONObject jsonMenu = wechatCommService.get("/cgi-bin/menu/get", token, null);
		System.out.println(jsonMenu.toString());
		return null;
	}

	/**
	 * <p>
	 * 删除菜单。注意，是全部删除。
	 */
	public void delete(String token) throws Exception {
		wechatCommService.post("/cgi-bin/menu/delete", token, null, null);
	}
}

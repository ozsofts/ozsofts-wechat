package net.ozsofts.wechat.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.MenuAPI;
import net.ozsofts.wechat.api.type.MenuContainer;
import net.ozsofts.wechat.api.type.MenuInfo;
import net.ozsofts.wechat.api.type.MenuMatchRule;

public class MenuAPIImpl extends BaseAPI implements MenuAPI {

	@Override
	public String create(String sysId, List<MenuInfo> menuInfos, MenuMatchRule rule) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> menuList = new LinkedList<Map<String, Object>>();
		for (MenuInfo menu : menuInfos) {
			menuList.add(menu.toMap());
		}
		data.put("button", menuList);

		String url = null;
		if (rule != null) {
			data.put("matchrule", rule);
			url = "/cgi-bin/menu/addconditional";
		} else {
			url = "/cgi-bin/menu/create";
		}

		JSONObject result = post(sysId, url, null, data);
		if (rule != null) {
			// 个性化菜单返回菜单Id
			return result.getString("menuid");
		}

		// 一般菜单不返回任何信息
		return null;
	}

	@Override
	public List<MenuContainer> get(String sysId) throws WechatException {
		JSONObject result = get(sysId, "/cgi-bin/menu/get", null);

		List<MenuContainer> menuContainerList = new ArrayList<MenuContainer>();

		// 先取得普通菜单
		JSONObject menuObject = result.getJSONObject("menu");

		MenuContainer conmenu = new MenuContainer(null);
		JSONArray menuArray = menuObject.getJSONArray("button");
		List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();
		for (int i = 0; i < menuArray.size(); i++) {
			JSONObject menuData = menuArray.getJSONObject(i);
			MenuInfo menuInfo = new MenuInfo(menuData);
			menuInfoList.add(menuInfo);
		}
		conmenu.setMenList(menuInfoList);
		menuContainerList.add(conmenu);

		JSONArray conditionMenuArray = result.getJSONArray("conditionalmenu");
		for (int i = 0; i < conditionMenuArray.size(); i++) {
			menuObject = conditionMenuArray.getJSONObject(i);
			String menuId = menuObject.getString("menuid");

			conmenu = new MenuContainer(menuId);

			menuArray = menuObject.getJSONArray("button");
			menuInfoList = new ArrayList<MenuInfo>();
			for (int j = 0; j < menuArray.size(); j++) {
				JSONObject menuData = menuArray.getJSONObject(i);
				MenuInfo menuInfo = new MenuInfo(menuData);
				menuInfoList.add(menuInfo);
			}
			conmenu.setMenList(menuInfoList);

			JSONObject ruleObject = menuObject.getJSONObject("matchrule");
			MenuMatchRule rule = new MenuMatchRule();
			rule.setTagId(ruleObject.getString("tag_id"));
			rule.setSex(ruleObject.getString("sex"));
			rule.setCountry(ruleObject.getString("country"));
			rule.setProvince(ruleObject.getString("province"));
			rule.setCity(ruleObject.getString("city"));
			rule.setClientPlatformTpe(ruleObject.getString("client_platform_type"));
			rule.setLanguage(ruleObject.getString("language"));
			conmenu.setMenuRule(rule);

			menuContainerList.add(conmenu);
		}

		return menuContainerList;
	}

	@Override
	public void delete(String sysId, String menuId) throws WechatException {
		if (StringUtils.isBlank(menuId)) {
			// 删除全部菜单
			get(sysId, "/cgi-bin/menu/delete", null);
		} else {
			// 删除个性化菜单
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("menuid", menuId);

			post(sysId, "/cgi-bin/menu/delconditional", null, data);
		}
	}

	@Override
	public List<MenuInfo> tryMatch(String sysId, String userId) throws WechatException {
		JSONObject result = get(sysId, "/cgi-bin/menu/trymatch", null);

		List<MenuInfo> menuInfoList = new ArrayList<MenuInfo>();

		// 先取得普通菜单
		JSONArray menuArray = result.getJSONArray("button");
		for (int i = 0; i < menuArray.size(); i++) {
			JSONObject menuData = menuArray.getJSONObject(i);
			MenuInfo menuInfo = new MenuInfo(menuData);
			menuInfoList.add(menuInfo);
		}

		return menuInfoList;
	}
}

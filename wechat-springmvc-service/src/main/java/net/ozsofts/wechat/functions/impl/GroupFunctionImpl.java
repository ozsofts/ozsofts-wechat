package net.ozsofts.wechat.functions.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.core.entity.WxGroup;
import net.ozsofts.wechat.functions.GroupFunction;
import net.ozsofts.wechat.service.WechatCommService;

public class GroupFunctionImpl implements GroupFunction {
	@Autowired
	private WechatCommService wechatCommService;

	//
	// 对分组进行管理的方法
	//
	/**
	 * <p>
	 * 创建分组
	 * 
	 * @param name
	 *            新创建的分组的名称
	 */
	public WxGroup createGroup(String token, String name) throws Exception {
		Map<String, Object> groupMessage = new HashMap<String, Object>();
		groupMessage.put("name", name);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("group", groupMessage);

		JSONObject result = wechatCommService.post("/cgi-bin/groups/create", token, null, params);
		JSONObject groupObject = result.getJSONObject("group");

		int groupId = new Long(groupObject.getLong("id")).intValue();

		WxGroup wxgroup = new WxGroup();
		wxgroup.setGroupId(groupId);
		wxgroup.setName(name);

		return wxgroup;
	}

	/**
	 * <p>
	 * 修改分组的名称
	 * 
	 * @param token
	 * @param groupId
	 *            分组微信标识号
	 * @param newName
	 *            新的名称
	 * @throws Exception
	 */
	public void changeGroupName(String token, int groupId, String newName) throws Exception {
		Map<String, Object> groupMessage = new HashMap<String, Object>();
		groupMessage.put("id", groupId);
		groupMessage.put("name", newName);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("group", groupMessage);

		wechatCommService.post("/cgi-bin/groups/update", token, null, params);
	}

	/**
	 * <p>
	 * 删除分组
	 * 
	 * @param token
	 * @param groupId
	 *            分组微信标识号
	 * @throws Exception
	 */
	public void deleteGroup(String token, int groupId) throws Exception {
		Map<String, Object> groupMessage = new HashMap<String, Object>();
		groupMessage.put("id", groupId);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("group", groupMessage);

		wechatCommService.post("/cgi-bin/groups/delete", token, null, params);
	}

	/**
	 * <p>
	 * 修改用户的分组
	 * 
	 * @param token
	 * @param openId
	 * @param groupId
	 *            分组微信标识号
	 * @throws Exception
	 */
	public void changeUserGroup(String token, String openId, int groupId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openid", openId);
		params.put("to_groupid", groupId);

		wechatCommService.post("/cgi-bin/groups/members/update", token, null, params);
	}

	/**
	 * <p>
	 * 批量修改用户分组
	 * 
	 * @param token
	 * @param openIds
	 * @param groupId
	 *            分组微信标识号
	 * @throws Exception
	 */
	public void batchChangeUserGroup(String token, String[] openIds, int groupId) throws Exception {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("openid_list", openIds);
		params.put("to_groupid", groupId);

		wechatCommService.post("/cgi-bin/groups/members/batchupdate", token, null, params);
	}

	/**
	 * <p>
	 * 取得公众号所有的用户组信息
	 */
	public List<WxGroup> getAllGroups(String token) throws Exception {
		System.out.println("token=" + token);
		JSONObject result = wechatCommService.get("/cgi-bin/groups/get", token, null);

		List<WxGroup> groupList = new LinkedList<WxGroup>();

		JSONArray groups = result.getJSONArray("groups");
		for (int i = 0; i < groups.size(); i++) {
			JSONObject jo = groups.getJSONObject(i);
			int groupId = new Long(jo.getLong("id")).intValue();
			String name = jo.getString("name");
			int count = new Long(jo.getLong("count")).intValue();

			WxGroup wxgroup = new WxGroup();
			wxgroup.setGroupId(groupId);
			wxgroup.setName(name);
			wxgroup.setUserCount(count);
			groupList.add(wxgroup);
		}

		return groupList;
	}
}

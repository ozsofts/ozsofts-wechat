package net.ozsofts.wechat.api.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.UserAPI;
import net.ozsofts.wechat.api.type.response.UserInfo;

public class UserAPIImpl extends BaseAPI implements UserAPI {

	@Override
	public List<String> getUsers(String sysId, String nextOpenId) throws WechatException {
		Map<String, String> params = new HashMap<String, String>();
		if (StringUtils.isNotBlank(nextOpenId)) {
			params.put("next_openid", nextOpenId);
		}

		JSONObject result = get(sysId, "/cgi-bin/user/get", params);
		System.out.println(result.toString());

		int totalCount = new Long(result.getLong("total")).intValue();
		int thisCount = new Long(result.getLong("count")).intValue();
		System.out.println("total:" + totalCount + "   count:" + thisCount);

		List<String> openIdList = new LinkedList<String>();
		if (thisCount > 0 && result.containsKey("data")) {
			JSONObject data = result.getJSONObject("data");
			JSONArray openIds = data.getJSONArray("openid");
			for (int i = 0; i < openIds.size(); i++) {
				openIdList.add(openIds.getString(i));
			}
		}

		return openIdList;
	}

	@Override
	public void updateRemark(String sysId, String openId, String remark) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("openid", openId);
		data.put("remark", remark);

		post(sysId, "/cgi-bin/user/info/updateremark", null, data);
	}

	@Override
	public UserInfo getUserInfo(String sysId, String openId) throws WechatException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("openid", openId);

		JSONObject result = get(sysId, "/cgi-bin/user/info", params);

		return makeUserInfo(result);
	}

	@Override
	public List<UserInfo> getUserInfoList(String sysId, List<String> openIds) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();

		List<Map<String, String>> ol = new LinkedList<Map<String, String>>();
		for (String openId : openIds) {
			Map<String, String> dm = new HashMap<String, String>();
			dm.put("openid", openId);
			dm.put("lang", "zh-CN");
			ol.add(dm);
		}
		data.put("user_list", ol);

		JSONObject result = post(sysId, "/cgi-bin/user/info/batchget", null, data);

		List<UserInfo> ul = new LinkedList<UserInfo>();
		JSONArray ja = result.getJSONArray("user_info_list");
		for (int i = 0; i < ul.size(); i++) {
			ul.add(makeUserInfo(ja.getJSONObject(i)));
		}

		return ul;
	}

	@Override
	public void batchTagsToUser(String sysId, List<String> openIds, int tagId) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("openid_list", openIds);
		data.put("tagid", tagId);

		post(sysId, "/cgi-bin/tags/members/batchtagging", null, data);
	}

	@Override
	public void batchDeleteTagsToUser(String sysId, List<String> openIds, int tagId) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("openid_list", openIds);
		data.put("tagid", tagId);

		post(sysId, "/cgi-bin/tags/members/batchuntagging", null, data);
	}

	@Override
	public List<String> getUserTags(String sysId, String openId) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("openid", openId);

		JSONObject result = post(sysId, "/cgi-bin/tags/getidlist", null, data);

		List<String> tagIdList = new LinkedList<String>();
		JSONArray ts = result.getJSONArray("tagid_list");
		for (int i = 0; i < ts.size(); i++) {
			tagIdList.add(ts.getString(i));
		}
		return tagIdList;
	}

	private UserInfo makeUserInfo(JSONObject result) {
		UserInfo user = new UserInfo();

		/* 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 */
		user.setIsSubscribe(result.containsKey("subscribe") && result.getString("subscribe").equals("1") ? true : false);

		/* 用户所在城市 */
		user.setCity(result.containsKey("city") ? result.getString("city") : "");
		/* 用户所在省份 */
		user.setProvince(result.containsKey("province") ? result.getString("province") : "");
		/* 用户所在国家 */
		user.setCountry(result.containsKey("country") ? result.getString("country") : "");

		user.setOpenId(result.containsKey("openid") ? result.getString("openid") : "");
		/* 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 */
		user.setUnionId(result.containsKey("unionid") ? result.getString("unionid") : "");
		/* 用户的昵称 */
		user.setNickName(result.containsKey("nickname") ? result.getString("nickname") : "");
		/* 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
		user.setSex(result.containsKey("sex") ? result.getString("sex") : "0");
		/* 用户的语言，简体中文为zh_CN */
		user.setLanguage(result.containsKey("language") ? result.getString("language") : "");

		/* 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */
		user.setHeadImgUrl(result.containsKey("headimgurl") ? result.getString("headimgurl") : "");

		/* 用户备注 */
		user.setRemark(result.containsKey("remark") ? result.getString("remark") : "");

		// TODO 读取标签信息
		return user;
	}
}

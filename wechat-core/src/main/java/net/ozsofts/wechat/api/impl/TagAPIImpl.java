package net.ozsofts.wechat.api.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.TagAPI;
import net.ozsofts.wechat.api.type.response.TagInfo;

public class TagAPIImpl extends BaseAPI implements TagAPI {

	@Override
	public TagInfo create(String sysId, String tagName) throws WechatException {
		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("name", tagName);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tag", nameMap);

		JSONObject result = post(sysId, "/cgi-bin/tags/create", null, data);

		TagInfo tag = new TagInfo();
		tag.setId(result.getString("id"));
		tag.setName(tagName);
		return tag;
	}

	@Override
	public List<TagInfo> getAll(String sysId) throws WechatException {
		JSONObject result = get(sysId, "/cgi-bin/tags/get", null);

		List<TagInfo> tagList = new LinkedList<TagInfo>();

		JSONArray tagArray = result.getJSONArray("tags");
		for (int i = 0; i < tagArray.size(); i++) {
			JSONObject t = tagArray.getJSONObject(i);

			TagInfo tag = new TagInfo();
			tag.setId(t.getString("id"));
			tag.setName(t.getString("name"));
			tag.setCount(t.getIntValue("count"));
			tagList.add(tag);
		}
		return tagList;
	}

	@Override
	public void update(String sysId, int tagId, String newTagName) throws WechatException {
		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("id", tagId);
		nameMap.put("name", newTagName);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tag", nameMap);

		post(sysId, "/cgi-bin/tags/update", null, data);
	}

	@Override
	public void delete(String sysId, int tagId) throws WechatException {
		Map<String, Object> nameMap = new HashMap<String, Object>();
		nameMap.put("id", tagId);

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tag", nameMap);

		post(sysId, "/cgi-bin/tags/delete", null, data);
	}

	@Override
	public List<String> getUsersByTagId(String sysId, int tagId, String nextOpenid) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tagid", tagId);
		if (StringUtils.isNotBlank(nextOpenid)) {
			data.put("next_openid", nextOpenid);
		}

		JSONObject result = post(sysId, "/cgi-bin/tags/delete", null, data);

		int count = result.getIntValue("count");

		List<String> openIdList = new LinkedList<String>();
		if (count > 0 && result.containsKey("data")) {
			JSONObject d = result.getJSONObject("data");
			JSONArray openIds = d.getJSONArray("openid");
			for (int i = 0; i < openIds.size(); i++) {
				openIdList.add(openIds.getString(i));
			}
		}

		return openIdList;
	}
}

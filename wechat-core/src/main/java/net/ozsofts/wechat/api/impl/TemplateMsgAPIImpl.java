package net.ozsofts.wechat.api.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.TemplateMsgAPI;
import net.ozsofts.wechat.api.type.TemplateMsg;

public class TemplateMsgAPIImpl extends BaseAPI implements TemplateMsgAPI {

	@Override
	public void setIndustry(String sysId, String[] industries) throws WechatException {
		if (industries == null || industries.length == 0) {

		}

		// 最多只能设置两个行业
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("industry_id1", industries[0]);
		if (industries.length > 1) {
			data.put("industry_id2", industries[1]);
		}
		post(sysId, "/cgi-bin/template/api_set_industry", null, data);
	}

	@Override
	public String[] getIndustry(String sysId) throws WechatException {
		JSONObject json = get(sysId, "/cgi-bin/template/get_industry", null);

		List<String> industryList = new ArrayList<String>();
		if (json.containsKey("primary_industry")) {
			JSONObject js = json.getJSONObject("primary_industry");
			String str = new StringBuilder(js.getString("first_class")).append("|").append(js.getString("second_class")).toString();
			industryList.add(str);
		}
		if (json.containsKey("secondary_industry")) {
			JSONObject js = json.getJSONObject("secondary_industry");
			String str = new StringBuilder(js.getString("first_class")).append("|").append(js.getString("second_class")).toString();
			industryList.add(str);
		}
		String[] industries = new String[industryList.size()];
		return industryList.toArray(industries);
	}

	@Override
	public String addTemplate(String sysId, String shortTemplateId) throws WechatException {
		// 最多只能设置两个行业
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("template_id_short", shortTemplateId);
		JSONObject json = post(sysId, "/cgi-bin/template/api_add_template", null, data);

		return json.getString("template_id");
	}

	@Override
	public String sendByTemplate(String sysId, TemplateMsg msg) throws WechatException {
		JSONObject json = post(sysId, "/cgi-bin/message/template/send", null, JSON.toJSONString(msg));
		return json.getString("msgid");
	}

	@Override
	public String sendByParams(String sysId, String openId, String msgId, String url, Map<String, String> params) throws WechatException {
		TemplateMsg msg = new TemplateMsg();
		msg.setTemplateId(msgId);
		msg.setTouser(openId);
		msg.setUrl(url);

		return sendByTemplate(sysId, msg);
	}
}

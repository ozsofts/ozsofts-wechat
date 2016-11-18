package net.ozsofts.wechat.functions.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.functions.TokenFunction;
import net.ozsofts.wechat.service.WechatCommService;

public class TokenFunctionImpl implements TokenFunction {
	@Autowired
	private WechatCommService wechatCommService;

	public Map<String, Object> getToken(String appId, String secret) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.PARA_GRANT_TYPE, "client_credential");
		params.put(Constants.PARA_APP_ID, appId);
		params.put(Constants.PARA_SECRET, secret);

		JSONObject json = wechatCommService.get("/cgi-bin/token", null, params);

		Map<String, Object> result = new HashMap<String, Object>();
		result.put(Constants.PARA_ACCESS_TOKEN, json.getString(Constants.PARA_ACCESS_TOKEN));
		result.put(Constants.PARA_EXPIRES_IN, json.getIntValue(Constants.PARA_EXPIRES_IN));
		return result;
	}
}

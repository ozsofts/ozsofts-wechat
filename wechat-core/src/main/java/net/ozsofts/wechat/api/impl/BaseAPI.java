package net.ozsofts.wechat.api.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.comm.WechatCommUtil;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;

public class BaseAPI implements InitializingBean {
	private static Logger log = LoggerFactory.getLogger(BaseAPI.class);

	@Autowired
	private WechatConfigManager wechatConfigManager;

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("BaseAPI afterPropertiesSet() {}", wechatConfigManager);
	}

	protected JSONObject get(String sysId, String funcUrl, Map<String, String> params) throws WechatException {
		log.debug("BaseAPI get({}, {}, {}) {}", sysId, funcUrl, params, wechatConfigManager);

		WechatConfigService wechatConfigService = wechatConfigManager.getWechatConfigService(sysId);
		if (wechatConfigService == null) {
			throw new WechatException("900002");
		}

		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put(Constants.PARA_ACCESS_TOKEN, wechatConfigService.getAccessToken());

		return WechatCommUtil.get(funcUrl, params);
	}

	protected JSONObject post(String sysId, String funcUrl, Map<String, String> params, Map<String, Object> data) throws WechatException {
		WechatConfigService wechatConfigService = wechatConfigManager.getWechatConfigService(sysId);
		if (wechatConfigService == null) {
			throw new WechatException("900002");
		}

		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put(Constants.PARA_ACCESS_TOKEN, wechatConfigService.getAccessToken());

		return WechatCommUtil.post(funcUrl, params, data);
	}

	protected JSONObject post(String sysId, String funcUrl, Map<String, String> params, String data) throws WechatException {
		WechatConfigService wechatConfigService = wechatConfigManager.getWechatConfigService(sysId);
		if (wechatConfigService == null) {
			throw new WechatException("900002");
		}

		if (params == null) {
			params = new HashMap<String, String>();
		}
		params.put(Constants.PARA_ACCESS_TOKEN, wechatConfigService.getAccessToken());

		return WechatCommUtil.post(funcUrl, params, data);
	}

	//
	// BEAN方法
	//
	public void setWechatConfigManager(WechatConfigManager wechatConfigManager) {
		this.wechatConfigManager = wechatConfigManager;
	}
}

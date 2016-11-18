package net.ozsofts.wechat.service.impl;

import java.util.HashMap;
import java.util.Map;

import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;

public class WechatConfigManagerImpl implements WechatConfigManager {
	private Map<String, WechatConfigService> wechatConfigServices;

	public WechatConfigManagerImpl() {
		wechatConfigServices = new HashMap<String, WechatConfigService>();
	}

	@Override
	public void register(String key, WechatConfigService wechatConfigService) {
		wechatConfigServices.put(key, wechatConfigService);
	}

	@Override
	public WechatConfigService getWechatConfigService(String key) {
		return wechatConfigServices.get(key);
	}

}

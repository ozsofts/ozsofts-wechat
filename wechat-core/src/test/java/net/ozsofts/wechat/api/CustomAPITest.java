package net.ozsofts.wechat.api;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.impl.BaseAPI;
import net.ozsofts.wechat.api.impl.CustomAPIImpl;
import net.ozsofts.wechat.message.resp.TextMessage;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;
import net.ozsofts.wechat.service.impl.WechatConfigManagerImpl;
import net.ozsofts.wechat.service.impl.WechatConfigServiceImpl;

public class CustomAPITest {
	private static CustomAPI customAPI;

	@BeforeClass
	public static void setUp() throws Exception {
		String appId = "wxd0c71ddb2756493c";
		String secret = "53329cb1c7379bcb8d54f5d52900d407";
		String token = "yvjie119";

		WechatConfigService wechatConfigService = new WechatConfigServiceImpl("yuantai", appId, secret, Constants.ENCRYPT_TYPE_RAW, "", token);

		WechatConfigManager wechatConfigManager = new WechatConfigManagerImpl();

		((WechatConfigServiceImpl) wechatConfigService).setWechatConfigManager(wechatConfigManager);
		((WechatConfigServiceImpl) wechatConfigService).initialize();

		customAPI = new CustomAPIImpl();
		((BaseAPI) customAPI).setWechatConfigManager(wechatConfigManager);
	}

	@Test
	public void testSend() throws WechatException {
		TextMessage msg = new TextMessage();
		msg.setContent("打扰了，这是一个测试的客服消息！");

		customAPI.sendMessage("yuantai", "o5JoVwEDzv8Ty-OneE8ddLgefoNE", msg);
		// customAPI.sendMessage("yuantai", "o5JoVwB765atZk01Loc_N9iqaIzU", msg);
	}

}

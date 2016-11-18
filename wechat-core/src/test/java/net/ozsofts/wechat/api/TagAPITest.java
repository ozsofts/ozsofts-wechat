package net.ozsofts.wechat.api;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.impl.BaseAPI;
import net.ozsofts.wechat.api.impl.TagAPIImpl;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;
import net.ozsofts.wechat.service.impl.WechatConfigManagerImpl;
import net.ozsofts.wechat.service.impl.WechatConfigServiceImpl;

public class TagAPITest {

	private static TagAPI tagAPI;

	@BeforeClass
	public static void setUp() throws Exception {
		String appId = "wxc7ce90c672a3272b";
		String secret = "6e59a6eeb0311e2a5b2a4cc723af4863";
		String token = "wemobirit";

		WechatConfigService wechatConfigService = new WechatConfigServiceImpl("test", appId, secret, Constants.ENCRYPT_TYPE_RAW, "", token);
		((WechatConfigServiceImpl) wechatConfigService).initialize();

		WechatConfigManager wechatConfigManager = new WechatConfigManagerImpl();
		wechatConfigManager.register("test", wechatConfigService);

		tagAPI = new TagAPIImpl();
		((BaseAPI) tagAPI).setWechatConfigManager(wechatConfigManager);
	}

	@Test
	public void testCreate() throws WechatException {
		tagAPI.create("test", "测试");
	}

	@Test
	public void testGetAll() throws WechatException {
		System.out.println(tagAPI.getAll("test"));
	}
}

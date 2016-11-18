package net.ozsofts.wechat.api;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.impl.BaseAPI;
import net.ozsofts.wechat.api.impl.MenuAPIImpl;
import net.ozsofts.wechat.api.type.MenuContainer;
import net.ozsofts.wechat.api.type.MenuInfo;
import net.ozsofts.wechat.api.type.MenuMatchRule;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;
import net.ozsofts.wechat.service.impl.WechatConfigManagerImpl;
import net.ozsofts.wechat.service.impl.WechatConfigServiceImpl;

public class MenAPITest {
	private static MenuAPI menuAPI;

	@BeforeClass
	public static void setUp() throws Exception {
		String appId = "wxc7ce90c672a3272b";
		String secret = "6e59a6eeb0311e2a5b2a4cc723af4863";
		String token = "wemobirit";

		WechatConfigService wechatConfigService = new WechatConfigServiceImpl("test", appId, secret, Constants.ENCRYPT_TYPE_RAW, "", token);
		((WechatConfigServiceImpl) wechatConfigService).initialize();

		WechatConfigManager wechatConfigManager = new WechatConfigManagerImpl();
		wechatConfigManager.register("test", wechatConfigService);

		menuAPI = new MenuAPIImpl();
		((BaseAPI) menuAPI).setWechatConfigManager(wechatConfigManager);
	}

	@Test
	public void testCreate() throws WechatException {
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();

		MenuInfo m = new MenuInfo();
		m.setType(MenuInfo.MENU_TYPE_CLICK);
		m.setName("今日歌曲");
		m.setKey("V1001_TODAY_MUSIC");
		menuInfos.add(m);

		m = new MenuInfo();
		m.setName("菜单1");
		menuInfos.add(m);

		List<MenuInfo> subMenus = new ArrayList<MenuInfo>();
		MenuInfo sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_VIEW);
		sm.setName("搜索");
		sm.setUrl("http://www.soso.com/");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_SCANCODE_WAITMSG);
		sm.setName("扫码带提示");
		sm.setKey("rselfmenu_1_1");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_SCANCODE_PUSH);
		sm.setName("扫码推事件");
		sm.setKey("rselfmenu_1_2");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_LOCATION_SELECT);
		sm.setName("发送位置");
		sm.setKey("rselfmenu_1_3");
		subMenus.add(sm);

		m.setChildren(subMenus);

		m = new MenuInfo();
		m.setName("发图");
		menuInfos.add(m);

		subMenus = new ArrayList<MenuInfo>();
		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_PIC_SYSPHOTO);
		sm.setName("系统拍照发图");
		sm.setKey("rselfmenu_2_1");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_PIC_PHOTOORALBUM);
		sm.setName("拍照或者相册发图");
		sm.setKey("rselfmenu_2_2");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_PIC_WEIXIN);
		sm.setName("微信相册发图");
		sm.setKey("rselfmenu_2_3");
		subMenus.add(sm);

		m.setChildren(subMenus);

		menuAPI.create("test", menuInfos, null);
	}

	@Test
	public void testCreateCondition() throws WechatException {
		List<MenuInfo> menuInfos = new ArrayList<MenuInfo>();

		MenuInfo m = new MenuInfo();
		m.setName("菜单1");
		menuInfos.add(m);

		List<MenuInfo> subMenus = new ArrayList<MenuInfo>();
		MenuInfo sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_VIEW);
		sm.setName("搜索");
		sm.setUrl("http://www.soso.com/");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_SCANCODE_WAITMSG);
		sm.setName("扫码带提示");
		sm.setKey("rselfmenu_1_1");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_SCANCODE_PUSH);
		sm.setName("扫码推事件");
		sm.setKey("rselfmenu_1_2");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_LOCATION_SELECT);
		sm.setName("发送位置");
		sm.setKey("rselfmenu_1_3");
		subMenus.add(sm);

		m.setChildren(subMenus);

		m = new MenuInfo();
		m.setName("发图");
		menuInfos.add(m);

		subMenus = new ArrayList<MenuInfo>();
		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_PIC_SYSPHOTO);
		sm.setName("系统拍照发图");
		sm.setKey("rselfmenu_2_1");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_PIC_PHOTOORALBUM);
		sm.setName("拍照或者相册发图");
		sm.setKey("rselfmenu_2_2");
		subMenus.add(sm);

		sm = new MenuInfo(m);
		sm.setType(MenuInfo.MENU_TYPE_PIC_WEIXIN);
		sm.setName("微信相册发图");
		sm.setKey("rselfmenu_2_3");
		subMenus.add(sm);

		m.setChildren(subMenus);

		MenuMatchRule rule = new MenuMatchRule();
		rule.setSex(MenuMatchRule.SEX_FEMALE);
		rule.setClientPlatformTpe(MenuMatchRule.CLIENT_PLATFORM_IOS);

		menuAPI.create("test", menuInfos, rule);
	}

	@Test
	public void testGet() throws WechatException {
		List<MenuContainer> menuContainerList = menuAPI.get("test");
		System.out.println(menuContainerList);
	}

	@Test
	public void testTryMatch() {
		fail("Not yet implemented");
	}

	@Test
	public void testDelete() throws WechatException {
		menuAPI.delete("test", null);
	}
}

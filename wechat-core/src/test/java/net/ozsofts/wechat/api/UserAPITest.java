package net.ozsofts.wechat.api;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.impl.BaseAPI;
import net.ozsofts.wechat.api.impl.UserAPIImpl;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;
import net.ozsofts.wechat.service.impl.WechatConfigManagerImpl;
import net.ozsofts.wechat.service.impl.WechatConfigServiceImpl;

public class UserAPITest {
	private static UserAPI userAPI;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String appId = "wxd0c71ddb2756493c";
		String secret = "53329cb1c7379bcb8d54f5d52900d407";
		String token = "yvjie119";

		WechatConfigService wechatConfigService = new WechatConfigServiceImpl("yuantai", appId, secret, Constants.ENCRYPT_TYPE_RAW, "", token);

		WechatConfigManager wechatConfigManager = new WechatConfigManagerImpl();

		((WechatConfigServiceImpl) wechatConfigService).setWechatConfigManager(wechatConfigManager);
		((WechatConfigServiceImpl) wechatConfigService).initialize();

		userAPI = new UserAPIImpl();
		((BaseAPI) userAPI).setWechatConfigManager(wechatConfigManager);
	}

	@Test
	public void testGetUsers() throws WechatException {
		System.out.println(userAPI.getUsers("yuantai", null));
	}

	@Test
	public void testUpdateRemark() throws WechatException {
		userAPI.updateRemark("yuantai", "oXZ_3t24OamYatKNVzNb6mVsPh3Q", "测试");
	}

	@Test
	public void testGetUserInfo() throws WechatException {
		System.out.println(userAPI.getUserInfo("yuantai", "o5JoVwEDzv8Ty-OneE8ddLgefoNE"));
		System.out.println("-------------------------------------------");
		System.out.println(userAPI.getUserInfo("yuantai", "o5JoVwHQ5bTI-zz7menjA0PSmIVs"));
		System.out.println("-------------------------------------------");
		System.out.println(userAPI.getUserInfo("yuantai", "o5JoVwB765atZk01Loc_N9iqaIzU"));
		System.out.println("-------------------------------------------");
		System.out.println(userAPI.getUserInfo("yuantai", "o5JoVwFszUu2NiQ4i-VM1hLv7Gqs"));
		System.out.println("-------------------------------------------");
		System.out.println(userAPI.getUserInfo("yuantai", "o5JoVwAxrBkhso__mpRrc9j8IREw"));
	}

	@Test
	public void testGetUserInfoList() {
		fail("Not yet implemented");
	}

	@Test
	public void testBatchTagsToUser() throws WechatException {
		List<String> openIds = new ArrayList<String>();
		openIds.add("oXZ_3t54NN08bvoA0N3H5VbYmqiM");

		userAPI.batchTagsToUser("test", openIds, 100);
	}

	@Test
	public void testBatchDeleteTagsToUser() throws WechatException {
		fail("Not yet implemented");
	}

	@Test
	public void testGetUserTags() throws WechatException {
		System.out.println(userAPI.getUserTags("test", "oXZ_3t54NN08bvoA0N3H5VbYmqiM"));
	}

}

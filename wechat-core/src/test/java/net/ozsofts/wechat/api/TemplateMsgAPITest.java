package net.ozsofts.wechat.api;

import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.impl.BaseAPI;
import net.ozsofts.wechat.api.impl.TemplateMsgAPIImpl;
import net.ozsofts.wechat.api.type.TemplateMsg;
import net.ozsofts.wechat.api.type.TemplateParam;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;
import net.ozsofts.wechat.service.impl.WechatConfigManagerImpl;
import net.ozsofts.wechat.service.impl.WechatConfigServiceImpl;

public class TemplateMsgAPITest {
	private static TemplateMsgAPI templateMsgAPI;

	@BeforeClass
	public static void setUp() throws Exception {
		String appId = "wxd0c71ddb2756493c";
		String secret = "53329cb1c7379bcb8d54f5d52900d407";
		String token = "yvjie119";

		WechatConfigService wechatConfigService = new WechatConfigServiceImpl("yuantai", appId, secret, Constants.ENCRYPT_TYPE_RAW, "", token);

		WechatConfigManager wechatConfigManager = new WechatConfigManagerImpl();

		((WechatConfigServiceImpl) wechatConfigService).setWechatConfigManager(wechatConfigManager);
		((WechatConfigServiceImpl) wechatConfigService).initialize();

		templateMsgAPI = new TemplateMsgAPIImpl();
		((BaseAPI) templateMsgAPI).setWechatConfigManager(wechatConfigManager);
	}

	@Test
	public void testGetIndustires() throws WechatException {
		String[] industries = templateMsgAPI.getIndustry("yuantai");
		System.out.println(industries);
	}

	@Test
	public void testSend() throws WechatException {
		TemplateMsg msg = new TemplateMsg();
		msg.setTemplateId("xfpEV_YApppwYNNQCC1m1Uif9Yy_VWwF6EwVxJ0ySgg");
		// msg.setTouser("o5JoVwEDzv8Ty-OneE8ddLgefoNE");
		msg.setTouser("o5JoVwB765atZk01Loc_N9iqaIzU");

		Map<String, TemplateParam> params = new HashMap<String, TemplateParam>();
		msg.setData(params);

		TemplateParam tp = new TemplateParam();
		tp.setValue("恭喜您成为17期一班学员。");
		params.put("first", tp);

		tp = new TemplateParam();
		tp.setValue("061102");
		params.put("keyword1", tp);

		tp = new TemplateParam();
		tp.setValue("张老师");
		params.put("keyword2", tp);

		tp = new TemplateParam();
		tp.setValue("我校为您提供吃饭和住宿，请您做出选择。以便我们更好的为您服务。记得不能迟到哦！");
		params.put("remark", tp);

		String msgId = templateMsgAPI.sendByTemplate("yuantai", msg);
		System.out.println("MsgId:" + msgId);
	}

}

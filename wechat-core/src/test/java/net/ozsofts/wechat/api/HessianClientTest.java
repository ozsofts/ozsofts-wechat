/**
 * Copyright (c) 2016, YYD All Rights Reserved.
 */
package net.ozsofts.wechat.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;

import net.ozsofts.wechat.api.type.TemplateMsg;
import net.ozsofts.wechat.api.type.TemplateParam;
import net.ozsofts.wechat.message.resp.TextMessage;

/**
 * description: <br/>
 * date: 2016年1月20日 上午10:06:53 <br/>
 *
 * @author jack
 */
public class HessianClientTest {
	@Test
	public void testUserAPI() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		UserAPI userAPI = (UserAPI) factory.create(UserAPI.class, "http://120.25.94.26/api/user");

		List<String> users = userAPI.getUsers("yuantai", null);
		System.out.println(users);
		// List<MenuContainer> menuList = menuAPI.get("yuantai");
		// System.out.println(menuList.size());
	}

	@Test
	public void testSendTemplateMsg() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		TemplateMsgAPI templateMsgAPI = (TemplateMsgAPI) factory.create(TemplateMsgAPI.class, "http://120.25.94.26/api/templateMsg");

		TemplateMsg msg = new TemplateMsg();
		msg.setTemplateId("xfpEV_YApppwYNNQCC1m1Uif9Yy_VWwF6EwVxJ0ySgg");
		msg.setTouser("o5JoVwEDzv8Ty-OneE8ddLgefoNE");
		// msg.setTouser("o5JoVwB765atZk01Loc_N9iqaIzU");

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

	@Test
	public void testSendCustomMessage() throws Exception {
		HessianProxyFactory factory = new HessianProxyFactory();
		CustomAPI customAPI = (CustomAPI) factory.create(CustomAPI.class, "http://120.25.94.26/api/custom");

		TextMessage msg = new TextMessage();
		msg.setContent("打扰了，这是一个测试的客服消息！");

		customAPI.sendMessage("yuantai", "o5JoVwEDzv8Ty-OneE8ddLgefoNE", msg);
		// customAPI.sendMessage("yuantai", "o5JoVwB765atZk01Loc_N9iqaIzU", msg);
	}
}

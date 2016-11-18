package net.ozsofts.wechat.server;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.web.context.WebApplicationContext;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "classpath*:/spring/spring-application.xml" })
public class WechatControllerTestCase {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private WechatConfigManager wechatConfigManager;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = webAppContextSetup(this.wac).build();
	}

	@Test
	public void auth() throws Exception {
		String sysId = "yuantai";

		MockHttpServletRequestBuilder request = get("/yuantai");
		makeRequestBuilder(sysId, request);

		ResultActions resultActions = mockMvc.perform(request);
		resultActions.andExpect(content().string("test"));
	}

	@Test
	public void testHandleTextMessage() throws Exception {
		String sysId = "yuantai";

		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[text]]></MsgType>" + //
				"<Content><![CDATA[this is a test]]></Content>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		MockHttpServletRequestBuilder request = post("/yuantai");
		makeRequestBuilder(sysId, request);
		request.content(msgdata);

		ResultActions resultActions = mockMvc.perform(request);
		resultActions.andExpect(content().string("test"));
	}

	private void makeRequestBuilder(String sysId, MockHttpServletRequestBuilder request) throws Exception {
		WechatConfigService wechatConfigService = wechatConfigManager.getWechatConfigService(sysId);

		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.PARA_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
		params.put(Constants.PARA_NONCE, String.valueOf(System.currentTimeMillis()));
		params.put(Constants.PARA_ENCRYPT_TYPE, Constants.ENCRYPT_TYPE_RAW);
		params.put(Constants.PARA_SIGNATURE,
				wechatConfigService.getSignature(params.get(Constants.PARA_TIMESTAMP), params.get(Constants.PARA_NONCE)));
		params.put(Constants.PARA_ECHOSTR, "test");

		for (Entry<String, String> entry : params.entrySet()) {
			request.param(entry.getKey(), entry.getValue());
		}
	}
}

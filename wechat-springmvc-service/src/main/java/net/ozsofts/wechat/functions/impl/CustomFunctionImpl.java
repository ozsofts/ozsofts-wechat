package net.ozsofts.wechat.functions.impl;

import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.core.message.ResponseMessage;
import net.ozsofts.wechat.functions.CustomFunction;
import net.ozsofts.wechat.service.WechatCommService;

public class CustomFunctionImpl implements CustomFunction {
	@Autowired
	private WechatCommService wechatCommService;

	// 发送客服消息的服务地址
	private static final String SEND_FUNC_URL = "/cgi-bin/message/custom/send";

	//
	// 通过客服接口发送信息，注意只有在用户上行消息的48小时内调用此接口才有作用
	//
	/** 发送客服信息 */
	public void sendMessage(String token, ResponseMessage message) throws Exception {
		wechatCommService.post(SEND_FUNC_URL, token, null, message.toMap());
	}
}

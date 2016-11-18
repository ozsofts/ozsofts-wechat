package net.ozsofts.wechat.functions;

import net.ozsofts.wechat.core.message.ResponseMessage;

public interface CustomFunction {
	// 发送客服消息的服务地址
	public static final String SEND_FUNC_URL = "/cgi-bin/message/custom/send";

	//
	// 通过客服接口发送信息，注意只有在用户上行消息的48小时内调用此接口才有作用
	//
	/** 发送客服信息 */
	public void sendMessage(String token, ResponseMessage message) throws Exception;
}

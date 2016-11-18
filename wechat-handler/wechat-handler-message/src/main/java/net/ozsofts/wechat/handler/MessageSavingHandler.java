package net.ozsofts.wechat.handler;

import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.core.manager.WxRespMessageManager;
import net.ozsofts.wechat.message.req.RequestMessage;
import net.ozsofts.wechat.message.resp.BaseMessage;

public class MessageSavingHandler implements MessageHandler {

	@Autowired
	private WxRespMessageManager wxRespMessageManager;

	@Override
	public BaseMessage handle(RequestMessage message) throws Exception {
		return null;
	}

}

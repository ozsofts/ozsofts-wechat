package net.ozsofts.wechat.service.message;

import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.message.ReceiveEventMessage;
import net.ozsofts.wechat.core.message.ReceiveMessage;
import net.ozsofts.wechat.core.message.ResponseMessage;
import net.ozsofts.wechat.service.MessageListener;
import net.ozsofts.wechat.service.UserService;

public class UserListener implements MessageListener {
	@Autowired
	private UserService userService;

	@Override
	public ResponseMessage onMessage(WxAccount wxaccount, ReceiveMessage message) throws Exception {
		if (!ReceiveMessage.MSGTYPE_EVENT.equals(message.getMsgType())) {
			return null;
		}

		ReceiveEventMessage event = (ReceiveEventMessage) message;

		ResponseMessage respMessage = null;
		if (ReceiveEventMessage.EVENT_SUBSCRIBE.equals(event.getEvent())) {
			respMessage = userService.subscribe(wxaccount, message.getFromUserName());
		} else if (ReceiveEventMessage.EVENT_UNSUBSCRIBE.equals(event.getEvent())) {
			respMessage = userService.unsubscribe(wxaccount, message.getFromUserName());
		}

		return respMessage;
	}
}

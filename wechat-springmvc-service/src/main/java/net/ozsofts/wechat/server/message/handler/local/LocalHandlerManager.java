package net.ozsofts.wechat.server.message.handler.local;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.message.ReceiveMessage;
import net.ozsofts.wechat.server.message.handler.MessageHandler;

/**
 * <p>
 * 本地消息处理管理对象
 * 
 * @author jack
 *
 */
public class LocalHandlerManager implements MessageHandler {
	private List<MessageHandler> messageHandlerList;

	@Override
	public void handle(WxAccount wxAccount, ReceiveMessage message) throws Exception {
		// 如果未设置处理对象，则不做任何处理
		if (messageHandlerList == null)
			return;
	}

	public void setMessageHandlerList(List<MessageHandler> messageHandlerList) {
		this.messageHandlerList = messageHandlerList;
	}
}

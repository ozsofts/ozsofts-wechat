package net.ozsofts.wechat.service.message;

import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.message.ReceiveMessage;
import net.ozsofts.wechat.core.message.ResponseMessage;
import net.ozsofts.wechat.server.message.service.MessageService;
import net.ozsofts.wechat.service.MessageListener;

/**
 * <p>
 * 对所有上行的信息进行通用处理
 * 
 * @author jack
 */
public class CommonMessageListener implements MessageListener {
	@Autowired
	private MessageService messageService;

	@Override
	public ResponseMessage onMessage(WxAccount wxaccount, ReceiveMessage recvMessage) throws Exception {
		if (ReceiveMessage.MSGTYPE_EVENT.equals(recvMessage.getMsgType())) {
			// 对于事件通知类信息，不在本类做处理
			return null;
		}

		// 保存上行的消息
		// messageService.save(wxaccount, recvMessage);

		return null; // 现在先不对上行的消息进行关键字匹配的处理

		// 对可能的关键字消息进行处理
		// return messageService.handleKeyword(wxaccount, recvMessage);
	}
}

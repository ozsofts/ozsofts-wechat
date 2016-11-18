package net.ozsofts.wechat.server.message.handler;

import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.message.ReceiveMessage;

/**
 * <p>
 * 消息侦听器对象
 * 
 * @author jack
 */
public interface MessageHandler {
	/**
	 * <p>
	 * 对消息进行处理
	 * 
	 * @param WxAccount
	 *            当前消息上行的公众号信息
	 * @param message
	 *            上行的消息
	 * @throws Exception
	 */
	public void handle(WxAccount wxAccount, ReceiveMessage message) throws Exception;
}

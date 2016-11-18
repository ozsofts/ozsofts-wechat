package net.ozsofts.wechat.service;

import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.message.ReceiveMessage;
import net.ozsofts.wechat.core.message.ResponseMessage;

/**
 * <p>
 * 消息侦听器对象
 * 
 * @author jack
 */
public interface MessageListener {
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
	public ResponseMessage onMessage(WxAccount wxAccount, ReceiveMessage message) throws Exception;
}

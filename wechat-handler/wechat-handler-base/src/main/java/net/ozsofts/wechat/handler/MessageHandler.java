package net.ozsofts.wechat.handler;

import net.ozsofts.wechat.message.req.RequestMessage;
import net.ozsofts.wechat.message.resp.BaseMessage;

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
	 * @param message
	 *            上行的消息
	 * @return 返回需要下行的消息，如果为null，则表示没有需要下行的消息，则系统会默认返回"success"
	 * @throws Exception
	 */
	public BaseMessage handle(RequestMessage message) throws Exception;
}

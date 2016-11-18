package net.ozsofts.wechat.core.manager;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxMessage;
import net.ozsofts.wechat.message.resp.BaseMessage;

/**
 * Description : Manager接口 WxMessageManager <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

public interface WxMessageManager {

	public int addWxMessage(WxMessage wxMessage);

	public int updateWxMessage(WxMessage wxMessage);

	public int deleteWxMessage(Long id);

	public int batchDeleteWxMessage(Long[] ids);

	public WxMessage getWxMessage(Long id);

	public List<WxMessage> getAllWxMessage();

	/**
	 * <p>
	 * 根据MsgId查找消息
	 * 
	 * @param msgId
	 * @return
	 */
	public WxMessage findByMsgid(String msgId);

	/**
	 * <p>
	 * 保存返回消息
	 * 
	 * @param accountId
	 * @param respMessage
	 */
	public void saveResponseMessage(String systemId, BaseMessage respMessage);
}

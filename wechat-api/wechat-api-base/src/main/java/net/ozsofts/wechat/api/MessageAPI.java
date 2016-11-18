package net.ozsofts.wechat.api;

import java.util.List;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.message.resp.BaseMessage;

/**
 * 消息相关API
 *
 * @author jack
 */
public interface MessageAPI {

	/**
	 * 群发消息给用户。 本方法调用需要账户为微信已认证账户
	 * 
	 * @param message
	 *            消息主体
	 * @param isToAll
	 *            是否发送给全部用户。false时需要填写tagId，true时可忽略tagId树形
	 * @param tagId
	 *            标签ID
	 * @return 群发结果
	 */
	public String sendMessageToUser(String sysId, BaseMessage message, boolean isToAll, Integer tagId) throws WechatException;

	/**
	 * 发布客服消息
	 *
	 * @param openid
	 *            关注者ID列表
	 * @param message
	 *            消息对象，支持各种消息类型
	 * @return 调用结果
	 */
	public String sendCustomMessage(String sysId, List<String> openIds, BaseMessage message) throws WechatException;

	/**
	 * <p>
	 * 删除群发消息
	 * 
	 * @param msgId
	 */
	public void delete(String sysId, String msgId) throws WechatException;

	/**
	 * <p>
	 * 消息预览
	 * 
	 * @param openId
	 */
	public String preview(String sysId, String openId, BaseMessage message) throws WechatException;

	/**
	 * <p>
	 * 查询消息群发的状态
	 * 
	 * @param msgId
	 * @return
	 */
	public String getStatus(String sysId, String msgId) throws WechatException;
}

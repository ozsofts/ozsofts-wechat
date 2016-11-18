package net.ozsofts.wechat.service;

import java.util.Map;

import net.ozsofts.wechat.handler.MessageHandler;

/**
 * <p>
 * 对所有上行的信息进行保存
 * 
 * @author jack
 */
public interface MessageService {
	/**
	 * <p>
	 * 对上行消息进行处理
	 * 
	 * @param reqdata
	 *            POST数据信息
	 * @param params
	 *            上传有参数信息
	 * @return
	 */
	public String handleMessage(String reqdata, Map<String, String> params);

	/**
	 * <p>
	 * 增加消息处理器
	 * 
	 * @param messageHandler
	 */
	public void addMessageHandler(MessageHandler messageHandler);
}

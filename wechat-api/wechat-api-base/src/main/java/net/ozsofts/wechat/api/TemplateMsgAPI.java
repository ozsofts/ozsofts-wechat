package net.ozsofts.wechat.api;

import java.util.Map;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.type.TemplateMsg;

/**
 * 模版消息 api
 */
public interface TemplateMsgAPI {
	/**
	 * 设置行业
	 *
	 * @param industry
	 *            行业参数
	 */
	public void setIndustry(String sysId, String[] industries) throws WechatException;

	/**
	 * <p>
	 * 获取行业信息
	 * 
	 * @return
	 */
	public String[] getIndustry(String sysId) throws WechatException;

	/**
	 * 添加模版
	 *
	 * @param shortTemplateId
	 *            模版短id
	 * @return 返回实际使用的模板ID
	 */
	public String addTemplate(String sysId, String shortTemplateId) throws WechatException;

	/**
	 * 发送模版消息
	 *
	 * @param msg
	 *            消息
	 * @return 返回msgId
	 */
	public String sendByTemplate(String sysId, TemplateMsg msg) throws WechatException;

	/**
	 * <p>
	 * 这种方法的模板在接口层进行定义，应用层与微信的消息进行隔离
	 * 
	 * @param sysId
	 * @param msgId
	 * @param params
	 * @return
	 * @throws WechatException
	 */
	public String sendByParams(String sysId, String openId, String msgId, String url, Map<String, String> params) throws WechatException;
}

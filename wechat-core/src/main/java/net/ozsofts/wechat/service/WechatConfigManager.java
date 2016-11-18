package net.ozsofts.wechat.service;

/**
 * <p>
 * 对所有的微信公众号的配置信息进行统一管理
 * 
 * @author jack
 */
public interface WechatConfigManager {
	/**
	 * <p>
	 * 注册一个新的微信管理对象
	 * 
	 * @param key
	 * @param wechatConfigService
	 */
	public void register(String key, WechatConfigService wechatConfigService);

	/**
	 * <p>
	 * 根据Key选择一个微信管理服务对象
	 * 
	 * @param key
	 * @return
	 */
	public WechatConfigService getWechatConfigService(String key);
}

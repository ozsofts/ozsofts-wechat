package net.ozsofts.wechat.service;

import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.message.ResponseMessage;

public interface UserService {
	/**
	 * <p>
	 * 微信关注
	 * 
	 * @param account
	 * @param openId
	 * @return
	 */
	public ResponseMessage subscribe(WxAccount account, String openId);

	/**
	 * <p>
	 * 取消关注
	 * 
	 * @param account
	 * @param openId
	 * @return
	 */
	public ResponseMessage unsubscribe(WxAccount account, String openId);
}

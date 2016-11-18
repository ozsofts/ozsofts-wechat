package net.ozsofts.wechat.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.core.utils.DateUtils;
import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.entity.WxUser;
import net.ozsofts.wechat.core.manager.WxUserManager;
import net.ozsofts.wechat.core.message.ResponseMessage;
import net.ozsofts.wechat.functions.UserFunction;
import net.ozsofts.wechat.service.TokenService;
import net.ozsofts.wechat.service.UserService;
import net.ozsofts.wechat.service.WechatEventService;

public class UserServiceImpl implements UserService {
	private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private WxUserManager wxUserManager;

	@Autowired
	private UserFunction userFunction;

	@Autowired
	private TokenService tokenService;
	@Autowired
	private WechatEventService wechatEventService;

	@Override
	public ResponseMessage subscribe(WxAccount account, String openId) {
		WxUser wxuser = wxUserManager.getUserByOpenId(openId);
		if (wxuser != null) {
			// 如果原来已经有用户信息，则检查是否退订过
			if (wxuser.getIsSubscribe() != null && wxuser.getIsSubscribe().booleanValue()) {
				// 如果当前是关注状态，则直接返回成功
				return null;
			}

			wxuser.setIsSubscribe(true);

			wxuser.setSubDate(DateUtils.getDate(DateUtils.YYYYMMDD));
			wxuser.setSubTime(DateUtils.getDate(DateUtils.HHMMSS));

			wxUserManager.updateWxUser(wxuser);
		} else {
			try {
				String token = tokenService.getAccessToken(account.getSystemId());

				// 新关注的用户,根据openId从微信取相关的数据
				wxuser = userFunction.getUserInfo(token, openId);
				wxuser.setAccountId(account.getId());
				wxUserManager.addWxUser(wxuser);
			} catch (Exception ex) {
				log.error("[userService:subscribe] 保存微信用户关注信息时出现错误-sysId:[" + account.getSystemId() + "], openId:[" + openId + "]", ex);
				return null;
			}
		}

		try {
			wechatEventService.subscribe(openId);
		} catch (Exception ex) {
			log.error("[userService:subscribe] 调用会员关注处理时出现错误-sysId:[" + account.getSystemId() + "], openId:[" + openId + "]", ex);
			return null;
		}

		// 此方法不返回数据，现在先使用原有系统自己的返回信息
		return null;
	}

	@Override
	public ResponseMessage unsubscribe(WxAccount account, String openId) {
		try {
			wechatEventService.unsubscribe(openId);
		} catch (Exception ex) {
			log.error("[userService:unsubscribe] 调用会员取消关注处理时出现错误-sysId:[" + account.getSystemId() + "], openId:[" + openId + "]", ex);
		}

		WxUser wxuser = wxUserManager.getUserByOpenId(openId);
		if (wxuser == null) {
			return null;
		}

		if (wxuser.getIsSubscribe() == null || !wxuser.getIsSubscribe().booleanValue()) {
			return null;
		}

		wxuser.setIsSubscribe(false);

		wxuser.setUnsubDate(DateUtils.getDate(DateUtils.YYYYMMDD));
		wxuser.setUnsubTime(DateUtils.getDate(DateUtils.HHMMSS));

		wxUserManager.updateWxUser(wxuser);
		return null;
	}
}

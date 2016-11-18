package net.ozsofts.wechat.functions;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxUser;

public interface UserFunction {
	/**
	 * <p>
	 * 取得公众号所有的用户信息
	 */
	public String getOpenIds(String token, String nextOpenId, List<String> openIdList) throws Exception;

	public List<String> getOpenIds(String token) throws Exception;

	public WxUser getUserInfo(String token, String openId) throws Exception;
}

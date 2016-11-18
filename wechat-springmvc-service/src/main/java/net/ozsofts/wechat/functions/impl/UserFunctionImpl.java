package net.ozsofts.wechat.functions.impl;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.core.utils.DateUtils;
import net.ozsofts.wechat.core.entity.WxUser;
import net.ozsofts.wechat.functions.UserFunction;
import net.ozsofts.wechat.service.WechatCommService;

public class UserFunctionImpl implements UserFunction {
	@Autowired
	private WechatCommService wechatCommService;

	/**
	 * <p>
	 * 取得公众号所有的用户信息
	 */
	public String getOpenIds(String token, String nextOpenId, List<String> openIdList) throws Exception {
		if (openIdList == null) {
			return null;
		}

		Map<String, String> params = new HashMap<String, String>();
		if (StringUtils.isNotBlank(nextOpenId)) {
			params.put("next_openid", nextOpenId);
		}

		JSONObject result = wechatCommService.get("/cgi-bin/user/get", token, params);
		System.out.println(result.toString());

		int totalCount = new Long(result.getLong("total")).intValue();
		int thisCount = new Long(result.getLong("count")).intValue();
		System.out.println("total:" + totalCount + "   count:" + thisCount);
		if (thisCount == 0) {
			return null;
		}

		if (result.containsKey("data")) {
			JSONObject data = result.getJSONObject("data");
			JSONArray openIds = data.getJSONArray("openid");
			for (int i = 0; i < openIds.size(); i++) {
				openIdList.add(openIds.getString(i));
			}
		}

		String newNextOpenId = result.getString("next_openid");
		return newNextOpenId;
	}

	public List<String> getOpenIds(String token) throws Exception {
		List<String> openIdList = new LinkedList<String>();

		String nextOpenId = null;
		do {
			nextOpenId = getOpenIds(token, nextOpenId, openIdList);
		} while (StringUtils.isNotBlank(nextOpenId));

		return openIdList;
	}

	public WxUser getUserInfo(String token, String openId) throws Exception {
		Map<String, String> params = new HashMap<String, String>();
		params.put("openid", openId);

		JSONObject result = wechatCommService.get("/cgi-bin/user/info", token, params);

		WxUser wxuser = new WxUser();

		/* 用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。 */
		wxuser.setIsSubscribe(result.containsKey("subscribe") && result.getString("subscribe").equals("1") ? true : false);

		/* 用户所在城市 */
		wxuser.setCity(result.containsKey("city") ? result.getString("city") : "");
		/* 用户所在省份 */
		wxuser.setProvince(result.containsKey("province") ? result.getString("province") : "");
		/* 用户所在国家 */
		wxuser.setCountry(result.containsKey("country") ? result.getString("country") : "");

		wxuser.setOpenId(result.containsKey("openid") ? result.getString("openid") : "");
		/* 只有在用户将公众号绑定到微信开放平台帐号后，才会出现该字段。 */
		wxuser.setUnionId(result.containsKey("unionid") ? result.getString("unionid") : "");
		/* 用户的昵称 */
		// wxuser.setNickName(result.containsKey("nickname") ? URLEncoder.encode(result.getString("nickname"), "UTF-8") : "");
		wxuser.setNickName(result.containsKey("nickname") ? result.getString("nickname") : "");
		/* 用户的性别，值为1时是男性，值为2时是女性，值为0时是未知 */
		wxuser.setSex(result.containsKey("sex") ? result.getString("sex") : "0");
		/* 用户的语言，简体中文为zh_CN */
		wxuser.setLanguage(result.containsKey("language") ? result.getString("language") : "");

		/* 用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。 */
		wxuser.setHeadImgUrl(result.containsKey("headimgurl") ? result.getString("headimgurl") : "");

		if (result.containsKey("subscribe_time")) {
			long subscribeTime = result.getLong("subscribe_time") * 1000;
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(subscribeTime);

			/* 用户关注日期，YYYYMMDD。如果用户曾多次关注，则取最后关注时间 */
			wxuser.setSubDate(DateUtils.getDate(cal.getTime(), DateUtils.YYYYMMDD));
			/* 用户关注时间，HHMMSS。如果用户曾多次关注，则取最后关注时间 */
			wxuser.setSubTime(DateUtils.getDate(cal.getTime(), DateUtils.HHMMSS));
		}

		/* 用户备注 */
		wxuser.setRemark(result.containsKey("remark") ? result.getString("remark") : "");

		return wxuser;
	}
}

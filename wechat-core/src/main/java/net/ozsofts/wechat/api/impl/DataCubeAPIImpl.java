package net.ozsofts.wechat.api.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.DataCubeAPI;
import net.ozsofts.wechat.api.response.ArticleSummaryResponse;
import net.ozsofts.wechat.api.response.ArticleTotalResponse;
import net.ozsofts.wechat.api.response.InterfaceSummaryHourResponse;
import net.ozsofts.wechat.api.response.InterfaceSummaryResponse;
import net.ozsofts.wechat.api.response.UpstreamMsgDistMonthResponse;
import net.ozsofts.wechat.api.response.UpstreamMsgDistResponse;
import net.ozsofts.wechat.api.response.UpstreamMsgDistWeekResponse;
import net.ozsofts.wechat.api.response.UpstreamMsgHourResponse;
import net.ozsofts.wechat.api.response.UpstreamMsgMonthResponse;
import net.ozsofts.wechat.api.response.UpstreamMsgResponse;
import net.ozsofts.wechat.api.response.UpstreamMsgWeekResponse;
import net.ozsofts.wechat.api.response.UserCumulateResponse;
import net.ozsofts.wechat.api.response.UserReadHourResponse;
import net.ozsofts.wechat.api.response.UserReadResponse;
import net.ozsofts.wechat.api.response.UserShareHourResponse;
import net.ozsofts.wechat.api.response.UserShareResponse;
import net.ozsofts.wechat.api.response.UserSummaryResponse;
import net.ozsofts.wechat.api.type.ArticleSummary;
import net.ozsofts.wechat.api.type.ArticleTotal;
import net.ozsofts.wechat.api.type.InterfaceSummary;
import net.ozsofts.wechat.api.type.InterfaceSummaryHour;
import net.ozsofts.wechat.api.type.UpstreamMsg;
import net.ozsofts.wechat.api.type.UpstreamMsgDist;
import net.ozsofts.wechat.api.type.UpstreamMsgDistMonth;
import net.ozsofts.wechat.api.type.UpstreamMsgDistWeek;
import net.ozsofts.wechat.api.type.UpstreamMsgHour;
import net.ozsofts.wechat.api.type.UpstreamMsgMonth;
import net.ozsofts.wechat.api.type.UpstreamMsgWeek;
import net.ozsofts.wechat.api.type.UserCumulate;
import net.ozsofts.wechat.api.type.UserRead;
import net.ozsofts.wechat.api.type.UserReadHour;
import net.ozsofts.wechat.api.type.UserShare;
import net.ozsofts.wechat.api.type.UserShareHour;
import net.ozsofts.wechat.api.type.UserSummary;

public class DataCubeAPIImpl extends BaseAPI implements DataCubeAPI {

	@Override
	public List<UserSummary> getUserSummary(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getusersummary", UserSummaryResponse.class);
		UserSummaryResponse response = JSON.parseObject(result.toJSONString(), UserSummaryResponse.class);
		return response.getList();
	}

	@Override
	public List<UserCumulate> getUserCumulate(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getusercumulate", UserCumulateResponse.class);
		UserCumulateResponse response = JSON.parseObject(result.toJSONString(), UserCumulateResponse.class);
		return response.getList();
	}

	@Override
	public List<ArticleSummary> getArticleSummary(String sysId, Date day) throws WechatException {
		JSONObject result = query(sysId, day, day, "/datacube/getarticlesummary", ArticleSummaryResponse.class);
		ArticleSummaryResponse response = JSON.parseObject(result.toJSONString(), ArticleSummaryResponse.class);
		return response.getList();
	}

	@Override
	public List<ArticleTotal> getArticleTotal(String sysId, Date day) throws WechatException {
		JSONObject result = query(sysId, day, day, "/datacube/getarticletotal", ArticleTotalResponse.class);
		ArticleTotalResponse response = JSON.parseObject(result.toJSONString(), ArticleTotalResponse.class);
		return response.getList();
	}

	@Override
	public List<UserRead> getUserRead(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getuserread", UserReadResponse.class);
		UserReadResponse response = JSON.parseObject(result.toJSONString(), UserReadResponse.class);
		return response.getList();
	}

	@Override
	public List<UserReadHour> getUserReadHour(String sysId, Date day) throws WechatException {
		JSONObject result = query(sysId, day, day, "/datacube/getuserreadhour", UserReadHourResponse.class);
		UserReadHourResponse response = JSON.parseObject(result.toJSONString(), UserReadHourResponse.class);
		return response.getList();
	}

	@Override
	public List<UserShare> getUserShare(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getusershare", UserShareResponse.class);
		UserShareResponse response = JSON.parseObject(result.toJSONString(), UserShareResponse.class);
		return response.getList();
	}

	@Override
	public List<UserShareHour> getUserShareHour(String sysId, Date day) throws WechatException {
		JSONObject result = query(sysId, day, day, "/datacube/getusersharehour", UserShareHourResponse.class);
		UserShareHourResponse response = JSON.parseObject(result.toJSONString(), UserShareHourResponse.class);
		return response.getList();
	}

	@Override
	public List<UpstreamMsg> getUpstreamMsg(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getupstreammsg", UpstreamMsgResponse.class);
		UpstreamMsgResponse response = JSON.parseObject(result.toJSONString(), UpstreamMsgResponse.class);
		return response.getList();
	}

	@Override
	public List<UpstreamMsgHour> getUpstreamMsgHour(String sysId, Date day) throws WechatException {
		JSONObject result = query(sysId, day, day, "/datacube/getupstreammsghour", UpstreamMsgHourResponse.class);
		UpstreamMsgHourResponse response = JSON.parseObject(result.toJSONString(), UpstreamMsgHourResponse.class);
		return response.getList();
	}

	@Override
	public List<UpstreamMsgWeek> getUpstreamMsgWeek(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getupstreammsgweek", UpstreamMsgWeekResponse.class);
		UpstreamMsgWeekResponse response = JSON.parseObject(result.toJSONString(), UpstreamMsgWeekResponse.class);
		return response.getList();
	}

	@Override
	public List<UpstreamMsgMonth> getUpstreamMsgMonth(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getupstreammsgmonth", UpstreamMsgMonthResponse.class);
		UpstreamMsgMonthResponse response = JSON.parseObject(result.toJSONString(), UpstreamMsgMonthResponse.class);
		return response.getList();
	}

	@Override
	public List<UpstreamMsgDist> getUpstreamMsgDist(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getupstreammsgdist", UpstreamMsgDistResponse.class);
		UpstreamMsgDistResponse response = JSON.parseObject(result.toJSONString(), UpstreamMsgDistResponse.class);
		return response.getList();
	}

	@Override
	public List<UpstreamMsgDistWeek> getUpstreamMsgDistWeek(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getupstreammsgdistweek", UpstreamMsgDistWeekResponse.class);
		UpstreamMsgDistWeekResponse response = JSON.parseObject(result.toJSONString(), UpstreamMsgDistWeekResponse.class);
		return response.getList();
	}

	@Override
	public List<UpstreamMsgDistMonth> getUpstreamMsgDistMonth(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getupstreammsgdistmonth", UpstreamMsgDistMonthResponse.class);
		UpstreamMsgDistMonthResponse response = JSON.parseObject(result.toJSONString(), UpstreamMsgDistMonthResponse.class);
		return response.getList();
	}

	@Override
	public List<InterfaceSummary> getInterfaceSummary(String sysId, Date beginDate, Date endDate) throws WechatException {
		JSONObject result = query(sysId, beginDate, endDate, "/datacube/getinterfacesummary", InterfaceSummaryResponse.class);
		InterfaceSummaryResponse response = JSON.parseObject(result.toJSONString(), InterfaceSummaryResponse.class);
		return response.getList();
	}

	@Override
	public List<InterfaceSummaryHour> getInterfaceSummaryHour(String sysId, Date day) throws WechatException {
		JSONObject result = query(sysId, day, day, "/datacube/getinterfacesummaryhour", InterfaceSummaryHourResponse.class);
		InterfaceSummaryHourResponse response = JSON.parseObject(result.toJSONString(), InterfaceSummaryHourResponse.class);
		return response.getList();
	}

	private JSONObject query(String sysId, Date beginDate, Date endDate, String url, Class<?> clazz) throws WechatException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("begin_date", new DateTime(beginDate).toString("yyyy-MM-dd"));
		params.put("end_date", new DateTime(endDate).toString("yyyy-MM-dd"));

		JSONObject result = get(sysId, url, params);
		result.put(JSON.DEFAULT_TYPE_KEY, clazz);

		return result;
	}
}

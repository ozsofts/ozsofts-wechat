package net.ozsofts.wechat.api;

import java.util.Date;
import java.util.List;

import net.ozsofts.wechat.WechatException;
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

/**
 * 数据分析API
 *
 * @author jack
 */
public interface DataCubeAPI {

	/**
	 * 获取用户增减数据，最大跨度为7天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 用户增减数据
	 */
	public List<UserSummary> getUserSummary(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取累计用户数据，最大跨度为7天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 用户增减数据
	 */
	public List<UserCumulate> getUserCumulate(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取图文群发每日数据
	 *
	 * @param day
	 *            查询日期
	 * @return 图文群发每日数据
	 */
	public List<ArticleSummary> getArticleSummary(String sysId, Date day) throws WechatException;

	/**
	 * 获取图文群发总数据
	 *
	 * @param day
	 *            查询日期
	 * @return 图文群发总数据
	 */
	public List<ArticleTotal> getArticleTotal(String sysId, Date day) throws WechatException;

	/**
	 * 获取图文统计数据，最大跨度为3天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 图文统计数据
	 */
	public List<UserRead> getUserRead(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取图文统计分时数据
	 *
	 * @param day
	 *            查询日期
	 * @return 图文统计分时数据
	 */
	public List<UserReadHour> getUserReadHour(String sysId, Date day) throws WechatException;

	/**
	 * 获取图文分享转发数据，最大跨度为7天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 图文分享转发数据
	 */
	public List<UserShare> getUserShare(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取图文分享转发分时数据
	 *
	 * @param day
	 *            查询日期
	 * @return 图文分享转发分时数据
	 */
	public List<UserShareHour> getUserShareHour(String sysId, Date day) throws WechatException;

	/**
	 * 获取消息发送概况数据，最大跨度为7天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 消息发送概况数据
	 */
	public List<UpstreamMsg> getUpstreamMsg(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取消息分送分时数据
	 *
	 * @param day
	 *            查询日期
	 * @return 消息分送分时数据
	 */
	public List<UpstreamMsgHour> getUpstreamMsgHour(String sysId, Date day) throws WechatException;

	/**
	 * 获取消息发送周数据，最大跨度为30天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 消息发送周数据
	 */
	public List<UpstreamMsgWeek> getUpstreamMsgWeek(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取消息发送月数据，最大跨度为30天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 消息发送月数据
	 */
	public List<UpstreamMsgMonth> getUpstreamMsgMonth(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取消息发送分布数据，最大跨度为15天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 消息发送分布数据
	 */
	public List<UpstreamMsgDist> getUpstreamMsgDist(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取消息发送分布周数据，最大跨度为30天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 消息发送分布周数据
	 */
	public List<UpstreamMsgDistWeek> getUpstreamMsgDistWeek(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取消息发送分布月数据，最大跨度为30天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 消息发送分布月数据
	 */
	public List<UpstreamMsgDistMonth> getUpstreamMsgDistMonth(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取接口分析数据，最大跨度为30天
	 *
	 * @param beginDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return 接口分析数据
	 */
	public List<InterfaceSummary> getInterfaceSummary(String sysId, Date beginDate, Date endDate) throws WechatException;

	/**
	 * 获取接口分析分时数据
	 *
	 * @param day
	 *            查询日期
	 * @return 接口分析分时数据
	 */
	public List<InterfaceSummaryHour> getInterfaceSummaryHour(String sysId, Date day) throws WechatException;
}

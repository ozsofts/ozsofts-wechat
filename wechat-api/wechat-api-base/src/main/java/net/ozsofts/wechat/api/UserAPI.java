package net.ozsofts.wechat.api;

import java.util.List;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.type.response.UserInfo;

/**
 * 用户管理API
 * 
 * @author jack
 */
public interface UserAPI {

	/**
	 * 获取关注者列表
	 *
	 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140840&token=&lang=zh_CN}
	 * 
	 * @param nextOpenid
	 *            下一个用户的ID
	 * @return 关注者列表对象
	 */
	public List<String> getUsers(String sysId, String nextOpenId) throws WechatException;

	/**
	 * 设置关注者备注
	 * 
	 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140838&token=&lang=zh_CN}
	 * 
	 * @param openid
	 *            关注者ID
	 * @param remark
	 *            备注内容
	 */
	public void updateRemark(String sysId, String openid, String remark) throws WechatException;

	/**
	 * 获取关注者信息
	 *
	 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN}
	 * 
	 * @param openid
	 *            关注者ID
	 * @return 关注者信息对象
	 */
	public UserInfo getUserInfo(String sysId, String openid) throws WechatException;

	/**
	 * 批量获取用户基本信息。最多支持一次拉取100条。
	 *
	 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140839&token=&lang=zh_CN}
	 * 
	 * @param openIds
	 *            关注者ID列表
	 * @return 关注者信息对象列表
	 */
	public List<UserInfo> getUserInfoList(String sysId, List<String> openIds) throws WechatException;

	/**
	 * 批量为用户打上标签 标签功能目前支持公众号为用户打上最多三个标签。
	 * 
	 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN}
	 * 
	 * @param openidList
	 *            用户openid列表
	 * @param tagId
	 *            标签ID
	 */
	public void batchTagsToUser(String sysId, List<String> openIds, int tagId) throws WechatException;

	/**
	 * 批量为用户取消标签
	 * 
	 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN}
	 * 
	 * @param openidList
	 *            用户openid列表
	 * @param tagId
	 *            标签ID
	 * @return 结果
	 */
	public void batchDeleteTagsToUser(String sysId, List<String> openIds, int tagId) throws WechatException;

	/**
	 * <p>
	 * 取得用户身上的所有标签
	 * 
	 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN}
	 * 
	 * @param openId
	 * @return 返回标签ID的列表
	 */
	public List<String> getUserTags(String sysId, String openId) throws WechatException;
}
package net.ozsofts.wechat.api;

import java.util.List;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.type.response.TagInfo;

/**
 * 标签相关
 * 
 * @author jack
 */
public interface TagAPI {

	/**
	 * 创建标签
	 * 
	 * @param tagName
	 *            标签名称
	 * @return 创建的标签结果
	 */
	public TagInfo create(String sysId, String tagName) throws WechatException;

	/**
	 * 获取已经创建的标签
	 * 
	 * @return 已经创建的标签列表
	 */
	public List<TagInfo> getAll(String sysId) throws WechatException;

	/**
	 * 编辑标签
	 * 
	 * @param tagId
	 *            标签ID
	 * @param newTagName
	 *            新的标签名称
	 */
	public void update(String sysId, int tagId, String newTagName) throws WechatException;

	/**
	 * 删除标签
	 * 
	 * @param tagId
	 *            需要删除的标签ID
	 * @return 删除结果
	 */
	public void delete(String sysId, int tagId) throws WechatException;

	/**
	 * 获取标签下粉丝
	 * 
	 * @param tagId
	 *            标签ID
	 * @param nextOpenid
	 *            下一个用户的openid,第一次不用填
	 * @return 本批用户列表
	 */
	public List<String> getUsersByTagId(String sysId, int tagId, String nextOpenid) throws WechatException;
}

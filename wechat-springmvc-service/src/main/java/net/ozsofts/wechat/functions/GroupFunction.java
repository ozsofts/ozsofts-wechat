package net.ozsofts.wechat.functions;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxGroup;

public interface GroupFunction {

	//
	// 对分组进行管理的方法
	//
	/**
	 * <p>
	 * 创建分组
	 * 
	 * @param name
	 *            新创建的分组的名称
	 */
	public WxGroup createGroup(String token, String name) throws Exception;

	/**
	 * <p>
	 * 修改分组的名称
	 * 
	 * @param token
	 * @param groupId
	 *            分组微信标识号
	 * @param newName
	 *            新的名称
	 * @throws Exception
	 */
	public void changeGroupName(String token, int groupId, String newName) throws Exception;

	/**
	 * <p>
	 * 删除分组
	 * 
	 * @param token
	 * @param groupId
	 *            分组微信标识号
	 * @throws Exception
	 */
	public void deleteGroup(String token, int groupId) throws Exception;

	/**
	 * <p>
	 * 修改用户的分组
	 * 
	 * @param token
	 * @param openId
	 * @param groupId
	 *            分组微信标识号
	 * @throws Exception
	 */
	public void changeUserGroup(String token, String openId, int groupId) throws Exception;

	/**
	 * <p>
	 * 批量修改用户分组
	 * 
	 * @param token
	 * @param openIds
	 * @param groupId
	 *            分组微信标识号
	 * @throws Exception
	 */
	public void batchChangeUserGroup(String token, String[] openIds, int groupId) throws Exception;

	/**
	 * <p>
	 * 取得公众号所有的用户组信息
	 */
	public List<WxGroup> getAllGroups(String token) throws Exception;
}

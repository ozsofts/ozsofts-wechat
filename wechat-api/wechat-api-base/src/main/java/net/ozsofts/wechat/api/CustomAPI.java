package net.ozsofts.wechat.api;

import java.io.InputStream;
import java.util.List;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.type.response.KFInfo;
import net.ozsofts.wechat.message.resp.BaseMessage;

/**
 * 客服相关API
 *
 * @author jack
 */
public interface CustomAPI {
	/**
	 * 发布客服消息
	 *
	 * @param openid
	 *            关注者ID
	 * @param message
	 *            消息对象，支持各种消息类型
	 * @return 调用结果
	 */
	public void sendMessage(String sysId, String openId, BaseMessage message) throws WechatException;

	/**
	 * 添加客服帐号
	 *
	 * @param customAccount
	 *            客服对象
	 * @return 添加结果
	 */
	public void addCustomAccount(String sysId, String kfAccount, String nickname) throws WechatException;

	/**
	 * <p>
	 * 邀请绑定客服帐号
	 * 
	 * <p>
	 * 新添加的客服帐号是不能直接使用的，只有客服人员用微信号绑定了客服账号后，方可登录Web客服进行操作。<br/>
	 * 此接口发起一个绑定邀请到客服人员微信号，客服人员需要在微信客户端上用该微信号确认后帐号才可用。<br/>
	 * 尚未绑定微信号的帐号可以进行绑定邀请操作，邀请未失效时不能对该帐号进行再次绑定微信号邀请。
	 * 
	 * @param kfAccount
	 * @param inviteWx
	 */
	public void inviteWorker(String sysId, String kfAccount, String inviteWx) throws WechatException;

	/**
	 * 修改客服帐号信息
	 *
	 * @param customAccount
	 *            客服帐号信息
	 * @return 修改结果
	 */
	public void updateCustomAccount(String sysId, String kfAccount, String nicknamet) throws WechatException;

	/**
	 * 删除客服帐号
	 * 
	 * @param accountName
	 *            客服帐号名
	 * @return 删除结果
	 */
	public void deleteCustomAccount(String sysId, String kfAccount) throws WechatException;

	/**
	 * 设置客服帐号头像
	 *
	 * @param accountName
	 *            客服帐号名
	 * @param file
	 *            头像文件
	 * @return 设置结果
	 */
	public void uploadHeadImg(String sysId, String kfAccount, InputStream headImgData) throws WechatException;

	/**
	 * 获取所有客服帐号信息
	 * 
	 * @return 所有客服帐号信息对象
	 */
	public List<KFInfo> getCustomAccountList(String sysId) throws WechatException;
}

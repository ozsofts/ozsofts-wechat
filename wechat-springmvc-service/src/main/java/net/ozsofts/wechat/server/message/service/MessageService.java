package net.ozsofts.wechat.server.message.service;

import net.ozsofts.wechat.core.entity.WxAccount;

/**
 * <p>
 * 对所有上行的信息进行保存
 * 
 * @author jack
 */
public interface MessageService {
	/**
	 * <p>
	 * 对上行消息进行处理
	 * 
	 * @param WxAccount
	 * @param reqdata
	 * @return
	 */
	public String handleMessage(WxAccount WxAccount, String reqdata);

	// /**
	// * <p>
	// * 保存上行消息
	// *
	// * @param account
	// * @param recvMessage
	// * @return
	// * @throws Exception
	// */
	// public WxMessage save(WxAccount account, ReceiveMessage recvMessage) throws Exception;
	//
	// /**
	// * <p>
	// * 对系统层面的关键字消息进行处理
	// *
	// * @param account
	// * @param recvMessage
	// * @return
	// */
	// public ResponseMessage handleKeyword(WxAccount account, ReceiveMessage recvMessage);
	//
	// public ResponseMessage matcheResponseMessage(long accountId, String content, List<WxRule> ruleList);
}

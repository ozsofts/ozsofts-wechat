package net.ozsofts.wechat.core.manager;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxRespMessage;
import net.ozsofts.wechat.core.message.ResponseMessage;

/**
 * Description : Manager接口 WxRespMessageManager <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

public interface WxRespMessageManager {

	public int addWxRespMessage(WxRespMessage wxRespMessage);

	public int updateWxRespMessage(WxRespMessage wxRespMessage);

	public int deleteWxRespMessage(Long id);

	public int batchDeleteWxRespMessage(Long[] ids);

	public WxRespMessage getWxRespMessage(Long id);

	public List<WxRespMessage> getAllWxRespMessage();

	public WxRespMessage findOne(long accountId, String ruleType, String refType, long refId, boolean auto);

	public ResponseMessage toResponseMessage(WxRespMessage wxRespMessage);

}

package net.ozsofts.wechat.core.manager.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxRespMessage;
import net.ozsofts.wechat.core.entity.WxRule;
import net.ozsofts.wechat.core.manager.WxRespMessageManager;
import net.ozsofts.wechat.core.message.ResponseImageMessage;
import net.ozsofts.wechat.core.message.ResponseMessage;
import net.ozsofts.wechat.core.message.ResponseMusicMessage;
import net.ozsofts.wechat.core.message.ResponseTextMessage;
import net.ozsofts.wechat.core.message.ResponseVideoMessage;
import net.ozsofts.wechat.core.message.ResponseVoiceMessage;

/**
 * Description : Manager实现类 WxRespMessageManagerImpl <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

@Service
public class WxRespMessageManagerImpl implements WxRespMessageManager {

	@Resource(name = "wxRespMessageDao")
	private Dao<WxRespMessage> wxRespMessageDao;

	@Override
	public int addWxRespMessage(WxRespMessage wxRespMessage) {
		int rows = wxRespMessageDao.save(wxRespMessage);
		return rows;
	}

	@Override
	public int updateWxRespMessage(WxRespMessage wxRespMessage) {
		int rows = wxRespMessageDao.dynamicUpdate(wxRespMessage);
		return rows;
	}

	@Override
	public int deleteWxRespMessage(Long id) {
		int rows = wxRespMessageDao.delete(id);
		return rows;
	}

	@Override
	public int batchDeleteWxRespMessage(Long[] ids) {
		int rows = wxRespMessageDao.batchDelete(ids);
		return rows;
	}

	@Override
	public WxRespMessage getWxRespMessage(Long id) {
		WxRespMessage wxRespMessage = wxRespMessageDao.get(id);
		return wxRespMessage;
	}

	@Override
	public List<WxRespMessage> getAllWxRespMessage() {
		List<WxRespMessage> list = wxRespMessageDao.findAll();
		return list;
	}

	public WxRespMessage findOne(long accountId, String ruleType, String refType, long refId, boolean auto) {
		StringBuilder sql = new StringBuilder(
				"select m.* from tbl_wx_rules r inner join tbl_wx_resp_messages m on r.resp_message_id=m.id where r.account_id=? and r.rule_type=? and r.ref_type=?");

		List<Object> params = new ArrayList<Object>();
		params.add(accountId);
		params.add(StringUtils.isNotBlank(ruleType) ? ruleType : WxRule.RULE_TYPE_AUTO);
		params.add(StringUtils.isBlank(refType) ? "" : refType);
		if (StringUtils.isNotBlank(refType) && refId > 0l) {
			sql.append(" and ref_id=?");
			params.add(refId);
		}
		sql.append(" limit 1");

		WxRespMessage wxrespmsg = wxRespMessageDao.findUnique(sql.toString(), params.toArray());

		// 如果需要自动回复，则读取自动回复的定义
		if (wxrespmsg == null && auto) {
			wxrespmsg = findOne(accountId, WxRule.RULE_TYPE_AUTO, refType, refId, false);
		}

		return wxrespmsg;
	}

	public ResponseMessage toResponseMessage(WxRespMessage wxRespMessage) {
		ResponseMessage respMessage = null;

		String msgType = wxRespMessage.getMsgType();
		if (ResponseMessage.MSGTYPE_TEXT.equals(msgType)) {
			// 文本消息
			ResponseTextMessage textMessage = new ResponseTextMessage();
			textMessage.setContent(wxRespMessage.getContent());
			respMessage = textMessage;
		} else if (ResponseMessage.MSGTYPE_IMAGE.equals(msgType)) {
			// 图片消息
			ResponseImageMessage voiceMessage = new ResponseImageMessage();
			voiceMessage.setMediaId(wxRespMessage.getMediaId());
			respMessage = voiceMessage;
		} else if (ResponseMessage.MSGTYPE_VOICE.equals(msgType)) {
			// 语音消息
			ResponseVoiceMessage voiceMessage = new ResponseVoiceMessage();
			voiceMessage.setMediaId(wxRespMessage.getMediaId());
			respMessage = voiceMessage;
		} else if (ResponseMessage.MSGTYPE_VIDEO.equals(msgType)) {
			// 视频消息
			ResponseVideoMessage videoMessage = new ResponseVideoMessage();
			videoMessage.setTitle(wxRespMessage.getTitle());
			videoMessage.setDescription(wxRespMessage.getDescription());
			videoMessage.setMediaId(wxRespMessage.getMediaId());
			videoMessage.setThumbMediaId(wxRespMessage.getThumbMediaId());
			respMessage = videoMessage;
		} else if (ResponseMessage.MSGTYPE_MUSIC.equals(msgType)) {
			// 音乐消息
			ResponseMusicMessage musicMessage = new ResponseMusicMessage();
			musicMessage.setTitle(wxRespMessage.getTitle());
			musicMessage.setDescription(wxRespMessage.getDescription());
			musicMessage.setMusicUrl(wxRespMessage.getMusicUrl());
			musicMessage.setHqMusicUrl(wxRespMessage.getHqMusicUrl());
			musicMessage.setThumbMediaId(wxRespMessage.getThumbMediaId());
			respMessage = musicMessage;
		}

		return respMessage;
	}
}

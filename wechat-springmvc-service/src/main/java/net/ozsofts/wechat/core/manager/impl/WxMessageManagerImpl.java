package net.ozsofts.wechat.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxMessage;
import net.ozsofts.wechat.core.manager.WxMessageManager;
import net.ozsofts.wechat.core.message.ResponseImageMessage;
import net.ozsofts.wechat.core.message.ResponseMessage;
import net.ozsofts.wechat.core.message.ResponseMusicMessage;
import net.ozsofts.wechat.core.message.ResponseTextMessage;
import net.ozsofts.wechat.core.message.ResponseVideoMessage;

/**
 * Description : Manager实现类 WxMessageManagerImpl <br>
 * <br>
 * Time : 2016/01/22 15:12
 */

@Service
public class WxMessageManagerImpl implements WxMessageManager {

	@Resource(name = "wxMessageDao")
	private Dao<WxMessage> wxMessageDao;

	@Override
	public int addWxMessage(WxMessage wxMessage) {
		int rows = wxMessageDao.save(wxMessage);
		return rows;
	}

	@Override
	public int updateWxMessage(WxMessage wxMessage) {
		int rows = wxMessageDao.dynamicUpdate(wxMessage);
		return rows;
	}

	@Override
	public int deleteWxMessage(Long id) {
		int rows = wxMessageDao.delete(id);
		return rows;
	}

	@Override
	public int batchDeleteWxMessage(Long[] ids) {
		int rows = wxMessageDao.batchDelete(ids);
		return rows;
	}

	@Override
	public WxMessage getWxMessage(Long id) {
		WxMessage wxMessage = wxMessageDao.get(id);
		return wxMessage;
	}

	@Override
	public List<WxMessage> getAllWxMessage() {
		List<WxMessage> list = wxMessageDao.findAll();
		return list;
	}

	public WxMessage findByMsgid(String msgId) {
		return wxMessageDao.findUniqueByProperty("msg_id", msgId);
	}

	public void saveResponseMessage(long accountId, ResponseMessage respMessage) {
		WxMessage wxmsg = new WxMessage();

		String msgType = respMessage.getMsgType();

		wxmsg.setToUser(respMessage.getToUserName());
		wxmsg.setFromUser(respMessage.getFromUserName());
		wxmsg.setMsgType(msgType);

		DateTime dt = new DateTime();
		wxmsg.setMsgDate(dt.toString("yyyy-MM-dd"));
		wxmsg.setMsgTime(dt.toString("HH:mm:ss"));

		if (ResponseMessage.MSGTYPE_TEXT.equals(msgType)) {
			wxmsg.setContent(((ResponseTextMessage) respMessage).getContent());
		} else if (ResponseMessage.MSGTYPE_IMAGE.equals(msgType) || ResponseMessage.MSGTYPE_VOICE.equals(msgType)) {
			wxmsg.setMediaId(((ResponseImageMessage) respMessage).getMediaId());
		} else if (ResponseMessage.MSGTYPE_VIDEO.equals(msgType)) {
			wxmsg.setTitle(((ResponseVideoMessage) respMessage).getTitle());
			wxmsg.setDescription(((ResponseVideoMessage) respMessage).getDescription());
			wxmsg.setMediaId(((ResponseVideoMessage) respMessage).getMediaId());
			wxmsg.setThumbMediaId(((ResponseVideoMessage) respMessage).getThumbMediaId());
		} else if (ResponseMessage.MSGTYPE_MUSIC.equals(msgType)) {
			wxmsg.setTitle(((ResponseMusicMessage) respMessage).getTitle());
			wxmsg.setDescription(((ResponseMusicMessage) respMessage).getDescription());
			wxmsg.setMusicUrl(((ResponseMusicMessage) respMessage).getMusicUrl());
			wxmsg.setHqMusicUrl(((ResponseMusicMessage) respMessage).getHqMusicUrl());
			wxmsg.setThumbMediaId(((ResponseMusicMessage) respMessage).getThumbMediaId());
		} else if (ResponseMessage.MSGTYPE_NEWS.equals(msgType)) {
			// 暂不支持图文信息下传
		}
		wxmsg.setAccountId(accountId);
		wxMessageDao.save(wxmsg);
	}
}

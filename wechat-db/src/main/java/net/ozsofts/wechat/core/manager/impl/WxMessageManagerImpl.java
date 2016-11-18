package net.ozsofts.wechat.core.manager.impl;

import java.util.List;

import javax.annotation.Resource;

import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxMessage;
import net.ozsofts.wechat.core.manager.WxMessageManager;
import net.ozsofts.wechat.message.req.RequestType;
import net.ozsofts.wechat.message.resp.BaseMessage;
import net.ozsofts.wechat.message.resp.ImageMessage;
import net.ozsofts.wechat.message.resp.TextMessage;
import net.ozsofts.wechat.message.resp.VideoMessage;

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

	public void saveResponseMessage(long accountId, BaseMessage respMessage) {
		WxMessage wxmsg = new WxMessage();

		String msgType = respMessage.getMsgType();

		wxmsg.setToUser(respMessage.getToUserName());
		wxmsg.setFromUser(respMessage.getFromUserName());
		wxmsg.setMsgType(msgType);

		DateTime dt = new DateTime();
		wxmsg.setMsgDate(dt.toString("yyyy-MM-dd"));
		wxmsg.setMsgTime(dt.toString("HH:mm:ss"));

		if (RequestType.TEXT.equals(msgType)) {
			wxmsg.setContent(((TextMessage) respMessage).getContent());
		} else if (RequestType.IMAGE.equals(msgType) || RequestType.VOICE.equals(msgType)) {
			wxmsg.setMediaId(((ImageMessage) respMessage).getMediaId());
		} else if (RequestType.VIDEO.equals(msgType)) {
			wxmsg.setTitle(((VideoMessage) respMessage).getTitle());
			wxmsg.setDescription(((VideoMessage) respMessage).getDescription());
			wxmsg.setMediaId(((VideoMessage) respMessage).getMediaId());
			wxmsg.setThumbMediaId(((VideoMessage) respMessage).getThumbMediaId());
			// } else if (RequestType.MUSIC.equals(msgType)) {
			// wxmsg.setTitle(((MusicMessage) respMessage).getTitle());
			// wxmsg.setDescription(((MusicMessage) respMessage).getDescription());
			// wxmsg.setMusicUrl(((MusicMessage) respMessage).getMusicUrl());
			// wxmsg.setHqMusicUrl(((MusicMessage) respMessage).getHqMusicUrl());
			// wxmsg.setThumbMediaId(((MusicMessage) respMessage).getThumbMediaId());
		}
		wxmsg.setAccountId(accountId);
		wxMessageDao.save(wxmsg);
	}
}

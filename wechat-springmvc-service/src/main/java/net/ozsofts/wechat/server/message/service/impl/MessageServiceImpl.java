package net.ozsofts.wechat.server.message.service.impl;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.InputSource;

import net.ozsofts.core.utils.StringUtils;
import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.entity.WxMessage;
import net.ozsofts.wechat.core.entity.WxXmlmessage;
import net.ozsofts.wechat.core.manager.WxMessageManager;
import net.ozsofts.wechat.core.manager.WxXmlmessageManager;
import net.ozsofts.wechat.core.message.ReceiveImageMessage;
import net.ozsofts.wechat.core.message.ReceiveLinkMessage;
import net.ozsofts.wechat.core.message.ReceiveLocationMessage;
import net.ozsofts.wechat.core.message.ReceiveMessage;
import net.ozsofts.wechat.core.message.ReceiveTextMessage;
import net.ozsofts.wechat.core.message.ReceiveVideoMessage;
import net.ozsofts.wechat.core.message.ReceiveVoiceMessage;
import net.ozsofts.wechat.server.message.handler.MessageHandler;
import net.ozsofts.wechat.server.message.service.MessageService;

/**
 * <p>
 * 对所有上行的信息进行保存
 * 
 * @author jack
 */
public class MessageServiceImpl implements MessageService {
	private static Logger log = LoggerFactory.getLogger(MessageServiceImpl.class);

	@Autowired
	private WxMessageManager wxMessageManager;
	@Autowired
	private WxXmlmessageManager wxXmlmessageManager;

	// @Autowired
	// private WxRespMessageManager wxRespMessageManager;
	// @Autowired
	// private WxRuleManager wxRuleManager;

	private Map<String, Class<?>> receiveMessageMap = new HashMap<String, Class<?>>();

	private List<MessageHandler> messageHandlerList = new LinkedList<MessageHandler>();

	public void initialize() throws Exception {
		String packageName = ReceiveMessage.class.getPackage().getName();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_TEXT, classLoader.loadClass(packageName + ".ReceiveTextMessage"));
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_IMAGE, classLoader.loadClass(packageName + ".ReceiveImageMessage"));
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_VOICE, classLoader.loadClass(packageName + ".ReceiveVoiceMessage"));
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_VIDEO, classLoader.loadClass(packageName + ".ReceiveVideoMessage"));
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_SHORTVIDEO, classLoader.loadClass(packageName + ".ReceiveShortvideoMessage"));
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_LOCATION, classLoader.loadClass(packageName + ".ReceiveLocationMessage"));
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_LINK, classLoader.loadClass(packageName + ".ReceiveLinkMessage"));
		receiveMessageMap.put(ReceiveMessage.MSGTYPE_EVENT, classLoader.loadClass(packageName + ".ReceiveEventMessage"));
	}

	public String handleMessage(WxAccount account, String reqdata) {
		Map<String, String> msgData = parseRequestXml(reqdata);

		// 保存原始上行数据
		WxXmlmessage xmlmsg = new WxXmlmessage();
		xmlmsg.setAccountId(account.getId());
		xmlmsg.setXml(reqdata);
		wxXmlmessageManager.addWxXmlmessage(xmlmsg);

		String msgType = msgData.get("MsgType");

		try {
			Class<?> clazz = receiveMessageMap.get(msgType);
			if (clazz == null) {
				log.error("[{}] 处理消息时对应的类型[{}]没有解析器!", account.getSystemId(), msgType);
				return "";
			}

			ReceiveMessage receiveMessage = (ReceiveMessage) clazz.newInstance();
			// 对上行的消息进行解析
			receiveMessage.parse(msgData);

			// 保存解析后的上行消息信息
			save(account, receiveMessage);

			// 实际对消息进行处理
			for (MessageHandler handler : messageHandlerList) {
				handler.handle(account, receiveMessage);
			}
		} catch (Exception e) {
			log.error("[{}] 处理消息时出现错误。", account.getSystemId(), e);
			return "";
		}

		// 回复此字符串表示服务器已经处理，微信不需要再重发相同的消息
		return "success";
	}

	@SuppressWarnings("rawtypes")
	private Map<String, String> parseRequestXml(String reqdata) {
		Map<String, String> values = new HashMap<String, String>();

		try {
			SAXReader reader = new SAXReader();
			StringReader sr = new StringReader(reqdata);
			InputSource is = new InputSource(sr);
			Document document = reader.read(is);
			Element rootElement = document.getRootElement();

			for (Iterator iter = rootElement.elementIterator(); iter.hasNext();) {
				Element el = (Element) iter.next();

				String name = el.getName();
				String value = el.getTextTrim();

				values.put(name, value);
			}
		} catch (Exception ex) {
		}

		return values;
	}

	/**
	 * <p>
	 * 保存上行消息
	 * 
	 * @param account
	 * @param recvMessage
	 * @return
	 * @throws Exception
	 */
	private WxMessage save(WxAccount account, ReceiveMessage recvMessage) throws Exception {
		if (ReceiveMessage.MSGTYPE_EVENT.equals(recvMessage.getMsgType())) {
			return null;
		}

		WxMessage message = wxMessageManager.findByMsgid(recvMessage.getMsgId());
		if (message != null) {
			// 微信会有重发机制，在这种情况下，不需要再进行保存
			return message;
		}

		message = new WxMessage();
		message.setToUser(recvMessage.getToUserName());
		message.setFromUser(recvMessage.getFromUserName());

		message.setMsgDate(recvMessage.getCreateTime().substring(0, 8));
		message.setMsgTime(recvMessage.getCreateTime().substring(8));
		message.setMsgType(recvMessage.getMsgType());
		message.setMsgId(recvMessage.getMsgId());
		message.setDirection("RECV");

		if (ReceiveMessage.MSGTYPE_TEXT.equals(recvMessage.getMsgType())) {
			message.setContent(((ReceiveTextMessage) recvMessage).getContent());
		} else if (ReceiveMessage.MSGTYPE_IMAGE.equals(recvMessage.getMsgType())) {
			message.setPicUrl(((ReceiveImageMessage) recvMessage).getPicUrl());
			message.setMediaId(((ReceiveImageMessage) recvMessage).getMediaId());
		} else if (ReceiveMessage.MSGTYPE_VOICE.equals(recvMessage.getMsgType())) {
			message.setFormat(((ReceiveVoiceMessage) recvMessage).getFormat());
			message.setMediaId(((ReceiveVoiceMessage) recvMessage).getMediaId());
		} else if (ReceiveMessage.MSGTYPE_VIDEO.equals(recvMessage.getMsgType())
				|| ReceiveMessage.MSGTYPE_SHORTVIDEO.equals(recvMessage.getMsgType())) {
			message.setMediaId(((ReceiveVideoMessage) recvMessage).getMediaId());
			message.setThumbMediaId(((ReceiveVideoMessage) recvMessage).getThumbMediaId());
		} else if (ReceiveMessage.MSGTYPE_LOCATION.equals(recvMessage.getMsgType())) {
			message.setLocationX(((ReceiveLocationMessage) recvMessage).getLocationX());
			message.setLocationY(((ReceiveLocationMessage) recvMessage).getLocationY());
			message.setScale(((ReceiveLocationMessage) recvMessage).getScale());
			message.setLabel(((ReceiveLocationMessage) recvMessage).getLabel());
		} else if (ReceiveMessage.MSGTYPE_LINK.equals(recvMessage.getMsgType())) {
			message.setTitle(((ReceiveLinkMessage) recvMessage).getTitle());
			message.setDescription(((ReceiveLinkMessage) recvMessage).getDescription());
			message.setUrl(((ReceiveLinkMessage) recvMessage).getUrl());
		}

		message.setAccountId(account.getId());

		// TODO 对媒介进行处理
		String mediaId = message.getMediaId();
		if (StringUtils.isNotBlank(mediaId)) {
		}

		mediaId = message.getThumbMediaId();
		if (StringUtils.isNotBlank(mediaId)) {
		}

		wxMessageManager.addWxMessage(message);
		return message;
	}

	//
	// BEAN方法
	//
	public void setMessageHandlerList(List<MessageHandler> messageHandlerList) {
		this.messageHandlerList = messageHandlerList;
	}

}

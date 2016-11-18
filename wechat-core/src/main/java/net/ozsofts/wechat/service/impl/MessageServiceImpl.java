package net.ozsofts.wechat.service.impl;

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
import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.core.entity.WxMessage;
import net.ozsofts.wechat.core.entity.WxXmlmessage;
import net.ozsofts.wechat.core.manager.WxMessageManager;
import net.ozsofts.wechat.core.manager.WxXmlmessageManager;
import net.ozsofts.wechat.handler.MessageHandler;
import net.ozsofts.wechat.message.req.EventType;
import net.ozsofts.wechat.message.req.ImageMessage;
import net.ozsofts.wechat.message.req.LinkMessage;
import net.ozsofts.wechat.message.req.LocationMessage;
import net.ozsofts.wechat.message.req.NormalMessage;
import net.ozsofts.wechat.message.req.RequestMessage;
import net.ozsofts.wechat.message.req.RequestType;
import net.ozsofts.wechat.message.req.TextMessage;
import net.ozsofts.wechat.message.req.VideoMessage;
import net.ozsofts.wechat.message.req.VoiceMessage;
import net.ozsofts.wechat.message.resp.BaseMessage;
import net.ozsofts.wechat.service.MessageService;

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

	private Map<String, Class<?>> requestMessageMap = new HashMap<String, Class<?>>();

	private List<MessageHandler> messageHandlerList = new LinkedList<MessageHandler>();

	public void initialize() throws Exception {
		String packageName = RequestMessage.class.getPackage().getName();

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		requestMessageMap.put(RequestType.TEXT, classLoader.loadClass(packageName + ".TextMessage"));
		requestMessageMap.put(RequestType.IMAGE, classLoader.loadClass(packageName + ".ImageMessage"));
		requestMessageMap.put(RequestType.VOICE, classLoader.loadClass(packageName + ".VoiceMessage"));
		requestMessageMap.put(RequestType.VIDEO, classLoader.loadClass(packageName + ".VideoMessage"));
		requestMessageMap.put(RequestType.SHORTVIDEO, classLoader.loadClass(packageName + ".ShortvideoMessage"));
		requestMessageMap.put(RequestType.LOCATION, classLoader.loadClass(packageName + ".LocationMessage"));
		requestMessageMap.put(RequestType.LINK, classLoader.loadClass(packageName + ".LinkMessage"));

		requestMessageMap.put(EventType.SUBSCRIBE, classLoader.loadClass(packageName + ".SubscribeEvent"));
		requestMessageMap.put(EventType.UNSUBSCRIBE, classLoader.loadClass(packageName + ".UnsubscribeEvent"));
		requestMessageMap.put(EventType.CLICK, classLoader.loadClass(packageName + ".MenuEvent"));
		requestMessageMap.put(EventType.VIEW, classLoader.loadClass(packageName + ".MenuEvent"));
		requestMessageMap.put(EventType.LOCATION, classLoader.loadClass(packageName + ".LocationEvent"));
		requestMessageMap.put(EventType.SCAN, classLoader.loadClass(packageName + ".QrCodeEvent"));

		requestMessageMap.put(EventType.SCANCODEPUSH, classLoader.loadClass(packageName + ".ScanCodeEvent"));
		requestMessageMap.put(EventType.SCANCODEWAITMSG, classLoader.loadClass(packageName + ".ScanCodeEvent"));

		requestMessageMap.put(EventType.PICSYSPHOTO, classLoader.loadClass(packageName + ".SendPicsInfoEvent"));
		requestMessageMap.put(EventType.PICPHOTOORALBUM, classLoader.loadClass(packageName + ".SendPicsInfoEvent"));
		requestMessageMap.put(EventType.PICWEIXIN, classLoader.loadClass(packageName + ".SendPicsInfoEvent"));

		requestMessageMap.put(EventType.LOCATIONSELECT, classLoader.loadClass(packageName + ".LocationSelectEvent"));
		requestMessageMap.put(EventType.TEMPLATESENDJOBFINISH, classLoader.loadClass(packageName + ".TemplateMsgEvent"));
		requestMessageMap.put(EventType.MASSSENDJOBFINISH, classLoader.loadClass(packageName + ".SendMessageEvent"));
	}

	public String handleMessage(String reqdata, Map<String, String> params) {
		String reqTrace = params.get(Constants.PARA_REQUEST_TRACE);
		String sysId = params.get(Constants.PARA_SYS_ID);

		// 保存原始上行数据
		WxXmlmessage xmlmsg = new WxXmlmessage();
		xmlmsg.setSystemId(sysId);
		xmlmsg.setXml(reqdata);
		wxXmlmessageManager.addWxXmlmessage(xmlmsg);

		BaseMessage responseMessage = null;
		String result = "";
		try {
			Map<String, String> msgData = parseRequestXml(reqdata);

			String msgType = msgData.get("MsgType");
			if ("event".equals(msgType)) {
				msgType = msgData.get("Event");
			}

			Class<?> clazz = requestMessageMap.get(msgType);
			if (clazz == null) {
				log.error("[{}] 处理消息时对应的类型[{}]没有解析器!", reqTrace, msgType);
				return "";
			}

			RequestMessage receiveMessage = (RequestMessage) clazz.newInstance();
			// 对上行的消息进行解析
			receiveMessage.parse(msgData);
			receiveMessage.setSysId(sysId);
			if (log.isDebugEnabled()) {
				log.debug("[{}] 接收到的消息:\n      {}", reqTrace, receiveMessage.toString());
			}

			// 保存解析后的上行消息信息
			save(sysId, receiveMessage);

			// 实际对消息进行处理
			for (MessageHandler handler : messageHandlerList) {
				responseMessage = handler.handle(receiveMessage);

				// 有一个消息处理后需要返回，不再进行后续的处理
				if (responseMessage != null) {
					break;
				}
			}

			// 将消息处理后的结果进行打包处理
			result = "success";
			if (responseMessage != null) {
				result = responseMessage.toXML();
			}
		} catch (Exception e) {
			log.error("[" + reqTrace + "] 处理消息时出现错误。", e);
		}

		// 回复此字符串表示服务器已经处理，微信不需要再重发相同的消息
		return result;
	}

	public void addMessageHandler(MessageHandler messageHandler) {
		this.messageHandlerList.add(messageHandler);
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

				if ("SendPicsInfo".equals(name) || "SendLocationInfo".equals(name) || "ScanCodeInfo".equals(name)) {
					for (Iterator citer = el.elementIterator(); citer.hasNext();) {
						Element cel = (Element) citer.next();

						String cname = cel.getName();
						String cvalue = cel.getTextTrim();
						values.put(cname, cvalue);
					}
				} else {
					String value = el.getTextTrim();
					values.put(name, value);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			log.error("解析上行消息XML数据包错误！", ex);
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
	private WxMessage save(String systemId, RequestMessage recvMessage) throws Exception {
		if (RequestType.EVENT.equals(recvMessage.getMsgType())) {
			return null;
		}

		NormalMessage normalMessage = (NormalMessage) recvMessage;
		WxMessage message = wxMessageManager.findByMsgid(normalMessage.getMsgId());
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
		message.setMsgId(normalMessage.getMsgId());
		message.setDirection("RECV");

		if (RequestType.TEXT.equals(recvMessage.getMsgType())) {
			message.setContent(((TextMessage) recvMessage).getContent());
		} else if (RequestType.IMAGE.equals(recvMessage.getMsgType())) {
			message.setPicUrl(((ImageMessage) recvMessage).getPicUrl());
			message.setMediaId(((ImageMessage) recvMessage).getMediaId());
		} else if (RequestType.VOICE.equals(recvMessage.getMsgType())) {
			message.setFormat(((VoiceMessage) recvMessage).getFormat());
			message.setMediaId(((VoiceMessage) recvMessage).getMediaId());
		} else if (RequestType.VIDEO.equals(recvMessage.getMsgType()) || RequestType.SHORTVIDEO.equals(recvMessage.getMsgType())) {
			message.setMediaId(((VideoMessage) recvMessage).getMediaId());
			message.setThumbMediaId(((VideoMessage) recvMessage).getThumbMediaId());
		} else if (RequestType.LOCATION.equals(recvMessage.getMsgType())) {
			message.setLocationX(((LocationMessage) recvMessage).getLocationX());
			message.setLocationY(((LocationMessage) recvMessage).getLocationY());
			message.setScale(((LocationMessage) recvMessage).getScale());
			message.setLabel(((LocationMessage) recvMessage).getLabel());
		} else if (RequestType.LINK.equals(recvMessage.getMsgType())) {
			message.setTitle(((LinkMessage) recvMessage).getTitle());
			message.setDescription(((LinkMessage) recvMessage).getDescription());
			message.setUrl(((LinkMessage) recvMessage).getUrl());
		}

		message.setSystemId(systemId);

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

	public static final void main(String[] args) throws Exception {
		String data = "<xml><ToUserName><![CDATA[gh_e136c6e50636]]></ToUserName>" + //
				"<FromUserName><![CDATA[oMgHVjngRipVsoxg6TuX3vz6glDg]]></FromUserName>" + //
				"<CreateTime>1408090606</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[scancode_waitmsg]]></Event>" + //
				"<EventKey><![CDATA[6]]></EventKey>" + //
				"<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>" + //
				"<ScanResult><![CDATA[2]]></ScanResult>" + //
				"</ScanCodeInfo>" + //
				"</xml>";

		MessageServiceImpl impl = new MessageServiceImpl();
		impl.initialize();

		impl.parseRequestXml(data);
	}
}

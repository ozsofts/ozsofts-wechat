package net.ozsofts.wechat.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.service.impl.MessageServiceImpl;
import net.ozsofts.wechat.service.impl.WechatConfigServiceImpl;

public class MessageServiceTest {
	private WechatConfigService wechatConfigService;
	private MessageService messageService;
	private Map<String, String> params;

	@Before
	public void setUp() throws Exception {
		wechatConfigService = new WechatConfigServiceImpl("test", "", "", Constants.ENCRYPT_TYPE_RAW, "", "TEST");

		messageService = new MessageServiceImpl();
		((MessageServiceImpl) messageService).initialize();

		params = new HashMap<String, String>();
		params.put(Constants.PARA_TIMESTAMP, String.valueOf(System.currentTimeMillis()));
		params.put(Constants.PARA_NONCE, String.valueOf(System.currentTimeMillis()));
		params.put(Constants.PARA_ENCRYPT_TYPE, Constants.ENCRYPT_TYPE_RAW);
		params.put(Constants.PARA_SIGNATURE,
				wechatConfigService.getSignature(params.get(Constants.PARA_TIMESTAMP), params.get(Constants.PARA_NONCE)));
	}

	@Test
	public void testHandleTextMessage() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[text]]></MsgType>" + //
				"<Content><![CDATA[this is a test]]></Content>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleImageMessage() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[image]]></MsgType>" + //
				"<PicUrl><![CDATA[this is a url]]></PicUrl>" + //
				"<MediaId><![CDATA[media_id]]></MediaId>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleVoiceMessage() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[voice]]></MsgType>" + //
				"<MediaId><![CDATA[media_id]]></MediaId>" + //
				"<Format><![CDATA[Format]]></Format>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);

		msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[voice]]></MsgType>" + //
				"<MediaId><![CDATA[media_id]]></MediaId>" + //
				"<Format><![CDATA[Format]]></Format>" + //
				"<Recognition><![CDATA[腾讯微信团队]]></Recognition>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleVideoMessage() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[video]]></MsgType>" + //
				"<MediaId><![CDATA[media_id]]></MediaId>" + //
				"<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleShortvideoMessage() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[shortvideo]]></MsgType>" + //
				"<MediaId><![CDATA[media_id]]></MediaId>" + //
				"<ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleLocationMessage() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[location]]></MsgType>" + //
				"<Location_X>23.134521</Location_X>" + //
				"<Location_Y>113.358803</Location_Y>" + //
				"<Scale>20</Scale>" + //
				"<Label><![CDATA[位置信息]]></Label>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleLinkMessage() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[fromUser]]></FromUserName>" + //
				"<CreateTime>1348831860</CreateTime>" + //
				"<MsgType><![CDATA[link]]></MsgType>" + //
				"<Title><![CDATA[公众平台官网链接]]></Title>" + //
				"<Description><![CDATA[公众平台官网链接]]></Description>" + //
				"<Url><![CDATA[url]]></Url>" + //
				"<MsgId>1234567890123456</MsgId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleSubscribeEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[subscribe]]></Event>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);

		msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[subscribe]]></Event>" + //
				"<EventKey><![CDATA[qrscene_123123]]></EventKey>" + //
				"<Ticket><![CDATA[TICKET]]></Ticket>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleUnsubscribeEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[unsubscribe]]></Event>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleQrCodeEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[SCAN]]></Event>" + //
				"<EventKey><![CDATA[SCENE_VALUE]]></EventKey>" + //
				"<Ticket><![CDATA[TICKET]]></Ticket>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleLocationEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[LOCATION]]></Event>" + //
				"<Latitude>23.137466</Latitude>" + //
				"<Longitude>113.352425</Longitude>" + //
				"<Precision>119.385040</Precision>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleClickEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[CLICK]]></Event>" + //
				"<EventKey><![CDATA[EVENTKEY]]></EventKey>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleViewEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[VIEW]]></Event>" + //
				"<EventKey><![CDATA[www.qq.com]]></EventKey>" + //
				"<MenuId>MENUID</MenuId>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleScanCodePushEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[scancode_push]]></Event>" + //
				"<EventKey><![CDATA[6]]></EventKey>" + //
				"<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>" + //
				"<ScanResult><![CDATA[1]]></ScanResult>" + //
				"</ScanCodeInfo>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleScanCodeWaitmsgEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[scancode_waitmsg]]></Event>" + //
				"<EventKey><![CDATA[6]]></EventKey>" + //
				"<ScanCodeInfo><ScanType><![CDATA[qrcode]]></ScanType>" + //
				"<ScanResult><![CDATA[2]]></ScanResult>" + //
				"</ScanCodeInfo>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandlePicSysPhotoEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[pic_sysphoto]]></Event>" + //
				"<EventKey><![CDATA[6]]></EventKey>" + //
				"<SendPicsInfo><Count>1</Count>" + //
				"<PicList><item><PicMd5Sum><![CDATA[1b5f7c23b5bf75682a53e7b6d163e185]]></PicMd5Sum>" + //
				"</item>" + //
				"</PicList>" + //
				"</SendPicsInfo>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandlePicPhotoAlbumEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[pic_photo_or_album]]></Event>" + //
				"<EventKey><![CDATA[6]]></EventKey>" + //
				"<SendPicsInfo><Count>1</Count>" + //
				"<PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum>" + //
				"</item>" + //
				"</PicList>" + //
				"</SendPicsInfo>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandlePicWeixinEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[pic_weixin]]></Event>" + //
				"<EventKey><![CDATA[6]]></EventKey>" + //
				"<SendPicsInfo><Count>1</Count>" + //
				"<PicList><item><PicMd5Sum><![CDATA[5a75aaca956d97be686719218f275c6b]]></PicMd5Sum>" + //
				"</item>" + //
				"</PicList>" + //
				"</SendPicsInfo>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleLocationSelectEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[location_select]]></Event>" + //
				"<EventKey><![CDATA[6]]></EventKey>" + //
				"<SendLocationInfo><Location_X><![CDATA[23]]></Location_X>" + //
				"<Location_Y><![CDATA[113]]></Location_Y>" + //
				"<Scale><![CDATA[15]]></Scale>" + //
				"<Label><![CDATA[ 广州市海珠区客村艺苑路 106号]]></Label>" + //
				"<Poiname><![CDATA[Test]]></Poiname>" + //
				"</SendLocationInfo>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleMassSendJobEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[MASSSENDJOBFINISH]]></Event>" + //
				"<MsgID>1988</MsgID>" + //
				"<Status><![CDATA[sendsuccess]]></Status>" + //
				"<TotalCount>100</TotalCount>" + //
				"<FilterCount>80</FilterCount>" + //
				"<SentCount>75</SentCount>" + //
				"<ErrorCount>5</ErrorCount>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}

	@Test
	public void testHandleTemplateMessageEvent() throws Exception {
		String msgdata = "<xml>" + //
				"<ToUserName><![CDATA[toUser]]></ToUserName>" + //
				"<FromUserName><![CDATA[FromUser]]></FromUserName>" + //
				"<CreateTime>123456789</CreateTime>" + //
				"<MsgType><![CDATA[event]]></MsgType>" + //
				"<Event><![CDATA[TEMPLATESENDJOBFINISH]]></Event>" + //
				"<MsgID>200163836</MsgID>" + //
				"<Status><![CDATA[success]]></Status>" + //
				"</xml>";

		messageService.handleMessage(msgdata, params);
	}
}

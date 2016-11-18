package net.ozsofts.wechat.message.req;

public final class EventType {
	public static final String SUBSCRIBE = "subscribe";
	public static final String UNSUBSCRIBE = "unsubscribe";
	public static final String CLICK = "CLICK";
	public static final String VIEW = "VIEW";
	public static final String LOCATION = "LOCATION";
	public static final String SCAN = "SCAN";

	//
	// 以下新增能力，均仅支持微信iPhone5.4.1以上版本，和Android5.4以上版本的微信用户，旧版本微信用户点击后将没有回应，开发者也不能正常接收到事件推送。
	// https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141016&token=&lang=zh_CN
	//
	/**
	 * <p>
	 * 扫码推送事件 scancode_push
	 * <p>
	 * 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。
	 */
	public static final String SCANCODEPUSH = "scancode_push";
	/**
	 * <p>
	 * 扫码推送事件，且弹出“消息接收中”提示框 scancode_waitmsg
	 * <p>
	 * 用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息。
	 */
	public static final String SCANCODEWAITMSG = "scancode_waitmsg";
	/**
	 * <p>
	 * 弹出系统拍照发图 pic_sysphoto
	 * <p>
	 * 用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。
	 */
	public static final String PICSYSPHOTO = "pic_sysphoto";
	/**
	 * <p>
	 * 弹出拍照或者相册发图 pic_photo_or_album
	 * <p>
	 * 用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。
	 */
	public static final String PICPHOTOORALBUM = "pic_photo_or_album";
	/**
	 * <p>
	 * 弹出微信相册发图器 pic_weixin
	 * <p>
	 * 用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。
	 */
	public static final String PICWEIXIN = "pic_weixin";
	/**
	 * <p>
	 * 弹出地理位置选择器 location_select
	 * <p>
	 * 用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。
	 */
	public static final String LOCATIONSELECT = "location_select";

	//
	// 发送事件通知事件
	//
	/**
	 * <p>
	 * 在模版消息发送任务完成后，微信服务器会将是否送达成功作为通知，发送到开发者中心中填写的服务器配置地址中。
	 */
	public static final String TEMPLATESENDJOBFINISH = "TEMPLATESENDJOBFINISH";
	/**
	 * <p>
	 * 由于群发任务提交后，群发任务可能在一定时间后才完成，因此，群发接口调用时，仅会给出群发任务是否提交成功的提示，若群发任务提交成功，则在群发任务结束时，会向开发者在公众平台填写的开发者URL（callback URL）推送事件。
	 */
	public static final String MASSSENDJOBFINISH = "MASSSENDJOBFINISH";
}

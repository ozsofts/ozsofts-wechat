package net.ozsofts.wechat.api.type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 微信菜单结构。
 * 
 * @author Jack
 */
public class MenuInfo {
	//
	// 菜单类型定义
	//
	/** 点击推事件。用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互 */
	public final static String MENU_TYPE_CLICK = "click";
	/** 跳转URL。用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的网页URL，可与网页授权获取用户基本信息接口结合，获得用户基本信息。 */
	public final static String MENU_TYPE_VIEW = "view";
	/** 扫码推事件。用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后显示扫描结果（如果是URL，将进入URL），且会将扫码的结果传给开发者，开发者可以下发消息。 */
	public final static String MENU_TYPE_SCANCODE_PUSH = "scancode_push";
	/** 扫码推事件且弹出“消息接收中”提示框。用户点击按钮后，微信客户端将调起扫一扫工具，完成扫码操作后，将扫码的结果传给开发者，同时收起扫一扫工具，然后弹出“消息接收中”提示框，随后可能会收到开发者下发的消息 */
	public final static String MENU_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";
	/** 弹出系统拍照发图。用户点击按钮后，微信客户端将调起系统相机，完成拍照操作后，会将拍摄的相片发送给开发者，并推送事件给开发者，同时收起系统相机，随后可能会收到开发者下发的消息。 */
	public final static String MENU_TYPE_PIC_SYSPHOTO = "pic_sysphoto";
	/** 弹出拍照或者相册发图。用户点击按钮后，微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”。用户选择后即走其他两种流程。 */
	public final static String MENU_TYPE_PIC_PHOTOORALBUM = "pic_photo_or_album";
	/** 弹出微信相册发图器。用户点击按钮后，微信客户端将调起微信相册，完成选择操作后，将选择的相片发送给开发者的服务器，并推送事件给开发者，同时收起相册，随后可能会收到开发者下发的消息。 */
	public final static String MENU_TYPE_PIC_WEIXIN = "pic_weixin";
	/** 弹出地理位置选择器。用户点击按钮后，微信客户端将调起地理位置选择工具，完成选择操作后，将选择的地理位置发送给开发者的服务器，同时收起位置选择工具，随后可能会收到开发者下发的消息。 */
	public final static String MENU_TYPE_LOCATION_SELECT = "location_select";
	/** 下发消息（除文本消息）用户点击media_id类型按钮后，微信服务器会将开发者填写的永久素材id对应的素材下发给用户，永久素材类型可以是图片、音频、视频、图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id */
	public final static String MENU_TYPE_MEDIA_ID = "media_id";
	/** 跳转图文消息URL用户点击view_limited类型按钮后，微信客户端将打开开发者在按钮中填写的永久素材id对应的图文消息URL，永久素材类型只支持图文消息。请注意：永久素材id必须是在“素材管理/新增永久素材”接口上传后获得的合法id。 */
	public final static String MENU_TYPE_VIEW_LIMITED = "view_limited";

	/** 菜单按钮类型 */
	private String type;
	/** 菜单名称 */
	private String name;
	/** 当菜单类型为click、scancode_waitmsg、scancode_push时，保存用户定义的KEY值 */
	private String key;
	/** 当菜单类型为view时，保存需要跳转的URL地址 */
	private String url;
	/** 当菜单类型为media_id或view_limited时，保存相关永久素材 */
	private String mediaId;

	/** 父菜单对象，微信的菜单只能有两级，所以当parent不为空时，children一定为空；反之，当parent为空时，表示是父菜单，此时可以带children */
	private MenuInfo parent;
	/** 子菜单列表 */
	private List<MenuInfo> children;

	public MenuInfo() {
		this((MenuInfo) null);
	}

	public MenuInfo(MenuInfo parent) {
		this.parent = parent;
	}

	public MenuInfo(JSONObject menuObject) {
		this(null, menuObject);
	}

	public MenuInfo(MenuInfo parent, JSONObject menuObject) {
		this.parent = parent;

		this.name = menuObject.getString("name");
		if (menuObject.containsKey("type")) {
			this.type = menuObject.getString("type");

			if (MENU_TYPE_VIEW.equals(type)) {
				this.url = menuObject.getString("url");
			} else if (MENU_TYPE_MEDIA_ID.equals(type) || MENU_TYPE_VIEW_LIMITED.equals(type)) {
				this.mediaId = menuObject.getString("media_id");
			} else {
				this.key = menuObject.getString("key");
			}
		} else {
			if (menuObject.containsKey("sub_button")) {
				this.children = new ArrayList<MenuInfo>();

				JSONArray subMenuArray = menuObject.getJSONArray("sub_button");
				for (int i = 0; i < subMenuArray.size(); i++) {
					MenuInfo subMenu = new MenuInfo(this, subMenuArray.getJSONObject(i));
					this.children.add(subMenu);
				}
			}
		}
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MenuInfo getParent() {
		return parent;
	}

	public List<MenuInfo> getChildren() {
		return children;
	}

	public void setChildren(List<MenuInfo> menuList) {
		this.children = menuList;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> values = new HashMap<String, Object>();
		values.put("type", type);
		values.put("name", name);
		if (MENU_TYPE_VIEW.equals(type)) {
			values.put("url", url);
		} else if (MENU_TYPE_MEDIA_ID.equals(type) || MENU_TYPE_VIEW_LIMITED.equals(type)) {
			values.put("media_id", mediaId);
		} else {
			values.put("key", key);
		}

		if (parent == null && children != null && !children.isEmpty()) {
			List<Map<String, Object>> submenus = new LinkedList<Map<String, Object>>();
			for (MenuInfo submenu : children) {
				submenus.add(submenu.toMap());
			}
			values.put("sub_button", submenus);
		}
		return values;
	}
}

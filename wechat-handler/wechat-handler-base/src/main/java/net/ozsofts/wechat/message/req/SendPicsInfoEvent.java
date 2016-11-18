package net.ozsofts.wechat.message.req;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 适用于：
 * <ul>
 * <li>pic_sysphoto：弹出系统拍照发图的事件推送</li>
 * <li>pic_photo_or_album：弹出拍照或者相册发图的事件推送</li>
 * <li>pic_weixin：弹出微信相册发图器的事件推送</li>
 * </ul>
 * 
 * {@link https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421141016&token=&lang=zh_CN}
 * 
 * @author jack
 *
 */
@SuppressWarnings("rawtypes")
public class SendPicsInfoEvent extends EventMessage {

	private String eventKey;
	private Integer count;
	private List<Map> picList;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.eventKey = params.get("EventKey");
		this.count = Integer.parseInt(params.get("Count"));

		// TODO 处理上传的图片信息
	}

	public String getEventKey() {
		return eventKey;
	}

	public Integer getCount() {
		return count;
	}

	public List<Map> getPicList() {
		return picList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(eventKey).append("|");
		sb.append(count).append("|");
		return sb.toString();
	}
}

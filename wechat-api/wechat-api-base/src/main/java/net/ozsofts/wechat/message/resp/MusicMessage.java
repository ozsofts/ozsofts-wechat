package net.ozsofts.wechat.message.resp;

import java.util.HashMap;
import java.util.Map;

public class MusicMessage extends BaseMessage {
	/** 音乐标题 */
	private String title;
	/** 音乐描述 */
	private String description;
	/** 音乐链接 */
	private String musicUrl;
	/** 高质量音乐链接，WIFI环境优先使用该链接播放音乐 */
	private String hqMusicUrl;
	/** 缩略图的媒体id，通过上传多媒体文件，得到的id */
	private String thumbMediaId;

	public MusicMessage() {
		super.msgType = ResponseType.MSGTYPE_MUSIC;
	}

	@Override
	protected String makeXML() {
		StringBuilder sb = new StringBuilder();
		sb.append("<Video>");
		sb.append("<Title><![CDATA[").append(title).append("]]></Title>");
		sb.append("<Description><![CDATA[").append(description).append("]]></Description>");
		sb.append("<MusicUrl><![CDATA[").append(musicUrl).append("]]></MusicUrl>");
		sb.append("<HQMusicUrl><![CDATA[").append(hqMusicUrl).append("]]></HQMusicUrl>");
		sb.append("<ThumbMediaId><![CDATA[").append(thumbMediaId).append("]]></ThumbMediaId>");
		sb.append("</Video>");
		return sb.toString();
	}

	@Override
	protected void makeMap(Map<String, Object> data) {
		Map<String, Object> cont = new HashMap<String, Object>();
		cont.put("title", title);
		cont.put("desctiprion", description);
		cont.put("musicurl", musicUrl);
		cont.put("hqmusicurl", hqMusicUrl);
		cont.put("thumb_media_id", thumbMediaId);

		data.put("music", cont);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getHqMusicUrl() {
		return hqMusicUrl;
	}

	public void setHqMusicUrl(String hqMusicUrl) {
		this.hqMusicUrl = hqMusicUrl;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}

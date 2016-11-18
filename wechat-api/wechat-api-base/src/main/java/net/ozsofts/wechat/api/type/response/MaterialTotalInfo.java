package net.ozsofts.wechat.api.type.response;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;

public class MaterialTotalInfo implements Serializable {
	private static final long serialVersionUID = -4881413762002611518L;

	@JSONField(name = "video_count")
	private int video;
	@JSONField(name = "voice_count")
	private int voice;
	@JSONField(name = "image_count")
	private int image;
	@JSONField(name = "news_count")
	private int news;

	public int getVideo() {
		return video;
	}

	public void setVideo(int video) {
		this.video = video;
	}

	public int getVoice() {
		return voice;
	}

	public void setVoice(int voice) {
		this.voice = voice;
	}

	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public int getNews() {
		return news;
	}

	public void setNews(int news) {
		this.news = news;
	}

	@Override
	public String toString() {
		return "GetMaterialTotalCountResponse{" + "video=" + video + ", voice=" + voice + ", image=" + image + ", news=" + news + '}';
	}
}

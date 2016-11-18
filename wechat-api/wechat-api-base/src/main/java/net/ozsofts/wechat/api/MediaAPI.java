package net.ozsofts.wechat.api;

import java.io.InputStream;
import java.util.List;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.enums.MediaType;
import net.ozsofts.wechat.api.type.Article;

/**
 * 多媒体资源API
 */
public interface MediaAPI {

	/**
	 * 上传资源，会在微信服务器上保存3天，之后会被删除
	 *
	 * @param type
	 *            资源类型
	 * @param file
	 *            需要上传的文件
	 * @return 响应对象
	 */
	public String uploadMedia(String sysId, MediaType type, InputStream mediaData) throws WechatException;

	/**
	 * 上传群发文章素材。
	 *
	 * @param articles
	 *            上传的文章信息
	 * @return 响应对象
	 */
	public String uploadNews(String sysId, List<Article> articles) throws WechatException;

	/**
	 * 上传群发消息图片素材
	 */
	public String uploadImg(String sysId, InputStream imgData) throws WechatException;

	/**
	 * 下载资源
	 *
	 * @param mediaId
	 *            微信提供的资源唯一标识
	 * @return 响应对象
	 */
	public void downloadMedia(String sysId, String mediaId) throws WechatException;
}

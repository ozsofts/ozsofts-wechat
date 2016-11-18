package net.ozsofts.wechat.api;

import java.io.InputStream;
import java.util.List;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.enums.MaterialType;
import net.ozsofts.wechat.api.type.Article;
import net.ozsofts.wechat.api.type.response.MaterialListInfo;
import net.ozsofts.wechat.api.type.response.MaterialTotalInfo;

public interface MaterialAPI {

	/**
	 * 上传视频素材文件。
	 * 
	 * @param meterialData
	 *            素材文件
	 * @param title
	 *            素材标题
	 * @param introduction
	 *            素材描述信息
	 * @return 上传结果
	 */
	public String uploadMaterialFile(String sysId, MaterialType type, InputStream materialData, String title, String introduction)
			throws WechatException;

	/**
	 * 上传图文消息素材
	 * 
	 * @param articles
	 *            图文消息列表
	 * @return mediaId
	 */
	public String uploadMaterialNews(String sysId, List<Article> articles) throws WechatException;

	/**
	 * 下载永久素材
	 * 
	 * @param mediaId
	 *            素材ID
	 * @param type
	 *            素材类型
	 * @return 下载结果
	 */
	public void downloadMaterial(String sysId, String mediaId, MaterialType type) throws WechatException;

	/**
	 * 获取已创建永久素材的数量
	 * 
	 * @return 永久素材数量结果
	 */
	public MaterialTotalInfo countMaterial(String sysId) throws WechatException;

	/**
	 * 获取素材列表
	 * 
	 * @param type
	 *            素材类型
	 * @param offset
	 *            从全部素材的该偏移位置开始返回，0表示从第一个素材 返回
	 * @param count
	 *            返回素材的数量，取值在1到20之间
	 * @return 素材列表结果
	 */
	public MaterialListInfo batchGetMaterial(String sysId, MaterialType type, int offset, int count) throws WechatException;

	/**
	 * 删除一个永久素材
	 * 
	 * @param mediaId
	 *            素材ID
	 * @return 删除结果
	 */
	public void deleteMaterial(String sysId, String mediaId) throws WechatException;
}

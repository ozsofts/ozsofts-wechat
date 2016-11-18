package net.ozsofts.wechat.functions.impl;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.functions.MediaFunction;
import net.ozsofts.wechat.service.WechatCommService;

/**
 * <p>
 * 临时素材功能接口
 * 
 * @author Jack
 * 
 */
public class MediaFunctionImpl implements MediaFunction {
	@Autowired
	private WechatCommService wechatCommService;

	/**
	 * <p>
	 * 新增临时素材
	 * 
	 * @param token
	 * @param type
	 * @param mediaFile
	 *            需要上传的素材文件
	 * @return
	 */
	public String uploadMedia(String token, String type, File mediaFile) throws Exception {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("type", type);

		return wechatCommService.postMedia("/cgi-bin/media/upload", token, params, null, mediaFile);
	}

	/**
	 * <p>
	 * 获取临时素材文件
	 * 
	 * @param token
	 * @param mediaId
	 * @param mediaFile
	 *            需要保存素材的文件
	 * @throws Exception
	 */
	public void getMedia(String token, String mediaId, File mediaFile) throws Exception {
		Map<String, String> params = new TreeMap<String, String>();
		params.put("media_id", mediaId);

		wechatCommService.getMedia("/cgi-bin/media/get", token, mediaId, mediaFile);
	}
}

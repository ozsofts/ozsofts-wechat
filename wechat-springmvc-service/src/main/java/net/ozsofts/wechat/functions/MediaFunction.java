package net.ozsofts.wechat.functions;

import java.io.File;

/**
 * <p>
 * 临时素材功能接口
 * 
 * @author Jack
 * 
 */
public interface MediaFunction {
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
	public String uploadMedia(String token, String type, File mediaFile) throws Exception;

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
	public void getMedia(String token, String mediaId, File mediaFile) throws Exception;
}

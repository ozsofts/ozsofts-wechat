package net.ozsofts.wechat.service;

import java.io.File;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * <p>
 * 访问微信接口的处理类，此类对访问微信的处理进行封装
 * <p>
 * 此类是工具方法，在整个处理中只有一个实例
 * 
 * @author jack
 */
public interface WechatCommService {

	public JSONObject get(String funcUrl, String token, Map<String, String> params) throws Exception;

	public void getMedia(String funcUrl, String token, String mediaId, File recvFile) throws Exception;

	public JSONObject post(String funcUrl, String token, Map<String, String> params, Map<String, Object> data) throws Exception;

	public String postMedia(String funcUrl, String token, Map<String, String> params, Map<String, Object> data, File file) throws Exception;
}

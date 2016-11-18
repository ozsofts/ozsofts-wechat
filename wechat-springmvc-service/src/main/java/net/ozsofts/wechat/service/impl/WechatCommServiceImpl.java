package net.ozsofts.wechat.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.Error;
import net.ozsofts.wechat.service.WechatCommService;

/**
 * <p>
 * 访问微信接口的处理类，此类对访问微信的处理进行封装
 * <p>
 * 此类是工具方法，在整个处理中只有一个实例
 * 
 * @author jack
 */
public class WechatCommServiceImpl implements WechatCommService {
	private static Logger trace = LoggerFactory.getLogger("wx.data");
	private static Logger log = LoggerFactory.getLogger(WechatCommServiceImpl.class);

	public JSONObject get(String funcUrl, String token, Map<String, String> params) throws Exception {
		long startTime = System.currentTimeMillis();
		StringBuilder tracesb = new StringBuilder("[GET : ").append(funcUrl).append("]\n");

		CloseableHttpClient httpclient = createSSLClientDefault();

		URIBuilder uribuilder = new URIBuilder() //
				.setScheme("https") //
				.setHost("api.weixin.qq.com") //
				.setPath(funcUrl)//
				.setParameter(Constants.PARA_ACCESS_TOKEN, token);

		if (params != null) {
			for (Entry<String, String> entry : params.entrySet()) {
				uribuilder.setParameter(entry.getKey(), entry.getValue());
			}
		}

		HttpGet httpget = new HttpGet(uribuilder.build());
		if (log.isDebugEnabled()) {
			tracesb.append("  request  :[").append(httpget.getRequestLine()).append("]\n");
		}

		CloseableHttpResponse response = httpclient.execute(httpget);
		try {
			byte[] buff = EntityUtils.toByteArray(response.getEntity());
			JSONObject result = JSONObject.parseObject(new String(buff));
			if (result.containsKey(Constants.PARA_ERR_CODE)) {
				String errorCode = result.getString(Constants.PARA_ERR_CODE);
				if (!"0".equals(errorCode)) {
					log.error("[get method] error code:[{}]  error message:[{}]", errorCode, Error.getErrorMessage(errorCode));
					throw new Exception("[get method] error code:[" + errorCode + "]  error message:[" + Error.getErrorMessage(errorCode) + "]");
				}
			}

			if (trace.isInfoEnabled()) {
				tracesb.append("  response :[").append(result.toString()).append("]\n");
				tracesb.append("[GET : ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}

			return result;
		} catch (Exception ex) {
			if (trace.isInfoEnabled()) {
				tracesb.append("  error    :[").append(ex.getMessage()).append("]\n");
				tracesb.append("[GET : ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}

			log.error("[get method] communication error!", ex);
			throw ex;
		} finally {
			if (response != null) {
				response.close();
			}

			if (httpclient != null) {
				httpclient.close();
			}
		}
	}

	public void getMedia(String funcUrl, String token, String mediaId, File recvFile) throws Exception {
		long startTime = System.currentTimeMillis();
		StringBuilder tracesb = new StringBuilder("[POST: ").append(funcUrl).append("]\n");

		CloseableHttpClient httpclient = createSSLClientDefault();

		URIBuilder uribuilder = new URIBuilder() //
				.setScheme("https") //
				.setHost("api.weixin.qq.com") //
				.setPath(funcUrl) //
				.setParameter(Constants.PARA_ACCESS_TOKEN, token);

		HttpPost httppost = new HttpPost(uribuilder.build());
		if (log.isDebugEnabled()) {
			tracesb.append("  request  :[").append(httppost.getRequestLine()).append("]\n");
		}

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("media_id", mediaId);
		JSONObject json = new JSONObject(data);
		httppost.setEntity(new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON));
		if (trace.isInfoEnabled()) {
			tracesb.append("    data :[").append(json.toString()).append("]\n");
		}

		CloseableHttpResponse response = httpclient.execute(httppost);
		BufferedOutputStream bos = null;
		try {
			byte[] buff = EntityUtils.toByteArray(response.getEntity());

			bos = new BufferedOutputStream(new FileOutputStream(recvFile));
			bos.write(buff);

			if (trace.isInfoEnabled()) {
				tracesb.append("  response :[ media length=").append(buff.length).append("]\n");
				tracesb.append("[POST: ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}
		} catch (Exception ex) {
			if (trace.isInfoEnabled()) {
				tracesb.append("  error    :[").append(ex.getMessage()).append("]\n");
				tracesb.append("[POST: ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}

			log.error("[get media post method] communication error!", ex);
			throw ex;
		} finally {
			if (bos != null) {
				IOUtils.closeQuietly(bos);
			}

			if (response != null) {
				response.close();
			}

			if (httpclient != null) {
				httpclient.close();
			}
		}
	}

	public JSONObject post(String funcUrl, String token, Map<String, String> params, Map<String, Object> data) throws Exception {
		long startTime = System.currentTimeMillis();
		StringBuilder tracesb = new StringBuilder("[POST: ").append(funcUrl).append("]\n");

		CloseableHttpClient httpclient = createSSLClientDefault();

		URIBuilder uribuilder = new URIBuilder() //
				.setScheme("https") //
				.setHost("api.weixin.qq.com") //
				.setPath("funcUrl") //
				.setParameter(Constants.PARA_ACCESS_TOKEN, token);

		for (Entry<String, String> entry : params.entrySet()) {
			uribuilder.setParameter(entry.getKey(), entry.getValue());
		}

		HttpPost httppost = new HttpPost(uribuilder.build());
		if (log.isDebugEnabled()) {
			tracesb.append("  request  :[").append(httppost.getRequestLine()).append("]\n");
		}

		// 设置POST的数据信息
		if (data != null && !data.isEmpty()) {
			JSONObject json = new JSONObject(data);
			httppost.setEntity(new StringEntity(json.toJSONString(), ContentType.APPLICATION_JSON));

			if (trace.isInfoEnabled()) {
				tracesb.append("    data :[").append(json.toString()).append("]\n");
			}
		}

		CloseableHttpResponse response = httpclient.execute(httppost);
		try {
			byte[] buff = EntityUtils.toByteArray(response.getEntity());

			JSONObject result = JSONObject.parseObject(new String(buff));
			if (result.containsKey(Constants.PARA_ERR_CODE)) {
				String errorCode = result.getString(Constants.PARA_ERR_CODE);
				if (!"0".equals(errorCode)) {
					log.error("[post method] error code:[" + errorCode + "]  error message:[" + Error.getErrorMessage(errorCode) + "]");
					throw new Exception("[post method] error code:[" + errorCode + "]  error message:[" + Error.getErrorMessage(errorCode) + "]");
				}
			}

			if (trace.isInfoEnabled()) {
				tracesb.append("  response :[").append(result.toString()).append("]\n");
				tracesb.append("[POST: ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}

			return result;
		} catch (Exception ex) {
			if (trace.isInfoEnabled()) {
				tracesb.append("  error    :[").append(ex.getMessage()).append("]\n");
				tracesb.append("[POST: ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}

			log.error("[post method] communication error!", ex);
			throw ex;
		} finally {
			if (response != null) {
				response.close();
			}

			if (httpclient != null) {
				httpclient.close();
			}
		}
	}

	public String postMedia(String funcUrl, String token, Map<String, String> params, Map<String, Object> data, File file) throws Exception {
		long startTime = System.currentTimeMillis();
		StringBuilder tracesb = new StringBuilder("[POST: ").append(funcUrl).append("]\n");

		CloseableHttpClient httpclient = createSSLClientDefault();

		URIBuilder uribuilder = new URIBuilder() //
				.setScheme("https") //
				.setHost("api.weixin.qq.com") //
				.setPath("funcUrl") //
				.setParameter(Constants.PARA_ACCESS_TOKEN, token);

		for (Entry<String, String> entry : params.entrySet()) {
			uribuilder.setParameter(entry.getKey(), entry.getValue());
		}

		HttpPost httppost = new HttpPost(uribuilder.build());
		if (log.isDebugEnabled()) {
			tracesb.append("  request  :[").append(httppost.getRequestLine()).append("]\n");
		}

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		if (data != null && !data.isEmpty()) {
			for (Entry<String, Object> d : data.entrySet()) {
				JSONObject jdata = JSONObject.parseObject((String) d.getValue());
				builder.addTextBody(d.getKey(), jdata.toString());
			}

			if (trace.isInfoEnabled()) {
				tracesb.append("    data :[").append(data.toString()).append("]\n");
			}
		}
		builder.addPart("media", new FileBody(file));
		httppost.setEntity(builder.build());

		CloseableHttpResponse response = httpclient.execute(httppost);
		try {
			byte[] buff = EntityUtils.toByteArray(response.getEntity());

			JSONObject result = JSONObject.parseObject(new String(buff));
			if (result.containsKey(Constants.PARA_ERR_CODE)) {
				String errorCode = result.getString(Constants.PARA_ERR_CODE);
				if (!"0".equals(errorCode)) {
					log.error("[postForm method] error code:[" + errorCode + "]  error message:[" + Error.getErrorMessage(errorCode) + "]");
					throw new Exception("[postForm method] error code:[" + errorCode + "]  error message:[" + Error.getErrorMessage(errorCode) + "]");
				}
			}

			if (trace.isInfoEnabled()) {
				tracesb.append("  response :[").append(result.toString()).append("]\n");
				tracesb.append("[POST: ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}

			return result.getString("media_id");
		} catch (Exception ex) {
			if (trace.isInfoEnabled()) {
				tracesb.append("  error    :[").append(ex.getMessage()).append("]\n");
				tracesb.append("[POST: ").append(funcUrl).append("  time:").append(System.currentTimeMillis() - startTime).append("]");
				trace.info(tracesb.toString());
			}

			log.error("[postForm method] communication error!", ex);
			throw ex;
		} finally {
			if (response != null) {
				response.close();
			}

			if (httpclient != null) {
				httpclient.close();
			}
		}
	}

	private CloseableHttpClient createSSLClientDefault() throws Exception {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				// 信任所有
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
			return HttpClients.custom().setSSLSocketFactory(sslsf).build();
		} catch (Exception e) {
			throw new Exception("生成SSL客户端出现问题！", e);
		}
	}
}

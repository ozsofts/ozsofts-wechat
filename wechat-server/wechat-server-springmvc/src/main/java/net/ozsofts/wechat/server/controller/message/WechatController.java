/**
 * Copyright (c) 2016, YYD All Rights Reserved.
 */
package net.ozsofts.wechat.server.controller.message;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.service.MessageService;
import net.ozsofts.wechat.service.WechatConfigManager;
import net.ozsofts.wechat.service.WechatConfigService;

/**
 * <p>
 * 微信服务上行处理对象<br/>
 * 
 * date: 2016年1月23日 下午3:43:59 <br/>
 *
 * @author jack
 */
@Controller
public class WechatController {
	private static Logger log = LoggerFactory.getLogger(WechatController.class);

	@Autowired
	private WechatConfigManager wechatConfigManager;

	@Autowired
	private MessageService messageService;

	/**
	 * <p>
	 * 在设置微信服务接口时进行验证使用，在设置完成后如果不修改参数一般不会再调用此接口
	 * 
	 * @param sysId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String auth(@PathVariable("id") String sysId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> params = getRequestParams(sysId, request);
		String reqTrace = params.get(Constants.PARA_REQUEST_TRACE);

		try {
			WechatConfigService wechatConfigService = wechatConfigManager.getWechatConfigService(sysId);
			if (wechatConfigService == null) {
				log.error("[{}] 未找到对应的微信参数，请检查配置!", reqTrace);
				return null;
			}

			String timestamp = params.get(Constants.PARA_TIMESTAMP);
			String nonce = params.get(Constants.PARA_NONCE);
			String echostr = params.get(Constants.PARA_ECHOSTR);

			if (log.isDebugEnabled()) {
				log.debug("[{}] 验证服务器有效性: [timestamp:{}] [nonce:{}] [echostr:{}]", reqTrace, timestamp, nonce, echostr);
			}

			// 在验证URL时需要使用接口中的加密类型进行判断
			String verifyEncryptType = params.get(Constants.PARA_ENCRYPT_TYPE);
			String signature = "";
			if (Constants.ENCRYPT_TYPE_AES.equalsIgnoreCase(verifyEncryptType)) {
				signature = params.get(Constants.PARA_MSG_SIGNATURE);
			} else {
				signature = params.get(Constants.PARA_SIGNATURE);
			}

			return wechatConfigService.verifyUrl(timestamp, nonce, echostr, verifyEncryptType, signature);
		} catch (Exception ex) {
			log.error("[{}] 在处理接入消息时出现错误!", reqTrace, ex);
			return null;
		}
	}

	/**
	 * <p>
	 * 微信上行服务接口
	 * 
	 * @param sysId
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String handle(@PathVariable("id") String sysId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> params = getRequestParams(sysId, request);
		String reqTrace = params.get(Constants.PARA_REQUEST_TRACE);
		if (log.isDebugEnabled()) {
			log.debug("[{}] 接口收到请求:[queryString:{}]", reqTrace, request.getQueryString());
		}

		try {
			// 检查通过后，读取POST的数据包信息
			BufferedInputStream bis = new BufferedInputStream(request.getInputStream());
			int offset = 0;
			int remain = request.getContentLength();
			byte[] buffer = new byte[remain];
			while (remain > 0) {
				int nread = bis.read(buffer, offset, remain);
				if (nread == -1)
					break;

				offset += nread;
				remain -= nread;
			}

			String postData = new String(buffer, "UTF8");
			if (log.isDebugEnabled()) {
				log.debug("[{}] 上行原始数据: {}", reqTrace, postData);
			}

			WechatConfigService wechatConfigService = wechatConfigManager.getWechatConfigService(sysId);
			if (wechatConfigService == null) {
				log.error("[{}] 未找到对应的微信参数，请检查配置!", reqTrace);
				return null;
			}

			String timestamp = params.get(Constants.PARA_TIMESTAMP);
			String nonce = params.get(Constants.PARA_NONCE);
			String requestEncryptType = params.get(Constants.PARA_ENCRYPT_TYPE);
			String signature = "";
			if (Constants.ENCRYPT_TYPE_AES.equalsIgnoreCase(requestEncryptType)) {
				signature = params.get(Constants.PARA_MSG_SIGNATURE);
			} else {
				signature = params.get(Constants.PARA_SIGNATURE);
			}

			String requestData = wechatConfigService.handleRequestMessage(timestamp, nonce, requestEncryptType, signature, postData);
			if (log.isDebugEnabled()) {
				log.debug("[{}] 实际消息内容:\n{}", reqTrace, requestData);
			}

			// 调用消息处理对象进行消息的实际处理
			String responseData = messageService.handleMessage(requestData, params);

			// 将消息处理后的结果进行打包处理
			String result = wechatConfigService.handleResponseMessage(timestamp, nonce, responseData);
			if (log.isDebugEnabled()) {
				log.debug("[{}] 接口返回结果:\n{}", reqTrace, result);
			}

			// 返回处理结果
			return result;
		} catch (Exception ex) {
			log.error("[" + reqTrace + "] 在处理上行消息时出现错误!", ex);
			return null;
		}
	}

	private static AtomicInteger serial = new AtomicInteger();

	private Map<String, String> getRequestParams(String sysId, HttpServletRequest req) {
		Map<String, String> params = new HashMap<String, String>();
		params.put(Constants.PARA_SYS_ID, sysId);

		StringBuilder requestTrace = new StringBuilder();
		requestTrace.append(sysId);
		requestTrace.append(DateTime.now().toString("-yyyyMMdd-HHmmss-"));
		requestTrace.append(StringUtils.leftPad(String.valueOf(serial.incrementAndGet()), 5, '0'));
		params.put(Constants.PARA_REQUEST_TRACE, requestTrace.toString());

		Map<String, String[]> requestParams = req.getParameterMap();
		for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
			String name = (String) iter.next();
			String[] values = (String[]) requestParams.get(name);
			String valueStr = "";
			for (int i = 0; i < values.length; i++) {
				valueStr = (i == values.length - 1) ? valueStr + values[i] : valueStr + values[i] + ",";
			}
			params.put(name, valueStr);
		}

		return params;
	}
}

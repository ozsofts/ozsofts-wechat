/**
 * Copyright (c) 2016, YYD All Rights Reserved.
 */
package net.ozsofts.wechat.server.message.controller;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import net.ozsofts.wechat.Constants;
import net.ozsofts.wechat.core.entity.WxAccount;
import net.ozsofts.wechat.core.manager.WxAccountManager;
import net.ozsofts.wechat.server.message.service.MessageService;
import net.ozsofts.wechat.service.AccountService;

/**
 * description: <br/>
 * date: 2016年1月23日 下午3:43:59 <br/>
 *
 * @author jack
 */
@Controller
public class WechatController {
	private static Logger log = LoggerFactory.getLogger(WechatController.class);

	@Autowired
	private WxAccountManager wxAccountManager;

	@Autowired
	private AccountService accountService;
	@Autowired
	private MessageService messageService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public String auth(@PathVariable("id") String sysId, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> params = getRequestParams(request);
		try {
			WxAccount account = wxAccountManager.getAccountBySystemId(sysId);
			if (account == null) {
				log.error("[{}] 未找到对应的微信参数，请检查配置!");
				return null;
			}

			String timestamp = params.get(Constants.PARA_TIMESTAMP);
			String nonce = params.get(Constants.PARA_NONCE);
			String echostr = params.get(Constants.PARA_ECHOSTR);

			if (log.isDebugEnabled()) {
				log.debug("[{}] 验证服务器有效性: [timestamp:{}] [nonce:{}] [echostr:{}]", sysId, timestamp, nonce, echostr);
			}

			// 在验证URL时需要使用接口中的加密类型进行判断
			String verifyEncryptType = params.get(Constants.PARA_ENCRYPT_TYPE);
			String signature = "";
			if (Constants.ENCRYPT_TYPE_AES.equalsIgnoreCase(verifyEncryptType)) {
				signature = params.get(Constants.PARA_MSG_SIGNATURE);
			} else {
				signature = params.get(Constants.PARA_SIGNATURE);
			}

			return accountService.verifyUrl(account, timestamp, nonce, echostr, verifyEncryptType, signature);
		} catch (Exception ex) {
			log.error("在处理接入消息时出现错误!", ex);
			return null;
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST)
	@ResponseBody
	public String handle(@PathVariable("id") String sysId, HttpServletRequest request, HttpServletResponse response) {
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

			if (log.isDebugEnabled()) {
				log.debug("[{}] 上行原始数据: {}", sysId, new String(buffer, "UTF8"));
			}

			// 检查接收数据的正确性
			Map<String, String> params = getRequestParams(request);
			params.put("system_id", sysId);

			if (log.isDebugEnabled()) {
				StringBuilder sb = new StringBuilder().append("[").append(sysId).append("] 接口收到请求:").append(params);
				sb.append(" [queryString:").append(request.getQueryString()).append("]");
				log.debug(sb.toString());
			}

			WxAccount account = wxAccountManager.getAccountBySystemId(sysId);
			if (account == null) {
				log.error("[{}] 未找到对应的微信参数，请检查配置!");
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

			// 检查上行消息的合法性以及对数据进行解密处理
			String requestData = accountService.handleRequestMessage(account, timestamp, nonce, requestEncryptType, signature,
					new String(buffer, "UTF8"));
			if (log.isDebugEnabled()) {
				log.debug("[{}] 实际消息内容:\n{}", sysId, requestData);
			}

			// 调用消息处理对象进行消息的实际处理
			String responseData = messageService.handleMessage(account, requestData);
			// 将消息处理后的结果进行打包处理
			String result = accountService.handleResponseMessage(account, timestamp, nonce, responseData);

			if (log.isDebugEnabled()) {
				log.debug("[{}] 接口返回结果:\n{}", sysId, result);
			}

			// 返回处理结果
			return result;
		} catch (Exception ex) {
			log.error("在处理上行消息时出现错误!", ex);
			return null;
		}
	}

	private Map<String, String> getRequestParams(HttpServletRequest req) {

		Map<String, String> params = new HashMap<String, String>();
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

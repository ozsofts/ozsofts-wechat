package net.ozsofts.wechat.server.payment.controller;

import java.io.BufferedInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * description: <br/>
 * date: 2016年1月19日 下午3:19:56 <br/>
 *
 * @author jack
 */
@Controller
public class WechatNotifyController {
	private static Logger log = LoggerFactory.getLogger(WechatNotifyController.class);

	@RequestMapping("/pay/notify")
	public String payNotify(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnCode = "SUCCESS";
		String returnMsg = "OK";
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

			String notifyStr = new String(buffer, "UTF8");
			if (log.isInfoEnabled()) {
				log.info("[WechatController:payNotify] 支付回调信息[{}]", notifyStr);
			}

			// wechatAppService.payNotify(notifyStr);
		} catch (Exception ex) {
			log.error("[WechatController:payNotify] 支付回调处理错误!", ex);

			returnCode = "FAIL";
			returnMsg = ex.getMessage();
		}

		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<return_code><![CDATA[").append(returnCode).append("]]></return_code>");
		sb.append("<return_msg><![CDATA[").append(returnMsg).append("]]></return_msg>");
		sb.append("</xml>");

		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().print(sb.toString());

		return null;
	}
}

package net.ozsofts.wechat.api;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.enums.QrcodeType;
import net.ozsofts.wechat.api.type.response.QrcodeInfo;

/**
 * 二维码相关API
 *
 * @author jack
 */
public interface QrcodeAPI {
	/**
	 * 创建二维码
	 *
	 * @param actionName
	 *            二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
	 * @param sceneId
	 *            场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @param expireSeconds
	 *            该二维码有效时间，以秒为单位。 最大不超过1800
	 * @return 二维码对象
	 */
	public QrcodeInfo createQrcode(String sysId, QrcodeType actionName, String sceneId, Integer expireSeconds) throws WechatException;

	/**
	 * 创建二维码
	 *
	 * @param actionName
	 *            二维码类型，QR_SCENE为临时,QR_LIMIT_SCENE为永久
	 * @param sceneId
	 *            场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
	 * @param sceneStr
	 *            场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
	 * @param expireSeconds
	 *            该二维码有效时间，以秒为单位。 最大不超过1800
	 * @return 二维码对象
	 */
	public QrcodeInfo createQrcode(String sysId, QrcodeType actionName, String sceneId, String sceneStr, Integer expireSeconds)
			throws WechatException;
}

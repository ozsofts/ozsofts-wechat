package net.ozsofts.wechat.core.manager;

import java.util.List;

import net.ozsofts.wechat.core.entity.WxXmlmessage;

/**
 * Description : Manager接口 WxXmlmessageManager <br>
 * <br>
 * Time : 2016/04/20 16:58
 */

public interface WxXmlmessageManager {

	public int addWxXmlmessage(WxXmlmessage wxXmlmessage);

	public int updateWxXmlmessage(WxXmlmessage wxXmlmessage);

	public int deleteWxXmlmessage(Long id);

	public int batchDeleteWxXmlmessage(Long[] ids);

	public WxXmlmessage getWxXmlmessage(Long id);

	public List<WxXmlmessage> getAllWxXmlmessage();

}

package net.ozsofts.wechat.api.impl;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import net.ozsofts.wechat.WechatException;
import net.ozsofts.wechat.api.CustomAPI;
import net.ozsofts.wechat.api.response.CustomAccountsResponse;
import net.ozsofts.wechat.api.type.response.KFInfo;
import net.ozsofts.wechat.message.resp.BaseMessage;

public class CustomAPIImpl extends BaseAPI implements CustomAPI {

	@Override
	public void sendMessage(String sysId, String openId, BaseMessage message) throws WechatException {
		message.setToUserName(openId);
		post(sysId, "/cgi-bin/message/custom/send", null, message.toMap());
	}

	@Override
	public void addCustomAccount(String sysId, String kfAccount, String nickname) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("kf_account", kfAccount);
		data.put("nickname", nickname);
		post(sysId, "/customservice/kfaccount/add", null, data);
	}

	@Override
	public void inviteWorker(String sysId, String kfAccount, String inviteWx) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("kf_account", kfAccount);
		data.put("invite_wx", inviteWx);
		post(sysId, "/customservice/kfaccount/inviteworker", null, data);
	}

	@Override
	public void updateCustomAccount(String sysId, String kfAccount, String nickname) throws WechatException {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("kf_account", kfAccount);
		data.put("nickname", nickname);
		post(sysId, "/customservice/kfaccount/update", null, data);
	}

	@Override
	public void deleteCustomAccount(String sysId, String kfAccount) throws WechatException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("kf_account", kfAccount);
		get(sysId, "/customservice/kfaccount/del", params);
	}

	@Override
	public void uploadHeadImg(String sysId, String kfAccount, InputStream in) throws WechatException {
	}

	@Override
	public List<KFInfo> getCustomAccountList(String sysId) throws WechatException {
		JSONObject result = get(sysId, "/cgi-bin/customservice/getkflist", null);

		CustomAccountsResponse response = JSON.toJavaObject(result, CustomAccountsResponse.class);
		return response.getCustomAccountList();
	}

}

package net.ozsofts.wechat.api.response;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

import net.ozsofts.wechat.api.type.response.KFInfo;

@SuppressWarnings("serial")
public class CustomAccountsResponse implements Serializable {

	@JSONField(name = "kf_list")
	private List<KFInfo> customAccountList;

	public List<KFInfo> getCustomAccountList() {
		return customAccountList;
	}

	public void setCustomAccountList(List<KFInfo> customAccountList) {
		this.customAccountList = customAccountList;
	}
}

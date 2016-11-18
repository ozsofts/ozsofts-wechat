package net.ozsofts.wechat.api.type.response;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

public class MaterialListInfo implements Serializable {
	private static final long serialVersionUID = 5116135358298816716L;

	@JSONField(name = "total_count")
	private int totalCount;// 该类型素材总数
	@JSONField(name = "item_count")
	private int itemCount;// 本次获取的数量
	@JSONField(name = "item")
	private List<Map<String, Object>> items;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	public List<Map<String, Object>> getItems() {
		return items;
	}

	public void setItems(List<Map<String, Object>> items) {
		this.items = items;
	}
}

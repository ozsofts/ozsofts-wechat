package net.ozsofts.wechat.api.type;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class BaseDataCube implements Serializable {
	private static final long serialVersionUID = -1930433703550040106L;

	@JSONField(name = "ref_date", format = "yyyy-MM-dd")
	private Date refDate;

	public Date getRefDate() {
		return refDate;
	}

	public void setRefDate(Date refDate) {
		this.refDate = refDate;
	}
}

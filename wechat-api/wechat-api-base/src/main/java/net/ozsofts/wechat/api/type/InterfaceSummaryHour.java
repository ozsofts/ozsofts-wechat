package net.ozsofts.wechat.api.type;

import com.alibaba.fastjson.annotation.JSONField;

@SuppressWarnings("serial")
public class InterfaceSummaryHour extends InterfaceSummary {

    @JSONField(name = "ref_hour")
    private Integer refHour;

    public Integer getRefHour() {
        return refHour;
    }

    public void setRefHour(Integer refHour) {
        this.refHour = refHour;
    }
}

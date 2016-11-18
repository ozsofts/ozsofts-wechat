package net.ozsofts.wechat.api.type.response;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class KFInfoTest {

	@Test
	public void test() {
		String kfdata = "{" //
				+ "\"kf_account\" : \"test3@test\"," //
				+ "\"kf_headimgurl\" : \"http://mmbiz.qpic.cn/mmbiz/4whpV1VZl2iccsvYbHvnphkyGtnvjfUS8Ym0GSaLic0FD3vN0V8PILcibEGb2fPfEOmw/0\","//
				+ "\"kf_id\" : \"1003\","//
				+ "\"kf_nick\" : \"ntest3\","//
				+ "\"invite_wx\" : \"kfwx3\","//
				+ "\"invite_expire_time\" : 123456789,"//
				+ "\"invite_status\" : \"waiting\""//
				+ "}";

		// JSONObject kfObject = JSONObject.parseObject(kfdata);
		// kfObject.put(JSON.DEFAULT_TYPE_KEY, KFInfo.class.getName());
		// System.out.println(kfObject.toJSONString());

		// KFInfo kfInfo = JSON.parseObject(kfObject.toJSONString(), KFInfo.class);
		KFInfo kfInfo = JSON.parseObject(kfdata, KFInfo.class);
		System.out.println(kfInfo);
	}

}

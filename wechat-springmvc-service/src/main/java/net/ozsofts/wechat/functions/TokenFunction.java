package net.ozsofts.wechat.functions;

import java.util.Map;

public interface TokenFunction {
	public Map<String, Object> getToken(String appId, String secret) throws Exception;
}

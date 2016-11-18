package net.ozsofts.wechat.message.req;

import java.util.Map;

import org.apache.commons.lang.StringUtils;

/**
 * 包括：
 * <ul>
 * <li>CLICK</li>
 * <li>VIEW</li>
 * </ul>
 * 
 * @author jack
 *
 */
public final class MenuEvent extends EventMessage {

	private String eventKey;
	private String menuId;

	public void parse(Map<String, String> params) {
		super.parse(params);

		this.eventKey = params.get("EventKey");
		if (params.containsKey("MenuId")) {
			this.menuId = params.get("MenuId");
		}
	}

	public String getEventKey() {
		return eventKey;
	}

	@Override
	public String getEvent() {
		return super.getEvent();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString()).append("|");
		sb.append(eventKey);
		if (StringUtils.isNotBlank(menuId)) {
			sb.append("|").append(menuId);
		}
		return sb.toString();
	}
}

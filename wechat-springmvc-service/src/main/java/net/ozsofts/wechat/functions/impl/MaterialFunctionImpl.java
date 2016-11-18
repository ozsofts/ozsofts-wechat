package net.ozsofts.wechat.functions.impl;

import org.springframework.beans.factory.annotation.Autowired;

import net.ozsofts.wechat.functions.MaterialFunction;
import net.ozsofts.wechat.service.WechatCommService;

public class MaterialFunctionImpl implements MaterialFunction {
	@Autowired
	private WechatCommService wechatCommService;

}

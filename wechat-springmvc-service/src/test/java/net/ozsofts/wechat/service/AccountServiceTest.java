package net.ozsofts.wechat.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.ozsofts.core.db.dao.Dao;
import net.ozsofts.wechat.core.entity.WxUser;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-application.xml" })
public class AccountServiceTest {
	@Autowired
	private Dao<WxUser> wxUserDao;
	@Autowired
	private AccountService accountService;

	@Before
	public void clear() {
		wxUserDao.batchUpdate("SET FOREIGN_KEY_CHECKS=0");
		wxUserDao.batchUpdate("truncate table t_wx_users");
		wxUserDao.batchUpdate("truncate table t_wx_groups");
	}

	// @Ignore
	@Test
	public void testInitAccount() throws Exception {
		accountService.initAccount("ikdyserv");
	}
}

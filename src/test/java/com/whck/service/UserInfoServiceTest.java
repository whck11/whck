package com.whck.service;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.whck.dmo.UserInfo;
/**
 * 
 * @author 马健原 2016-1-24
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Transactional
public class UserInfoServiceTest {
	@Autowired
	protected UserInfoService userInfoService;

	@Test
	public void testFindUserInfoByUserName() {
		UserInfo user = this.userInfoService.findByUserName("whck@163.com");
		System.out.println(user);
	}
}

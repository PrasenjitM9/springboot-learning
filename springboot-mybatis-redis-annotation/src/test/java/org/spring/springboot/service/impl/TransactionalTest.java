package org.spring.springboot.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionalTest {
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private IUserService userService;

	
	@Test
	public void test() {
		userService.testTransactional();
	}
}
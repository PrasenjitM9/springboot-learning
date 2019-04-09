package cn.bocon.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bocon.service.IUserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TransactionalTest {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private IUserService userService;

	
	@Test
	public void test() {
		userService.testTransactional();
	}
}
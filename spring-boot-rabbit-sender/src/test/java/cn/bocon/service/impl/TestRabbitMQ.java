package cn.bocon.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bocon.Application;
import cn.bocon.HelloSender;
import cn.bocon.HelloSender2;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

	@Autowired
	private HelloSender helloSender;
	
	@Autowired
	private HelloSender2 helloSender2;

	@Test
	public void sendStr() {
		String str = "测试测试";
		helloSender.send0(str);

	}	
	
	@Test
	public void sendOsdStr() {
		String str = "测试测试2222222";
		helloSender.sendOsd(str);

	}		
	
	@Test
	public void testSerial() {
		helloSender.send();

	}

	@Test
	public void testRabbit2() {
		helloSender.sendTopic();

	}

	@Test
	public void testRabbit3() {
		helloSender.sendFanout();
	}
	
	@Test
	public void oneToMany() {
		for(int i = 0; i < 100; i++) {
			helloSender.sendOneToMany(String.valueOf(i));
		}
	}	
	
	@Test
	public void manyToMany() {
		for(int i = 0; i < 100; i++) {
			helloSender.sendOneToMany(String.valueOf(i));
			helloSender2.sendOneToMany(String.valueOf(i));
		}
	}		
}

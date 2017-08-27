package cn.bocon.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.bocon.entity.User;
import cn.bocon.service.IUserService;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RedisTest {
	private Logger logger = LogManager.getLogger(getClass());
	@Autowired
	private IUserService userService;

	@Before
	public void before() {
		User user = new User();
		user.setName("redis测试");
		user.setAge(10);
		user.setBirthday("2017-08-19");
		userService.insert(user);
	}	
	
	@Test
	public void test() {
		Wrapper<User> wrapper = new EntityWrapper<>();
		wrapper.eq("name", "redis测试");
		User u1 = userService.selectOne(wrapper);
		System.out.println("第一次查询：" + u1.getAge());

		User u2 = userService.selectOne(wrapper);
		System.out.println("第二次查询：" + u2.getAge());

		u1.setAge(20);
		userService.updateById(u1);
		User u3 = userService.selectOne(wrapper);
		System.out.println("第三次查询：" + u3.getAge());		
	}
}
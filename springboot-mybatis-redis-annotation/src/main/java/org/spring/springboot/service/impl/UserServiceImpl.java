package org.spring.springboot.service.impl;

import java.io.Serializable;

import org.spring.springboot.entity.User;
import org.spring.springboot.mapper.UserMapper;
import org.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
public class UserServiceImpl  extends ServiceImpl<UserMapper, User>  implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	@Cacheable(value = "user",key="'id_'+#id")
	public User selectById(Serializable id) {
		return userMapper.selectById(id);
	}
}

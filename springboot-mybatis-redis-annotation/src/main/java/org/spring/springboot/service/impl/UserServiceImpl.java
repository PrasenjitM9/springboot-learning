package org.spring.springboot.service.impl;

import java.io.Serializable;

import org.spring.springboot.entity.User;
import org.spring.springboot.mapper.UserMapper;
import org.spring.springboot.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
@CacheConfig(cacheNames = "users")
public class UserServiceImpl  extends ServiceImpl<UserMapper, User>  implements IUserService {
	@Autowired
	private UserMapper userMapper;
	
	
	@Cacheable(key="'id_'+#id")
	@Override
	public User selectById(Serializable id) {
		return super.selectById(id);
	}
	
	@CacheEvict(key="'id_'+#id")  
	@Override
	public boolean deleteById(Serializable id) {
		return super.deleteById(id);
	}
	
	@CachePut(key="'id_'+#id")
	@Override
	public boolean updateById(User user) {
		return super.updateById(user);
	}
	
}
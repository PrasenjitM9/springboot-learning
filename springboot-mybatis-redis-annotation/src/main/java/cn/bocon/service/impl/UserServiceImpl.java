package cn.bocon.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.bocon.entity.User;
import cn.bocon.mapper.UserMapper;
import cn.bocon.service.IUserService;

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
	
	@Transactional
	public void testTransactional() {
		User user = new User();
		user.setName("事务测试");
		user.setAge(10);
		user.setBirthday("2017-08-19");		
		insert(user);
		User user2 = new User();
		user2.setName("事务测试2");
		user2.setAge(10);
		user2.setBirthday("2017-08-19");			
		insert(user2);
	}
}

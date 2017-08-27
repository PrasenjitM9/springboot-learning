package cn.bocon.service;

import cn.bocon.entity.User;

import com.baomidou.mybatisplus.service.IService;

public interface IUserService extends IService<User> {
	public void testTransactional();
}

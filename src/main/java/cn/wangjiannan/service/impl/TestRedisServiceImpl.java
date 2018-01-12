package cn.wangjiannan.service.impl;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.model.User;
import cn.wangjiannan.service.TestRedisService;

@Service
public class TestRedisServiceImpl extends ServiceImpl<UserMapper, User> implements TestRedisService {
	/*
	 * value:缓存的名字;key:缓存key
	 */
	@Override
	@CachePut(value = "halfHour", key = "#user.id")
	public User save(User user) {
		System.out.println("save user");
		return user;
	}

	@Override
	@Cacheable(value = "halfHour", key = "#id")
	public User select(Long id) {
		System.out.println("cache miss, invoke find by id, id:" + id);
		Long id1 = 2L;
		User user = new User();
		user.setId(id1);
		user.setName("b");
		return user;

	}
}

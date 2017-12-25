package cn.wangjiannan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.UserService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public List<User> selectByLoginName(UserVo userVo) {
		User user = new User();
		user.setLoginName(userVo.getLoginName());
		// EntityWrapper实体包装器、封装器
		EntityWrapper<User> wrapper = new EntityWrapper<User>(user);
		if (null != userVo.getId()) {
			wrapper.where("id != {0}", userVo.getId());
		}
		return this.selectList(wrapper);
	}

	/*
	 * value:缓存的名字;key:缓存key
	 */
	@CachePut(value = "halfHour", key = "#user.id")
	public User save(User user) {
		System.out.println("save user");
		return user;
	}

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

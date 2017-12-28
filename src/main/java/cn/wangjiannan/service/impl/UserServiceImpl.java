package cn.wangjiannan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import cn.wangjiannan.mapper.UserMapper;
import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.PageVo;
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

	// @Override
	// public void selectDataGrid(PageInfo pageInfo) {
	// Page<Map<String, Object>> page = new Page<Map<String, Object>>(pageInfo.getNowpage(), pageInfo.getSize());
	// page.setOrderByField(pageInfo.getSort());
	// page.setAsc(pageInfo.getOrder().equalsIgnoreCase("asc"));
	// List<Map<String, Object>> list = userMapper.selectUserPage(page, pageInfo.getCondition());
	// pageInfo.setRows(list);
	// pageInfo.setTotal(page.getTotal());
	// }

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

	@Override
	public void selectUserPage(PageVo<User> pageVo) {
		Page<User> page = new Page<>(pageVo.getNowpage(), pageVo.getPagesize());
		// page.setOrderByField(pageVo.getSort());
		// page.setAsc(pageVo.getOrder().equalsIgnoreCase("asc"));
		System.out.println("---------" + page.toString());
		userMapper.selectUserPage(page, pageVo.getCondition());
		pageVo.setRows(page.getRecords());
		pageVo.setTotal(page.getTotal());

	}

}

package cn.wangjiannan.test;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.UserService;
import cn.wangjiannan.test.base.BaseTest;

public class UserTest extends BaseTest {
	@Autowired
	private UserService userService;

	@Test
	public void testUser() {
		UserVo userVo = new UserVo();
		userVo.setLoginName("admin");
		// userVo.setId(1L);
		List<User> list = userService.selectByLoginName(userVo);
		logger.info("-----" + list.get(0).toString());
	}
}

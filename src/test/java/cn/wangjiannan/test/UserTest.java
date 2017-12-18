package cn.wangjiannan.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath*:/applicationContext.xml", "classpath*:/applicationContext-springMVC.xml" })
public class UserTest extends AbstractJUnit4SpringContextTests {
	private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
	@Autowired
	private UserService userService;

	@Test
	public void testUser() {
		UserVo userVo = new UserVo();
		userVo.setLoginName("admin");
		// userVo.setId(1L);
		List<User> list = userService.selectByLoginName(userVo);
	}
}

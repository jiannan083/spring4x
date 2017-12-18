package cn.wangjiannan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.common.base.BaseController;
import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.UserService;

/**
 * 测试
 * 
 * @author wangjiannan
 *
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	@Autowired
	private UserService userService;

	@RequestMapping("/test1")
	@ResponseBody
	public void test1() {
		logger.info("-------------------------------");
		User user = userService.selectById(1L);
		System.out.println("---------" + user.getName());
		System.out.println("----------------");
		UserVo userVo = new UserVo();
		userVo.setLoginName("admin");
		// userVo.setId(1L);
		List<User> list = userService.selectByLoginName(userVo);
		logger.info("-----" + list.get(0).toString());

	}

}

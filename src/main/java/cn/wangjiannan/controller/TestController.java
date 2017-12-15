package cn.wangjiannan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.common.base.BaseController;
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
		userService.getUserById(1L);
	}

}

package cn.wangjiannan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wangjiannan.common.base.BaseController;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	/**
	 * 用户管理页
	 * 
	 * @author wangjiannan
	 * @date 2017年12月14日 下午3:50:02
	 *
	 * @return
	 */
	@GetMapping("/manager")
	public String manager() {
		return "admin/user/user";
	}

}

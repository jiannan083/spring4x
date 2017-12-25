package cn.wangjiannan.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.wangjiannan.common.base.BaseController;
import cn.wangjiannan.common.shiro.captcha.DreamCaptcha;
import cn.wangjiannan.common.util.StringUtils;

/**
 * 登录退出
 * 
 * @author wangjiannan
 * @date 2017年12月20日 上午9:37:18
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private DreamCaptcha dreamCaptcha;

	@RequestMapping("/login")
	@ResponseBody
	public Object login(HttpServletRequest request, HttpServletResponse response, String username, String password, String captcha,
			@RequestParam(value = "rememberMe", defaultValue = "0") Integer rememberMe) {
		if (StringUtils.isBlank(username)) {
			throw new RuntimeException("用户名不能为空");
		}
		if (StringUtils.isBlank(password)) {
			throw new RuntimeException("密码不能为空");
		}
		if (StringUtils.isBlank(captcha)) {
			throw new RuntimeException("验证码不能为空");
		}
		if (!dreamCaptcha.validate(request, response, captcha)) {
			throw new RuntimeException("验证码错误");
		}
		// 1.创建Subject实例
		Subject user = SecurityUtils.getSubject();
		// 2.判断当前用户是否登录
		// if (user.isAuthenticated() == false) {
		// 3.将用户名和密码封装UsernamePasswordToken
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		try {
			user.login(token);
			return renderSuccess();
		} catch (UnknownAccountException e) {
			throw new RuntimeException("账号不存在！", e);
		} catch (DisabledAccountException e) {
			throw new RuntimeException("账号未启用！", e);
		} catch (IncorrectCredentialsException e) {
			throw new RuntimeException("密码错误！", e);
		} catch (Throwable e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		// }
		// return null;
	}

}

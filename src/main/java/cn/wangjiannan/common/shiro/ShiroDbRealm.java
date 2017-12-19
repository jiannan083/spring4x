package cn.wangjiannan.common.shiro;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.RoleService;
import cn.wangjiannan.service.UserService;

/**
 * shiro权限认证
 * 
 * 在认证、授权内部实现机制中都有提到，最终处理都将交给Real进行处理。因为在Shiro中，
 * 
 * 最终是通过Realm来获取应用程序中的用户、角色及权限信息的。通常情况下，在Realm中会
 * 
 * 直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO.
 * 
 * @author wangjiannan
 * @date 2017年12月17日 下午1:19:24
 */
public class ShiroDbRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(ShiroDbRealm.class);

	@Autowired
	private UserService userService;
	private RoleService roleService;

	/**
	 * 认证实现:继承AuthorizingRealm抽象类，重载doGetAuthenticationInfo ()，重写获取用户信息的方法.
	 * 
	 * Shiro登录认证(原理：用户提交 用户名和密码 --- shiro 封装令牌 ---- realm通过用户名将密码查询返回
	 * 
	 * ---- shiro 自动去比较查询出密码和用户输入密码是否一致---- 进行登陆控制 )
	 * 
	 * @author wangjiannan
	 * @date 2017年12月18日 下午1:28:33
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		logger.info("Shiro开始登录认证");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		UserVo userVo = new UserVo();
		userVo.setLoginName(token.getUsername());
		List<User> list = userService.selectByLoginName(userVo);
		// 账号不存在
		if (list == null || list.isEmpty()) {
			return null;
		}
		User user = list.get(0);
		// 账号未启用
		if (user.getStatus() == 1) {
			return null;
		}
		// 读取用户的url和角色
		Map<String, Set<String>> resourceMap = roleService.selectResourceMapByUserId(user.getId());
		Set<String> urls = resourceMap.get("urls");
		Set<String> roles = resourceMap.get("roles");
		ShiroUser shiroUser = new ShiroUser(user.getId(), user.getLoginName(), user.getName(), urls);
		shiroUser.setRoles(roles);
		// 认证缓存信息
		return new SimpleAuthenticationInfo(shiroUser, user.getPassword().toCharArray(), ShiroByteSource.of(user.getSalt()), getName());
	}

	/**
	 * Shiro权限认证
	 * 
	 * 授权实现:则与认证实现非常相似，在我们自定义的Realm中，重载doGetAuthorizationInfo()方法，重写获取用户权限的方法即可
	 * 
	 * @author wangjiannan
	 * @date 2017年12月18日 下午1:28:37
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		// TODO Auto-generated method stub
		return null;
	}

}

package cn.wangjiannan.service;

import java.util.List;

import cn.wangjiannan.common.result.Tree;
import cn.wangjiannan.common.shiro.ShiroUser;

public interface ResourceService {
	List<Tree> selectTree(ShiroUser shiroUser);
}

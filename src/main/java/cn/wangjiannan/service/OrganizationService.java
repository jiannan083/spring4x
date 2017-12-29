package cn.wangjiannan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.common.result.Tree;
import cn.wangjiannan.model.Organization;

public interface OrganizationService extends IService<Organization> {
	List<Tree> selectTree();

	List<Organization> selectTreeGrid();
}

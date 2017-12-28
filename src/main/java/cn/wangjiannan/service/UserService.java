package cn.wangjiannan.service;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

import cn.wangjiannan.model.User;
import cn.wangjiannan.model.vo.PageVo;
import cn.wangjiannan.model.vo.UserVo;

public interface UserService extends IService<User> {

	List<User> selectByLoginName(UserVo userVo);

	// void selectDataGrid(PageInfo pageInfo);

	User save(User user);

	User select(Long id);

	void selectUserPage(PageVo<User> pageVo);
}

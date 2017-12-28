package cn.wangjiannan.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.wangjiannan.common.base.BaseController;
import cn.wangjiannan.common.result.PageInfo;
import cn.wangjiannan.common.util.StringUtils;
import cn.wangjiannan.model.vo.UserVo;
import cn.wangjiannan.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

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

	@PostMapping("/dataGrid")
	public Object dataGrid(UserVo userVo, Integer page, Integer rows, String sort, String order) {
		// page:第几页;rows:每页显示条数;sort:排序字段;order:asc/desc
		PageInfo pageInfo = new PageInfo(page, rows, sort, order);
		Map<String, Object> condition = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(userVo.getName())) {
			condition.put("name", userVo.getName());
		}
		if (userVo.getOrganizationId() != null) {
			condition.put("organizationId", userVo.getOrganizationId());
		}
		if (userVo.getCreatedateStart() != null) {
			condition.put("startTime", userVo.getCreatedateStart());
		}
		if (userVo.getCreatedateEnd() != null) {
			condition.put("endTime", userVo.getCreatedateEnd());
		}
		pageInfo.setCondition(condition);
		// userService.selectDataGrid(pageInfo);
		return null;
	}

}

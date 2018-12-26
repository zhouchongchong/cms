package com.cloudminds.cms.controller;

import com.cloudminds.cms.entity.mysql.User;
import com.cloudminds.cms.service.UserService;
import com.cloudminds.cms.vo.BaseResp;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:29 2018/11/29.
 * @Description:
 */
@RestController
@RequestMapping("/user/")
public class UserController extends BaseController {
	private static String sys ="sys.user";

	private static final Logger _log = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 新增用户
	 *
	 * @return
	 */
	@GetMapping("add_user")
	@ApiOperation(value = "新增用户")
	public BaseResp addUser(){
		BaseResp baseResp = new BaseResp();
		baseResp.setSys(sys);
		baseResp.setData(userService.save(new User()));
		return baseResp;
	}

}

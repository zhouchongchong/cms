package com.cloudminds.cms.controller;

import com.cloudminds.cms.entity.mongo.User;
import com.cloudminds.cms.vo.BaseResp;
import com.cloudminds.cms.vo.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouchong
 * Created by 76409 on 11:04 2018/12/6.
 * @Description:
 */
@RestController
@RequestMapping("/cmsUser/")
public class LoginController extends BaseController {

	public static final Logger _log = LoggerFactory.getLogger(LoginController.class);

	private static final String sys = "sys.login";

	@Autowired
	@Qualifier("userAuthenticationManager")
	private AuthenticationManager authenticationManager;

	@ApiOperation(value = "login")
	@PostMapping("/v1/login")
	public BaseResp login(@RequestBody LoginUser loginUser){
		_log.info("********** login user:{}", loginUser);
		BaseResp baseResp = new BaseResp();
		baseResp.setSys(sys);

		String userName = loginUser.getUserName();
		String password = loginUser.getPassword();

		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userName, password);

		Authentication authenticate = authenticationManager.authenticate(authenticationToken);

		SecurityContextHolder.getContext().setAuthentication(authenticate);

		if (authenticate.isAuthenticated()){
			final User authUser = (User)authenticate.getPrincipal();

//			if(!StringUtils.isEmpty())
		}


		return baseResp;
	}

}

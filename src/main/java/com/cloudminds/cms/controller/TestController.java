package com.cloudminds.cms.controller;

import com.cloudminds.cms.vo.BaseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhouchong
 * Created by 76409 on 17:24 2018/12/10.
 * @Description:
 */
@RestController
@RequestMapping("/test/")
@Api(value = "test",description = "测试")
public class TestController extends BaseController {
	public static final String sys = "sys.test";

	@ApiOperation("测试认证")
	@GetMapping("auth")
	public BaseResp authenticationTest(String t){
		BaseResp baseResp = new BaseResp();
		baseResp.setSys(sys);
		baseResp.setData("test auth");
		return baseResp;
	}

	@ApiOperation("url攔截測試")
	@GetMapping("authUrl")
	public BaseResp urlAuthTest(String t){
		BaseResp baseResp = new BaseResp();
		baseResp.setSys(sys);
		baseResp.setData("test url");
		return baseResp;
	}
}

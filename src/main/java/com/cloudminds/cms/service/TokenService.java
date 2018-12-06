package com.cloudminds.cms.service;

import com.cloudminds.cms.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:59 2018/12/6.
 * @Description:
 */
@Component
public class TokenService {
	public static final Logger _log = LoggerFactory.getLogger(TokenService.class);
	@Value("${com.cloudminds.user.token.ttl}")
	private Long tokenTTL;

	@Autowired
	private RedisUtil redisUtil;

}

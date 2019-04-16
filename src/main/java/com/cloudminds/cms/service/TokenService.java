package com.cloudminds.cms.service;

import com.cloudminds.cms.entity.mysql.User;
import com.cloudminds.cms.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.UUID;

//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.security.authentication.BadCredentialsException;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:59 2018/12/6.
 * @Description:
 */
@Component
//@RefreshScope
public class TokenService {
	public static final Logger _log = LoggerFactory.getLogger(TokenService.class);
	@Value("${com.cloudminds.user.token.ttl}")
	private Long tokenTTL;

	public Long getTokenTTL() {
		return tokenTTL;
	}

	@Autowired
	private RedisUtil redisUtil;


	public String getToken(String userName) {
		Assert.notNull(userName, "Redis get kv key can't be null");
		return redisUtil.getStrValue(userName);
	}

	/**
	 * 生成token
	 *
	 * @return
	 */
	public String generateNewToken() {
		return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
	}

	public void store(User user) {
		redisUtil.setValue(user.getToken(), user, tokenTTL);
		redisUtil.setValue(user.getUsername(), user.getToken(), tokenTTL);
	}

	public User reStore(String token) {
		User user = null;
		try {
			Assert.notNull(token, "TOKEN CANT BE NULL");
			user = (User) redisUtil.getValue(token);
			Assert.notNull(user, "REDIS CACHE EXPIRED");
			redisUtil.expire(token, tokenTTL);
			redisUtil.expire(user.getUsername(), tokenTTL);
		} catch (Exception e) {
			_log.error("CANT GET USER");
		} finally {
			return user;
		}

	}

	public boolean delToken(String token){
		User user = null;
		try {
			Assert.notNull(token, "TOKEN CANT BE NULL");
			user = (User) redisUtil.getValue(token);
			Assert.notNull(user, "REDIS CACHE EXPIRED");
			redisUtil.deleteKey(token);
			redisUtil.deleteKey(user.getUsername());
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}

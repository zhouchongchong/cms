package com.cloudminds.cms.service;

import com.cloudminds.cms.config.exception.LoginException;
import com.cloudminds.cms.dao.mongo.UserDao;
import com.cloudminds.cms.entity.mongo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Author: zhouchong
 * Created by 76409 on 14:28 2018/12/7.
 * @Description:
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user  = userService.findByName(username);
		if (StringUtils.isEmpty(user)){
			throw new LoginException(LoginException.NOT_EXIST,"USER CON'T FIND");
		}

		return null;
	}
}

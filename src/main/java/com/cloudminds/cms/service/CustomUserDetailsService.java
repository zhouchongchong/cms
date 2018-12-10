package com.cloudminds.cms.service;

import com.cloudminds.cms.config.exception.LoginException;
import com.cloudminds.cms.entity.mongo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Author: zhouchong
 * Created by 76409 on 14:28 2018/12/7.
 * @Description:
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserService userService;
	@Autowired
	MongoTemplate mongoTemplate;
	@Override
	public User loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user  = userService.findByName(username);
		if (StringUtils.isEmpty(user)){
			throw new UsernameNotFoundException("USER CON'T FIND");
		}

		if (user.getStatus() != 1){
			throw new LoginException(LoginException.NOT_EXIST,"USER HAD BE DELETED");
		}

		user.setAccountNonExpired(true);
		user.setAccountNonLocked(true);
		user.setEnabled(true);
		user.setCredentialsNonExpired(true);


		return user;
	}
}

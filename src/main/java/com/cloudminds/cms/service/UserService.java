package com.cloudminds.cms.service;

import com.cloudminds.cms.dao.mysql.UserMapper;
import com.cloudminds.cms.entity.mysql.User;
import com.cloudminds.cms.entity.mysql.UserExample;
import com.cloudminds.cms.utils.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:31 2018/11/29.
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
	@Autowired
	private UserMapper userMapper;

	private static String sys = "******* US";

	private static final Logger _log = LoggerFactory.getLogger(UserService.class);

	/**
	 * 添加用户
	 *
	 * @param user
	 * @return
	 */
	public User save (User user){
		user.setUsername("zhouchong");
		user.setLogincount(0L);

		user.setStatus(Byte.parseByte("1"));
		user.setRegistertime(new Date());
		user.setPassword(PasswordUtil.digest("123456"));

		int save = userMapper.insert(user);
		_log.info("{}, User: {}, is save:{}",sys,user,save);

		return user;
	}

	public User findByName(String userName){
		Map<String, Object> queryMap = new HashMap<>();
		queryMap.put("userName",userName);
		User user = null;
		try {
			UserExample userExample = new UserExample();
			UserExample.Criteria criteria = userExample.createCriteria();
			criteria.andUsernameEqualTo(userName);
			List<User> users = userMapper.selectByExample(userExample);
			if (users.isEmpty()){
				throw new UsernameNotFoundException("USER CON'T FIND");
			}
			user = users.get(0);
		}catch (IndexOutOfBoundsException e){
			throw new UsernameNotFoundException("USER CON'T FIND");
		} finally {
			return user;
		}
	}
}

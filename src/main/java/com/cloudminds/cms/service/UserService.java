package com.cloudminds.cms.service;

import com.cloudminds.cms.dao.mongo.UserDao;
import com.cloudminds.cms.entity.mongo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:31 2018/11/29.
 * @Description:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService {
	@Autowired
	private UserDao userDao;

	private static String sys = "******* US";

	private static final Logger _log = LoggerFactory.getLogger(UserService.class);

	/**
	 * 添加用户
	 *
	 * @param user
	 * @return
	 */
	public User save (User user){
		user.setAdmin(true);
		user.setGroupId(1);
		user.setLoginCount(0L);
		user.setStatus(1);
		user.setRegisterTime(new Date());
		user.setPassWord("1231231");

		boolean save = userDao.save(user);
		_log.info("{}, User: {}, is save:{}",sys,user,save);

		return user;
	}
}

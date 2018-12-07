package com.cloudminds.cms.config.security;

import com.cloudminds.cms.utils.PasswordUtil;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: zhouchong
 * Created by 76409 on 13:47 2018/12/7.
 * @Description:
 */
public class CustomPasswordEncoder implements PasswordEncoder {
	@Override
	public String encode(CharSequence rawPassword) {
		return PasswordUtil.digest(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		return encodedPassword.equals(PasswordUtil.digest(rawPassword.toString()));
	}
}

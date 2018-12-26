package com.cloudminds.cms.config.exception;

import lombok.Data;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

/**
 * @Author: zhouchong
 * Created by 76409 on 18:55 2018/12/6.
 * @Description:
 */
@Data
public class LoginException extends InternalAuthenticationServiceException {
	public static final String WRONG_PWD = "-1";
	public static final String ALREADY_LOGIN = "-2";
	public static final String NOT_EXIST = "-3";

	private String code;

	public LoginException(String message, Throwable cause) {
		super(message, cause);
	}

	public LoginException(String message) {
		super(message);
	}

	public LoginException(String code, String message) {
		super(message);
		this.code = code;
	}
}

package com.cloudminds.cms.config.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Author: zhouchong
 * Created by 76409 on 15:39 2018/12/27.
 * @Description:
 */
@Component
public class PermissionAccessDecisionManager implements AccessDecisionManager {

	/**
	 * decide 方法是判定是否拥有权限的决策方法，authentication是CustomUserService
	 * 中循环添加到 GrantedAuthority 对象中的权限信息集合,object 包含客户端发起的请求的requset信息，
	 * 可转换为 HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
	 * configAttributes为MyFilterInvocationSecurityMetadataSource的getAttributes(Object object)
	 * 这个方法返回的结果.
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null || configAttributes.isEmpty()){
			return;
		}
		ConfigAttribute cfb;
		String needkey;
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()){
			 cfb = iterator.next();
			 needkey = cfb.getAttribute();
			 for (GrantedAuthority ga: authentication.getAuthorities()){
				 if (needkey.trim().equals(ga.getAuthority())){
				 	return;
				 }
			 }
		}

		throw new AccessDeniedException("no right");
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}

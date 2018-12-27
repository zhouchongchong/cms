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
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
		if (configAttributes == null || configAttributes.isEmpty()){
			return;
		}
		ConfigAttribute cfb;
		String needRole;
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()){
			 cfb = iterator.next();
			 needRole = cfb.getAttribute();
			 for (GrantedAuthority ga: authentication.getAuthorities()){
				 if (needRole.trim().equals(ga.getAuthority())){
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

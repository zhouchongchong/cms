package com.cloudminds.cms.config.security;

import com.cloudminds.cms.dao.mysql.PermissionMapper;
import com.cloudminds.cms.entity.mysql.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: zhouchong
 * Created by 76409 on 15:34 2018/12/25.
 * @Description:
 */
@Component
public class URLFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	@Autowired
	PermissionMapper permissionMapper;

	private Map<String,Collection<ConfigAttribute>> map;

	private void loadResourceDefine(){
		map = new HashMap<>();

		List<Permission> permissions = permissionMapper.findAll();

		for (Permission permission:permissions){
			if (permission.getPermissionKey()==null){
				continue;
			}
			SecurityConfig securityConfig = new SecurityConfig(permission.getPermissionKey());
			List<ConfigAttribute> list = new ArrayList<>();
			list.add(securityConfig);
			map.put(permission.getPermissionUrl(),list);
		}
	}
	/**
	 * 此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法， 用来判定用户
	 * 是否有此权限。如果不在权限表中则放行
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map ==null || map.isEmpty()){
			loadResourceDefine();
		}

		HttpServletRequest request = ((FilterInvocation)object).getHttpRequest();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry: map.entrySet()){
			if (new AntPathRequestMatcher(entry.getKey()).matches(request)){
				return map.get(entry.getKey());
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}
}

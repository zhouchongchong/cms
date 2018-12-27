package com.cloudminds.cms.config.security;

import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @Author: zhouchong
 * Created by 76409 on 14:06 2018/12/27.
 * @Description:
 */
public class URLPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {
	private URLFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource;
	private PermissionAccessDecisionManager permissionAccessDecisionManager;

	public URLPostProcessor(URLFilterInvocationSecurityMetadataSource urlFilterInvocationSecurityMetadataSource, PermissionAccessDecisionManager permissionAccessDecisionManager) {
		this.urlFilterInvocationSecurityMetadataSource = urlFilterInvocationSecurityMetadataSource;
		this.permissionAccessDecisionManager = permissionAccessDecisionManager;
	}

	@Override
	public <O extends FilterSecurityInterceptor> O postProcess(O object) {
		object.setSecurityMetadataSource(urlFilterInvocationSecurityMetadataSource);
		object.setAccessDecisionManager(permissionAccessDecisionManager);
		return object;
	}
}

package com.cloudminds.cms.config.security;

import com.cloudminds.cms.constant.ConstantBean;
import com.cloudminds.cms.entity.mongo.User;
import com.cloudminds.cms.service.TokenService;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @Author: zhouchong
 * Created by 76409 on 16:26 2018/12/10.
 * @Description:
 */
public class TokenAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

	private TokenService tokenService;

	public void setTokenService(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	protected TokenAuthenticationFilter(String defaultFilterProcessesUrl) {
		super(defaultFilterProcessesUrl);
	}

	protected TokenAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
		final String token = Optional
				.ofNullable(request.getHeader(ConstantBean.AUTH_HEADER))
				.orElse(request.getParameter("t"));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (StringUtils.isEmpty(authentication)){
			if (token == null){
				throw new BadCredentialsException("CANT GET AUTHENTICATION");
			}
			User user = tokenService.reStore(token);
			if (user == null){
				throw new BadCredentialsException("CANT GET USER");
			}
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user,null,user.getAuthorities());
			authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			return  authenticationToken;
		}
		return authentication;
	}

	@Override
	protected void successfulAuthentication(
			final HttpServletRequest request,
			final HttpServletResponse response,
			final FilterChain chain,
			final Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
}

package com.cloudminds.cms.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @Author: zhouchong
 * Created by 76409 on 9:58 2018/11/30.
 * @Description:
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	/**
	 * 公开的访问链接
	 */
	private static final RequestMatcher PUBLIC_URLS = new OrRequestMatcher(
			new AntPathRequestMatcher("/swagger-ui.html/**"),
			new AntPathRequestMatcher("/swagger-resources/**"),
			new AntPathRequestMatcher("/webjars/**"),
			new AntPathRequestMatcher("/configuration/ui"),
			new AntPathRequestMatcher("/configuration/security"),
			new AntPathRequestMatcher("/v2/api-docs"),
			new AntPathRequestMatcher("/user/add_user")
			);

	private static final RequestMatcher PROTECTED_URLS = new NegatedRequestMatcher(PUBLIC_URLS);

	/**
	 * 对http 请求进行拦截
	 * 配置拦截信息 并针对性过滤
	 *
	 * @param http
	 * @throws Exception
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/index").permitAll()
				.anyRequest().authenticated()
				.and()
				.exceptionHandling()
				.defaultAuthenticationEntryPointFor(forbiddenEntryPoint(), PROTECTED_URLS)
				.and()
//				.formLogin()
//				.loginPage("/login").permitAll()
//				.and()
				.logout().permitAll();
	}

	/**
	 * 用户登录验证
	 *
	 * @param auth
	 * @throws Exception
	 */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("zhouchong").password("zhouchong510").roles("ADMIN");
	}

	/**
	 * @param web
	 * @throws Exception
	 */
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PUBLIC_URLS);
	}

	@Bean
	AuthenticationEntryPoint forbiddenEntryPoint() {
		return new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED);
	}
}

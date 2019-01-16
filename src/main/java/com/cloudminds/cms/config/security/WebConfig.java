package com.cloudminds.cms.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: zhouchong
 * Created by 76409 on 10:52 2019/1/9.
 * @Description:
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
				.allowedHeaders("*")
				.allowedMethods("*")
				.allowedOrigins("*");
	}
}

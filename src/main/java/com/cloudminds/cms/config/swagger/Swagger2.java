package com.cloudminds.cms.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Tohru
 * Created by admin on 17:27 2018/6/4.
 * @Description:
 */
@Configuration
@EnableSwagger2
public class Swagger2 {
	@Bean
	public Docket customDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.cloudminds.cms.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(apiInfo())
				.enableUrlTemplating(false);
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("API文档")
				.description("CMS系统API文档")
				.contact(new Contact("cloudminds", "http://www.cloudminds.com", "a@cloudminds.com"))
				.version("0.0.1")
				.build();
	}

}

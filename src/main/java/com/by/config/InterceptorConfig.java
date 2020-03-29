package com.by.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.by.filter.AuthorizationInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer  {

	@Override
	 public void addInterceptors(InterceptorRegistry registry) {
       // 拦截所有请求，通过判断是否有 @Authorize 注解 决定是否需要登录
       registry.addInterceptor(AuthorizationInterceptor()).addPathPatterns("/**");
	 }
	 
	 @Bean
	 public AuthorizationInterceptor AuthorizationInterceptor() {
		 return new AuthorizationInterceptor();
	 }
	
}

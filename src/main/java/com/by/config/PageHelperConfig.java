package com.by.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pagehelper.PageInterceptor;

@Configuration
public class PageHelperConfig {

	@Bean
	public PageInterceptor getPageInterceptor() {
		PageInterceptor pageIntercptor = new PageInterceptor();
		Properties properties = new Properties();
		properties.setProperty("value", "true");
		pageIntercptor.setProperties(properties);
		return pageIntercptor;
	}

}
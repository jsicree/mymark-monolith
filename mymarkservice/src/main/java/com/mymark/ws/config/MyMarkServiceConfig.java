package com.mymark.ws.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.mymark.ws" })
public class MyMarkServiceConfig implements WebMvcConfigurer {

	@Resource
	private Environment environment;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");

	}

	@Bean
	public Validator newCustomerRequestValidator() {
		return new com.mymark.ws.validator.NewCustomerRequestValidator();
	}
	
}
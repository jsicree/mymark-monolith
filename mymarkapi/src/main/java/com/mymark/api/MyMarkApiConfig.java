package com.mymark.api;

import javax.annotation.Resource;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;


@Configuration
@ComponentScan(basePackages = { "com.mymark.api" })
public class MyMarkApiConfig {

	@Resource
	private Environment environment;


}
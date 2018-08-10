package com.mymark.ws.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mymark.app.config.MyMarkAppConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] { MyMarkAppConfig.class, MyMarkSecurityConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MyMarkServiceConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] { "/" };
	}
	
}

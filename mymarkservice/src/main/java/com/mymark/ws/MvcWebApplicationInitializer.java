package com.mymark.ws;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.mymark.app.config.SpringConfig;

public class MvcWebApplicationInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
//		return new Class[] { SpringConfig.class, MultipleWebSecurityConfig.class };
		return new Class[] { SpringConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] { MvcWebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String [] { "/" };
	}
	
}

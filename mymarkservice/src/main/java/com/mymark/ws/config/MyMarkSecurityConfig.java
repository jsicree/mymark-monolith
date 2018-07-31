package com.mymark.ws.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

@EnableWebSecurity
public class MyMarkSecurityConfig {

	@Resource
	private Environment environment;

	protected final static Logger log = LoggerFactory.getLogger(MyMarkSecurityConfig.class);

	@Value("${SECURITY_DB_DRIVER_CLASS}")
	private String dbDriverClassName;

	@Value("${SECURITY_DB_URL}")
	private String dbUrl;

	@Value("${SECURITY_DB_USERNAME}")
	private String dbUserName;
	
	@Value("${SECURITY_DB_PASSWORD}")
	private String dbPassword;
		
	@Autowired
	private DataSource securityDataSource;

	@Bean
	public DriverManagerDataSource securityDataSource() {

		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(dbDriverClassName);
		ds.setUrl(dbUrl);
		ds.setUsername(dbUserName);
		ds.setPassword(dbPassword);
		return ds;
	}

	@Bean
	public UserDetailsService userDetailsService() {
		JdbcDaoImpl dao = new JdbcDaoImpl();
		dao.setDataSource(securityDataSource);
		return dao;
	}
	
	
	
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//                .withUser("user").password("{noop}password").roles("WEBAPP");                
//    }
    
	@Configuration
	@Order(1)                                                        
	public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
		protected void configure(HttpSecurity http) throws Exception {
			http.csrf().disable()
				.antMatcher("/api/**")
				.authorizeRequests()
					.anyRequest().access("hasRole('ROLE_WEBAPP')")
					.and()
				.httpBasic();
		}
	}
    
	
	
}
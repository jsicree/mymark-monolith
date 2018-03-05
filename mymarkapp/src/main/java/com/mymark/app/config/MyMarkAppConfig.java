package com.mymark.app.config;

import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Properties;

import javax.annotation.Resource;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

//
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mymark.app.service.GreetingService;
import com.mymark.app.service.impl.GreetingServiceImpl;


// import org.springframework.dao.QueryTimeoutException;

/**
 * Spring configuration class. This class uses a property file
 * <code>config.properties</code> to configure the data source and other
 * beans.
 * 
 * @author jsicree
 * 
 */
@Configuration
@EnableJpaRepositories(basePackages = { "com.mymark.app.jpa.repository" })
@PropertySource({ "classpath:mymarkapp.properties" })
//@IntegrationComponentScan("com.mymark.app.integration")
//@EnableIntegration
//@EnableCaching
public class MyMarkAppConfig {

	private static final String PROPERTY_NAME_DATABASE_DRIVER_CLASS = "db.driverClass";
	private static final String PROPERTY_NAME_DATABASE_URL = "db.url";
	private static final String PROPERTY_NAME_DATABASE_USERNAME = "db.username";
	private static final String PROPERTY_NAME_DATABASE_PASSWORD = "db.password";

	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";
	private static final String PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY = "hibernate.ejb.naming_strategy";
	private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
	private static final String PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN = "entitymanager.packages.to.scan";

	@Resource
	private Environment environment;


	@Bean
	public ComboPooledDataSource dataSource() {

		ComboPooledDataSource ds = null;

		ds = new ComboPooledDataSource();
		try {
			ds.setDriverClass(environment.getRequiredProperty(PROPERTY_NAME_DATABASE_DRIVER_CLASS));
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String url = environment.getRequiredProperty(PROPERTY_NAME_DATABASE_URL);		
		ds.setJdbcUrl(url);		
		ds.setUser(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_USERNAME));
		ds.setPassword(environment
				.getRequiredProperty(PROPERTY_NAME_DATABASE_PASSWORD));
		ds.setMinPoolSize(5);
		ds.setMaxPoolSize(10);
		return ds;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
			DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean lef = new LocalContainerEntityManagerFactoryBean();
		lef.setDataSource(dataSource);
		lef.setJpaVendorAdapter(jpaVendorAdapter);
		lef.setPackagesToScan(environment
				.getRequiredProperty(PROPERTY_NAME_ENTITYMANAGER_PACKAGES_TO_SCAN));

		Properties jpaProperties = new Properties();
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_DIALECT, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_NAMING_STRATEGY));
		jpaProperties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL, environment
				.getRequiredProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
		lef.setJpaProperties(jpaProperties);

		return lef;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter hibernateJpaVendorAdapter = new HibernateJpaVendorAdapter();
		hibernateJpaVendorAdapter.setShowSql(false);
		hibernateJpaVendorAdapter.setGenerateDdl(false);
		hibernateJpaVendorAdapter.setDatabase(Database.MYSQL);
		return hibernateJpaVendorAdapter;
	}

	@Bean
	public PlatformTransactionManager transactionManager(
			EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}

//	@Bean
//    public CacheManager cacheManager() {
//        // configure and return an implementation of Spring's CacheManager SPI
//        SimpleCacheManager cacheManager = new SimpleCacheManager();
//        ArrayList<ConcurrentMapCache> cacheList = new ArrayList<ConcurrentMapCache>();
//        cacheList.add(new ConcurrentMapCache("joe.spring.springapp.data.countries"));
//        cacheList.add(new ConcurrentMapCache("joe.spring.springapp.data.states"));        
//        cacheManager.setCaches(cacheList);
//        return cacheManager;
//    }	
		
	@Bean
	public GreetingService greetingService() {
		return new GreetingServiceImpl();
	}
	
	
}
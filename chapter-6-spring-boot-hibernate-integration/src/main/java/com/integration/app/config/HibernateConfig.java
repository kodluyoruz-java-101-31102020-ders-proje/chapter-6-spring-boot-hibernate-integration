package com.integration.app.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@Order(1)
@PropertySource({ "classpath:application.properties" })
public class HibernateConfig {

	@Autowired
	private Environment env;
	
	@Bean
		public DataSource createDataSource() {
		
		DriverManagerDataSource datasource = new DriverManagerDataSource();
		datasource.setUrl(env.getProperty("spring.datasource.url"));
		datasource.setUsername(env.getProperty("spring.datasource.username"));
		datasource.setPassword(env.getProperty("spring.datasource.password"));
		datasource.setDriverClassName(env.getProperty("spring.datasource.driver"));
		return datasource;
	}
	
	@Bean
	public EntityManagerFactory createEntityManagerFactory() {
		
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(Database.MYSQL);
		jpaVendorAdapter.setDatabasePlatform(env.getProperty("spring.jpa.mysql.database-platform"));

		jpaVendorAdapter.setShowSql(env.getProperty("spring.jpa.show-sql", Boolean.class));
		
		
		LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
		
		factoryBean.setDataSource(createDataSource());
		factoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		factoryBean.setPackagesToScan("com.integration.app.dao.entity");
		factoryBean.afterPropertiesSet();
		factoryBean.setJpaProperties(additionalProperties());
		
		return factoryBean.getObject();
	}
	
	@Bean
	@Primary
	public SessionFactory createSessionFactory() {
		
		EntityManagerFactory entityManagerFactory = createEntityManagerFactory();
		return entityManagerFactory.unwrap(SessionFactory.class);
	}
	
	private final Properties additionalProperties() {
		
		Properties properties = new Properties();
		properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto"));
		properties.setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"));
		properties.setProperty("hibernate.show_sql", env.getProperty("spring.jpa.show-sql"));
		properties.setProperty("hibernate.format_sql", env.getProperty("spring.jpa.properties.hibernate.format_sql"));
		return properties;
	}
}

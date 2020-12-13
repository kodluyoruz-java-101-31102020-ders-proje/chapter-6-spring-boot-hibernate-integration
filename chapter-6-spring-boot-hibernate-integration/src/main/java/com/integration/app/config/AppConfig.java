package com.integration.app.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

@Configuration
@Order(2)
@ComponentScan( { 
	"com.integration.app.dao.impl", 
	"com.integration.app.service.impl",
	"com.integration.app.aop.aspect",
	"com.integration.app.console.runner"})
@Import(HibernateConfig.class)
@PropertySource({ "classpath:application.properties" })
public class AppConfig {

}

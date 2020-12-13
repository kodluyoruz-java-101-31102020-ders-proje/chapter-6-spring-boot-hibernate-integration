package com.integration.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.integration.app.config.AppConfig;

@SpringBootApplication(scanBasePackages = "com.integration.app.config")
@Import(AppConfig.class)
public class Application {

	public static void main(String[] args) {
		
		SpringApplication.run(Application.class, args);
	}
}

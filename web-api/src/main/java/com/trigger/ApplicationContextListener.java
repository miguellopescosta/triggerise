package com.trigger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "com.trigger")
public class ApplicationContextListener extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ApplicationContextListener.class, args);
	}

}

package com.schoolmenu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
//@EnableEurekaClient
public class SchoolmenuApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(SchoolmenuApplication.class, args);
	}

}

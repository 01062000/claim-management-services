package com.cts.auth.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class AuthorizationApplication{
	
	/*@Autowired
	AuthRequestRepo authRequestRepo;
	*/
	
	public static void main(String[] args) {
		SpringApplication.run(AuthorizationApplication.class, args);
	}

}
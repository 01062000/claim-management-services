package com.cts.claim.cms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class ClaimMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClaimMicroserviceApplication.class, args);
	}

}

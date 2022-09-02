package com.cts.policy.cms.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;


@FeignClient(name = "authorization-service", url = "${Authorization.URL}")
public interface AuthClient {
	
	@GetMapping(value = "/validate")
	public boolean authorizeTheRequest(@RequestHeader(value = "Authorization", required = true) String token); 

}

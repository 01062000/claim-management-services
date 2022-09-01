package com.cts.member.cms.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.member.cms.model.ClaimDetails;
import com.cts.member.cms.model.ClaimStatus;

@FeignClient(name = "claim-service", url = "${Claims.URL}")
public interface ClaimsClient {

	
	@GetMapping(path="/getAllClaims/{memberId}")
	public List<ClaimDetails> getClaimDetails(@PathVariable String memberId, @RequestHeader(name = "Authorization", required = true) String token);
	
	@GetMapping("/getClaimStatus/{claimId}")
	ClaimStatus statusDetails(@PathVariable String claimId,@RequestHeader(name = "Authorization", required = true)String token);
		
	@PostMapping("/submitClaim")
	ClaimStatus onSubmitStatusDetails(@RequestBody ClaimDetails claimDetails,@RequestHeader(name = "Authorization", required = true)String token);
}

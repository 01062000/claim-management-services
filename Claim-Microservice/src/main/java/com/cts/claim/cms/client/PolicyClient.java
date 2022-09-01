package com.cts.claim.cms.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.claim.cms.model.ListOfBenefits;
import com.cts.claim.cms.model.ClaimAmount;
import com.cts.claim.cms.model.Provider;


@FeignClient(name = "policy-service", url = "${Policy.URL}")
public interface PolicyClient {
	
	@GetMapping(path="/getChainOfProviders/{policyId}")
	public ResponseEntity<Provider> getChainOfProviders(@PathVariable String policyId,@RequestHeader(name = "Authorization", required = true) String token);
	
	@GetMapping(path="/getEligibleBenefits/{policyId}/{memberId}")
	public ResponseEntity<ListOfBenefits> getEligibleBenefits(@PathVariable String policyId,@PathVariable String memberId,@RequestHeader(name = "Authorization", required = true) String token);
	
	@GetMapping(path="/getEligibleClaimAmount/{policyId}/{memberId}")
	public ResponseEntity<ClaimAmount> getEligibleAmount(@PathVariable String policyId,@PathVariable String memberId,@RequestHeader(name = "Authorization", required = true) String token);
}

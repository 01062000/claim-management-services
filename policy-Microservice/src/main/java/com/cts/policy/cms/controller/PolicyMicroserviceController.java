package com.cts.policy.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.cts.policy.cms.model.Benefits;
import com.cts.policy.cms.model.ListOfBenefits;
import com.cts.policy.cms.model.ClaimAmount;
import com.cts.policy.cms.model.Hospital;
import com.cts.policy.cms.model.Policy;
import com.cts.policy.cms.model.Provider;
import com.cts.policy.cms.service.BenefitHospitalPolicyService;
import com.cts.policy.cms.service.BenefitsService;
import com.cts.policy.cms.service.ClaimAmountService;
import com.cts.policy.cms.service.ProviderService;
import com.cts.policy.cms.feign.AuthClient;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PolicyMicroserviceController {
	
	@Autowired
	private ProviderService providerService;
	
	@Autowired
	private BenefitsService benefitsService;
	
	@Autowired
	private ClaimAmountService claimAmountService;
	
	@Autowired
	private BenefitHospitalPolicyService benefitHospitalPolicyService;
	
	 @Autowired
	private AuthClient authClient;
	 
	private String msg = "Token is either expired or invalid...";
	
	@GetMapping("/getBenefits/{benefitName}")
	public String getBenefitsIds(@PathVariable String benefitName, @RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		if (!authClient.authorizeTheRequest(token)) {
			throw new Exception(msg);
		}
		return benefitHospitalPolicyService.getBenefits(benefitName);
	}
	
	@GetMapping("/hospitals/{name}")
	public String getHospitalsIds(@PathVariable String name, @RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		if (!authClient.authorizeTheRequest(token)) {
			throw new Exception(msg);
		}
		return benefitHospitalPolicyService.getHospitals(name);
	}
	
	@GetMapping("/policies/{policyType}")
	public String getPolicyIds(@PathVariable String policyType, @RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		if (!authClient.authorizeTheRequest(token)) {
			throw new Exception(msg);
		}
		return benefitHospitalPolicyService.getPolicies(policyType);
	}
	
	@GetMapping("/getBenefitsDetails")
	public List<Benefits> getAllBenefitsDetails(@RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		if (!authClient.authorizeTheRequest(token)) {
			throw new Exception(msg);
		}
		return benefitHospitalPolicyService.getBenefitsDetails();
	}
	
	@GetMapping("/getHospitalsDetails")
	public List<Hospital> getAllHospitalsDetails(@RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		if (!authClient.authorizeTheRequest(token)) {
			throw new Exception(msg);
		}
		return benefitHospitalPolicyService.getHospitalsDetails();
	}
	
	@GetMapping("/getPoliciesDetails")
	public List<Policy> getAllPoliciesDetails(@RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		if (!authClient.authorizeTheRequest(token)) {
			throw new Exception(msg);
		}
		return benefitHospitalPolicyService.getPoliciesDetails();
	}
	
	@GetMapping(path="/getChainOfProviders/{policyId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Provider> getChainOfProviders(@PathVariable String policyId, @RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		log.info("Inside get chain of providers...");
			if (!authClient.authorizeTheRequest(token)) {
				throw new Exception(msg);
			}
		log.info("Exiting get chain of providers...");
		return new ResponseEntity<>(providerService.getProviders(policyId), HttpStatus.OK);
	}
	
		
	@GetMapping(path="/getEligibleBenefits/{policyId}/{memberId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ListOfBenefits> getEligibleBenefits(@PathVariable String policyId, @PathVariable String memberId, @RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		log.info("Inside get eligible benefits");
			if (!authClient.authorizeTheRequest(token)) {
				throw new Exception(msg);
			}
		log.info("Exiting get eligible benefits");
    	return new ResponseEntity<>(benefitsService.getBenefits(policyId,memberId), HttpStatus.OK);
	}
	
	@GetMapping(path="/getEligibleClaimAmount/{policyId}/{memberId}", produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClaimAmount> getEligibleAmount(@PathVariable String policyId, @PathVariable String memberId, @RequestHeader(name = "Authorization", required = true)String token) throws Exception{
		log.info("Inside get eligible benefits");
			if (!authClient.authorizeTheRequest(token)) {										
				throw new Exception(msg);
		}	   
		log.info("Exiting get elibile amount");
		return new ResponseEntity<>(claimAmountService.getClaimAmount(policyId,memberId), HttpStatus.OK);
	}
}

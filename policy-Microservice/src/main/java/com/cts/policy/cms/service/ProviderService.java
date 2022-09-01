package com.cts.policy.cms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.policy.cms.model.Provider;
import com.cts.policy.cms.exception.InvalidPolicyId;
import com.cts.policy.cms.model.Policy;
import com.cts.policy.cms.repository.PolicyRepo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@Service
public class ProviderService {
	
	@Autowired
	private PolicyRepo policyRepo;
	
	public Provider getProviders(String policyId) throws InvalidPolicyId {
		log.info("Inside get providers method in provider service...");
		Optional<Policy> policies = policyRepo.findById(policyId);
		if(!policies.isPresent())
			throw new InvalidPolicyId("Invalid Policy Id");
		log.info("Exiting get providers method in provider service...");
		return new Provider(policies.get().getHospitals());
	}
}

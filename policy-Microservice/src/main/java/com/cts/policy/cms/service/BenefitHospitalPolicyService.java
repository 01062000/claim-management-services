package com.cts.policy.cms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.policy.cms.model.Benefits;
import com.cts.policy.cms.model.Hospital;
import com.cts.policy.cms.model.Policy;
import com.cts.policy.cms.repository.BenefitsRepo;
import com.cts.policy.cms.repository.HospitalRepo;
import com.cts.policy.cms.repository.PolicyRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BenefitHospitalPolicyService {

	@Autowired
	private BenefitsRepo benefitsRepo;
	@Autowired
	private HospitalRepo hospitalRepo;
	@Autowired
	private PolicyRepo policyRepo;
	
	public String getBenefits(String benefitName) {
		log.info("Inside get benefits method in benefits service...");
		Optional<Benefits> findByName = benefitsRepo.findByBenefitName(benefitName);
		String benefitId = findByName.get().getBenefitId();
		return benefitId;
	}
	
	public List<Benefits> getBenefitsDetails() {
		List<Benefits> findAll = benefitsRepo.findAll();
		return findAll;
	}
	
	public String getHospitals(String name) {
		log.info("Inside get hospitals method in benefits service...");
		Optional<Hospital> findByName = hospitalRepo.findByName(name);
		String hospitalId = findByName.get().getHospitalId();
		return hospitalId;
	}
	
	public List<Hospital> getHospitalsDetails() {
		List<Hospital> findAll = hospitalRepo.findAll();
		return findAll;
	}
	
	public String getPolicies(String policyType) {
		log.info("Inside get policies method in benefits service...");
		Optional<Policy> findByName = policyRepo.findByPolicyType(policyType);
		String policyId = findByName.get().getPolicyId();
		return policyId; 
	}
	
	public List<Policy> getPoliciesDetails() {
		List<Policy> findAll = policyRepo.findAll();
		return findAll;
	}
}

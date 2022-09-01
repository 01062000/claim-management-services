package com.cts.policy.cms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.policy.cms.model.ListOfBenefits;
import com.cts.policy.cms.exception.ExpiredPolicyException;
import com.cts.policy.cms.exception.InvalidMemberIdException;
import com.cts.policy.cms.exception.InvalidPolicyId;
import com.cts.policy.cms.model.Policy;
import com.cts.policy.cms.repository.PolicyRepo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BenefitsService {
	
	@Autowired
	private PolicyRepo policyRepo;
	
	@Autowired
	private MemberPolicyService memberPolicyService;

	public ListOfBenefits getBenefits(String policyId, String memberId){
			log.info("Inside get benefits method in benefits service...");
			Optional<Policy> policy = policyRepo.findById(policyId);
			if(!policy.isPresent() ){
				throw new InvalidPolicyId("Invalid Policy Id...");
			}
			if(!memberPolicyService.isValidMember(memberId)){
				throw new InvalidMemberIdException("Invalid Member Id...");
			}
			if(!memberPolicyService.isPremiumPaid(memberId)){
				throw new ExpiredPolicyException("Premium not paid...");
			}			
			log.info("Exiting get benefits method in benefits service...");
			return new ListOfBenefits(policy.get().getBenefits());	
	}
}

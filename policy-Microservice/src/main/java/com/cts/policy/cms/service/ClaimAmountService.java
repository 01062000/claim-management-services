package com.cts.policy.cms.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.cts.policy.cms.model.ClaimAmount;
import com.cts.policy.cms.exception.ExpiredPolicyException;
import com.cts.policy.cms.exception.InvalidMemberIdException;
import com.cts.policy.cms.exception.InvalidPolicyId;
import com.cts.policy.cms.model.Policy;
import com.cts.policy.cms.repository.PolicyRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClaimAmountService {
	
	@Autowired
	private PolicyRepo policyRepo;
	
	@Autowired
	private MemberPolicyService memberPolicyService;
	
	public ClaimAmount getClaimAmount(String policyId, String memberId) throws InvalidPolicyId{
		log.info("Inside get claim amount method in claim amount service...");
		Optional<Policy> policy = policyRepo.findById(policyId);
		if(!policy.isPresent() ){
			throw new InvalidPolicyId("Invalid Policy Id...");
		}
		if(!memberPolicyService.isValidMember(memberId)){
			throw new InvalidMemberIdException("Invalid Member Id...");
		}
		if(!memberPolicyService.isPremiumPaid(memberId)){
			throw new ExpiredPolicyException("Premium is not paid...");
		}
		log.info("Exiting get claim amount method in claim amount service...");
		return new ClaimAmount(policy.get().getSumInsured());
	}
}

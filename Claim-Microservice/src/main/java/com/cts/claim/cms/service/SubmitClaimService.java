package com.cts.claim.cms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.claim.cms.client.PolicyClient;
import com.cts.claim.cms.model.ListOfBenefits;
import com.cts.claim.cms.model.ClaimAmount;
import com.cts.claim.cms.model.Claims;
import com.cts.claim.cms.model.ClaimStatus;
import com.cts.claim.cms.model.Provider;
import com.cts.claim.cms.exception.PolicyException;
import com.cts.claim.cms.model.Benefits;
import com.cts.claim.cms.model.Claim;
import com.cts.claim.cms.model.Hospital;
import com.cts.claim.cms.repository.ClaimRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SubmitClaimService {
	@Autowired
	private ClaimRepo claimRepo;
	@Autowired
	private PolicyClient policyClient;

	public List<Claim> getDetails(String memberId) {
		List<Claim> findAll = claimRepo.findAllBymemberId(memberId);
		return findAll;
	}
	private boolean checkProvider(Claims claims, String token) {
		Provider provider = policyClient.getChainOfProviders(claims.getPolicyId(), token).getBody();
		System.out.println("ProviderDTO: "+provider.getProviders());
		log.info("Inside Check provider method : Begin");
		if (provider !=null && provider.getProviders() != null) {
		for (Hospital hospitalDetails : provider.getProviders()) {
			if (claims.getHospitalId().equalsIgnoreCase(hospitalDetails.getHospitalId())) {
				return true;
				}
			}
		}
		log.info("Inside Check provider method : End");
		return false;
	}

	private boolean checkBenefit(Claims claims, String token) {
		ListOfBenefits listOfBenefits = policyClient.getEligibleBenefits(claims.getPolicyId(), claims.getMemberId(), token).getBody();
		System.out.println("BenefitsDTO: "+listOfBenefits.getBenefits());
		log.info("Inside Check benefit method : Begin");
		if (listOfBenefits != null &&  listOfBenefits.getBenefits() !=null) {
		for (Benefits benefits : listOfBenefits.getBenefits()) {
			if (claims.getBenefitId().equalsIgnoreCase(benefits.getBenefitId())) {
				return true;
			}
		  }
		}
		log.info("Inside Check benefit method : End");
		return false;
	}

	private boolean checkAmount(Claims claims, String token) {
		ClaimAmount claimAmount = policyClient.getEligibleAmount(claims.getPolicyId(), claims.getMemberId(), token).getBody();
		System.out.println("ClaimAmountDTO: "+claimAmount);
		log.info("Inside Check amount method : Begin");
		if (claimAmount != null) {
			return (claims.getClaimAmount() <= claimAmount.getEligibleAmount());
		}
		log.info("Inside Check amount method : End");
		return false;
	}

	public ResponseEntity<ClaimStatus> submitClaim(Claims claims, String token) throws NullPointerException {
		System.out.println("Hello World");
		boolean hospitalFlag = false;
		boolean benefitFlag = false;
		boolean amountFlag = false;
		log.info("Inside Submit claim method : Begin");
		try {
		hospitalFlag = checkProvider(claims, token);
		benefitFlag = checkBenefit(claims, token);
		amountFlag = checkAmount(claims, token);
		}catch(PolicyException pe) {
			throw new PolicyException("Policy Serivce is Not working Properly");
		}
		Claim claim = new Claim();
		claim.setClaimId(claims.getClaimId());
		claim.setDescription(claims.getDescription());
		claim.setBenefitId(claims.getBenefitId());
		claim.setClaimAmount(claims.getClaimAmount());
		claim.setHospitalId(claims.getHospitalId());
		claim.setMemberId(claims.getMemberId());
		claim.setPolicyId(claims.getPolicyId());
		claim.setRemarks(claims.getRemarks());
		if (hospitalFlag && benefitFlag && amountFlag) {
			claim.setStatus("Pending Action");
			claim.setDescription("All the fields are successfully verified! Please wait for furthur action");
		} else {
			claim.setStatus("Claim Rejected");
			if (!hospitalFlag) {
				claim.setDescription("Providers Details are not valid");
			} else if (!benefitFlag) {
				claim.setDescription("Benefits Details are not valid");
			} else {
				claim.setDescription("Claim amount is not valid");
			}
		}
		if (hospitalFlag && benefitFlag && amountFlag) {
			claimRepo.save(claim);
		}
		claimRepo.save(claim);
		ClaimStatus claimStatus = new ClaimStatus();
		claimStatus.setClaimStatus(claim.getStatus());
		claimStatus.setClaimDescription(claim.getDescription());
		claimStatus.setClaimId(claim.getClaimId());
		log.info("Inside Submit claim method : End");
		return new ResponseEntity<>(claimStatus, HttpStatus.OK);
	}
}

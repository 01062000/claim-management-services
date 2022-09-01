package com.cts.claim.cms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.cts.claim.cms.model.ClaimStatus;
import com.cts.claim.cms.exception.InvalidClaimIdException;
import com.cts.claim.cms.repository.ClaimRepo;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClaimStatusService {

	@Autowired
	ClaimRepo claimRepo;
	
	public ResponseEntity<ClaimStatus> getClaimStatus(String claimId) throws InvalidClaimIdException {
		    log.info("Inside the fetch Claim Status method : Begin");
			ClaimStatus claimStatus = new ClaimStatus();
			claimStatus.setClaimStatus("Pending Action");
			claimStatus.setClaimDescription("All the fields are successfully verified! Please wait for furthur action");
			claimStatus.setClaimId(claimId);
			log.info("Inside the fetch Claim Status method : End");
			return new ResponseEntity<>(claimStatus, HttpStatus.OK);
	}

}

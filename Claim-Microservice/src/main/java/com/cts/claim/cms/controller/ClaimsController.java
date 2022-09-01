package com.cts.claim.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.claim.cms.client.AuthClient;
import com.cts.claim.cms.model.Claims;
import com.cts.claim.cms.model.Claim;
import com.cts.claim.cms.model.ClaimStatus;
import com.cts.claim.cms.exception.InvalidClaimIdException;
import com.cts.claim.cms.exception.InvalidTokenException;
import com.cts.claim.cms.service.ClaimStatusService;
import com.cts.claim.cms.service.SubmitClaimService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/claimModule")
@Api(value = "For dealing with claim ")
public class ClaimsController {

	@Autowired
	private ClaimStatusService claimStatusService;
	@Autowired
	private SubmitClaimService submitClaimService;
	@Autowired
	private AuthClient authClient;
	
	@GetMapping(path="/getAllClaims/{memberId}")
	public List<Claim> getClaimDetails(@PathVariable String memberId, @RequestHeader(name = "Authorization", required = true) String token) throws InvalidTokenException{
		log.info("Inside fetch claim status by memberId menthod : Begin");
		if (!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		
		log.info("Inside fetch claim status by memberId menthod : End");
		return submitClaimService.getDetails(memberId);
	}
	
	@GetMapping(path="/getClaimStatus/{claimId}")
	public ResponseEntity<ClaimStatus> getClaimStatus(@PathVariable("claimId") String claimId,@RequestHeader(name = "Authorization", required = true) String token)throws InvalidClaimIdException,InvalidTokenException {
		log.info("Inside fetch claim status menthod : Begin");
		if (!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		
		log.info("Inside fetch claim status menthod : End");
		return claimStatusService.getClaimStatus(claimId); 
	}
	
	@PostMapping(path ="/submitClaim")
	public ResponseEntity<ClaimStatus> submitClaim(@RequestBody Claims claims,@RequestHeader(name = "Authorization", required = true) String token)throws InvalidTokenException,NullPointerException {
		System.out.println(claims.getClaimAmount());
		ClaimStatus claimStatus = new ClaimStatus();
		System.out.println(claims.getClaimId());
		claimStatus.setClaimId(claims.getClaimId());
		claimStatus.setClaimStatus("Pending Action");
		claimStatus.setClaimDescription("All the fields are successfully verified! Please wait for furthur action");
		if (authClient.authorizeTheRequest(token)) {
			return submitClaimService.submitClaim(claims,token);
		}else {
			throw new InvalidTokenException("Token is either expired or invalid");
		}
		//return new ResponseEntity<>(claimStatusDTO, HttpStatus.OK);
	}
}

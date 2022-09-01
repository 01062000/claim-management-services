package com.cts.member.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.member.cms.client.AuthClient;
import com.cts.member.cms.exception.InvalidTokenException;
import com.cts.member.cms.model.ClaimStatus;
import com.cts.member.cms.model.Bills;
import com.cts.member.cms.model.ClaimDetails;
import com.cts.member.cms.service.ClaimStatusAndDetails;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



@RestController
@Slf4j
@RequestMapping("/memberModule")
@Api(value = "Member module endpoint")
@CrossOrigin
public class MemberController { 
    
    @Autowired
    private ClaimStatusAndDetails ClaimStatusAndDetails;
    
     @Autowired
     private AuthClient authClient;
     
     String msg = "Token is either expired or invalid...";
     
     @ApiOperation(value = "to get the claims for the User")
     @GetMapping(value="/getAllClaims/{memberId}",produces = MediaType.APPLICATION_JSON_VALUE)
     public List<ClaimDetails> getAllClaimsByMemberId(@PathVariable("memberId") String memberId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException{
    	 log.info("Inside the getAllClaimsByMemberId EndPoint : Begin");
         // Checking the validity of user
     	if (!authClient.authorizeTheRequest(token)) {
 			throw new InvalidTokenException(msg);
 		}
     	log.info("Inside the getAllClaimsByMemberId EndPoint : Ended");
    	return ClaimStatusAndDetails.getAllClaimDetails(memberId, token);
     }
    @ApiOperation(value = "to get the Bills for the User")
    @GetMapping(value="/viewBills/{memberId}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Bills> getBills( @PathVariable("memberId") String memberId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {
        log.info("Inside the getBills EndPoint : Begin");
        // Checking the validity of user
    	if (!authClient.authorizeTheRequest(token)) {
			throw new InvalidTokenException(msg);
		}
    	log.info("Inside the getBills EndPoint : Ended");
        return new ResponseEntity<>( ClaimStatusAndDetails.fetchBills(memberId), HttpStatus.OK);
    }
    
    @ApiOperation(value = "To get the claim Status For Given Id")
    @GetMapping(value="/getClaimStatus/{claimId}", produces = "application/json")
    public ResponseEntity<ClaimStatus> returnClaimStatus(@PathVariable("claimId") String claimId, @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {
        
        log.info("Inside the getClaimStatus EndPoint : Begin");
        // Checking the validity of user
        if (!authClient.authorizeTheRequest(token))  {
            throw new InvalidTokenException(msg);
        }
        log.info("Inside the getClaimStatus EndPoint : Ended");
        return new ResponseEntity<>( ClaimStatusAndDetails.fetchClaimStatus(claimId, token), HttpStatus.OK);          
    }
    
    @PostMapping(value="/submitClaim", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "To Submit the Claim", response = ClaimStatus.class, httpMethod = "POST")
    public ResponseEntity<ClaimStatus> returnClaimStatusOnUpdate(@RequestBody ClaimDetails claimDetails,  @RequestHeader(name = "Authorization", required = true)String token) throws InvalidTokenException
    {
        log.info("Inside the submitClaim EndPoint : Begin");
        // Checking the validity of user
        if (!authClient.authorizeTheRequest(token))  {
            throw new InvalidTokenException(msg);
        }
        System.out.println(claimDetails.getClaimId());
        log.info("Inside the submitClaim EndPoint : Ended");
        return new ResponseEntity<>( ClaimStatusAndDetails.fetchClaimDetails(claimDetails,token), HttpStatus.OK);
    }
}

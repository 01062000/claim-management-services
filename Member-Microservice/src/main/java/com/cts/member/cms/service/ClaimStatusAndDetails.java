package com.cts.member.cms.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.member.cms.model.ClaimDetails;
import com.cts.member.cms.client.ClaimsClient;
import com.cts.member.cms.exception.InvalidClaimIdException;
import com.cts.member.cms.exception.InvalidMemberIdException;
import com.cts.member.cms.model.ClaimStatus;
import com.cts.member.cms.model.Bills;
import com.cts.member.cms.repository.BillsRepo;

import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class ClaimStatusAndDetails{

        @Autowired
        private BillsRepo billsRepo;
        
        @Autowired
        private ClaimsClient claimsClient;
        
        public List<ClaimDetails> getAllClaimDetails(String memberId,  String token){
        	List<ClaimDetails> claimDetails = claimsClient.getClaimDetails(memberId, token);
        	return claimDetails;
        }
        
        // This method fetch claim Status from Claims MicroService
        public  ClaimStatus fetchClaimStatus(String claimId, String token)
        {
            log.info("Inside the fetch Claim Status method : Begin");
            ClaimStatus claimStatus  = new ClaimStatus();
            try {
            	claimStatus = claimsClient.statusDetails(claimId,token);
            }catch(Exception e){
                throw new InvalidClaimIdException("The Claim Id is Invalid");
            }
            return claimStatus;
        }
        
        
        // This method fetch Bills from Bills table
        public  Bills fetchBills(String memberId)
        {
            log.info("Inside the fetch Bills method : Begin");
            
            Optional<Bills> bill = billsRepo.findById(memberId);
            if(!bill.isPresent() )
    		{
    			throw new InvalidMemberIdException("The Member Id is Invalid");
    		}
            
            return bill.get();
           
        }
        
        
        // This method submit the claim details to Claims MicroService
        public  ClaimStatus fetchClaimDetails( ClaimDetails claimDetails,String token)
        {
            log.info("Inside the fetch Claim Status method : Begin");
            ClaimStatus claimStatus  = new ClaimStatus();
            claimDetails.setClaimId(claimDetails.getClaimId());
            System.out.println(claimDetails.getClaimId());
            try {
            claimStatus = claimsClient.onSubmitStatusDetails(claimDetails,token);
            }
            catch(Exception e)
            {
            	throw e;
            }
            return claimStatus;
        }
}

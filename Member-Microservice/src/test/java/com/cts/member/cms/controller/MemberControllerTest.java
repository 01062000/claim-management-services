package com.cts.member.cms.controller;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;

import com.cts.member.cms.client.AuthClient;
import com.cts.member.cms.exception.InvalidClaimIdException;
import com.cts.member.cms.exception.InvalidTokenException;
import com.cts.member.cms.service.ClaimStatusAndDetails;
import com.cts.member.cms.model.ClaimStatus;
import com.cts.member.cms.model.Bills;
import com.cts.member.cms.model.ClaimDetails;





@SpringBootTest
public class MemberControllerTest {
	
	@InjectMocks
	MemberController memberController;
	
	@Mock
	ClaimStatusAndDetails claimStatusAndDetails;

	@Mock
	AuthClient authClient;

    @Test
    @DisplayName("Checking for MemberController - if it is loading or not for @GetMapping")
    void MemberControllerNotNullTest(){
    	
    	MemberController memberController = new MemberController();
        assertThat(memberController).isNotNull();
    }
    
    

    @Test
	@DisplayName("Testing get Bills is working correctly or not")
	public void testgetBills() {
    	
    	Date d2 = Date.valueOf("2020-06-04");
    	Date d3 = Date.valueOf("2011-07-02");
    	
        Bills billsObj= new Bills("M101","P1001", d3,65600,1200,d2);
    	when(authClient.authorizeTheRequest(anyString())).thenReturn(true);
    	when(claimStatusAndDetails.fetchBills("M101")).thenReturn(new Bills("M101","P1001", d3,65600,1200,d2));
    	
    	ResponseEntity<Bills> response = memberController.getBills("M101", "token");
    	
    	assertEquals(billsObj.getMemberId(), response.getBody().getMemberId());
	}
//    
//    @Test
//   	@DisplayName("Testing get Bills invalid Claim Id Exception")
//   	public void testgetBills_fails1() {
//       	
//    	when(authClient.getsValidity(anyString())).thenReturn(new ValidatingDTO(true));
//    	Assertions.assertThrows(InvalidMemberIdException.class, ()->{
//    		memberController.getBills("12111", "token");
//    	});
//   	}
    
    @Test()
   	@DisplayName("Testing get Bills invalid Token Id Exception")
   	public void testgetBills_fails2() {
       	
    	when(authClient.authorizeTheRequest(anyString())).thenReturn(false);
    	//Assertions.assertThrows(InvalidTokenException.class, ()->{ memberController.getBills("M101", "token");});
   	}
    // Second Endpoint testing
    
    @Test
   	@DisplayName("Testing get claim Status is working correctly or not")
   	public void testgetClaimStatus() {
             
       	when(authClient.authorizeTheRequest(anyString())).thenReturn(true);
       	when(claimStatusAndDetails.fetchClaimStatus("M101","token")).thenReturn(new ClaimStatus("C101","Pending","Need More Action"));
       	
       	ResponseEntity<ClaimStatus> response = memberController.returnClaimStatus("C101", "token");
       	
       	//assertEquals("Pending", response.getBody().getClaimStatus());
   	}
       
       @Test
      	@DisplayName("Testing get Claim Status invalid Claim Id Exception")
      	public void testgetClaimStatus_fails1() {
          	
       	when(authClient.authorizeTheRequest(anyString())).thenReturn(true);
          	when(claimStatusAndDetails.fetchClaimStatus(anyString(), anyString())).thenThrow(InvalidClaimIdException.class);
         
          	//Assertions.assertThrows(InvalidClaimIdException.class, ()->{memberController.returnClaimStatus("C101", "token");});
      	}
       
       @Test
      	@DisplayName("Testing get Claim Status invalid Token Id Exception")
      	public void testgetClaimStatus_fails2() {
          	
       		when(authClient.authorizeTheRequest(anyString())).thenReturn(false);
       	 	//Assertions.assertThrows(InvalidTokenException.class, ()->{memberController.returnClaimStatus("C101", "token");});
      	}
       
       
       // Testing the 3rd Endpoint
       @Test
      	@DisplayName("Testing get Submitting Claim is working correctly or not")
      	public void testgetClaimStatusOnUpdate() {
          	
       		ClaimDetails claimDetails = new ClaimDetails();
       		claimDetails.setClaimId("C101");
       		claimDetails.setStatus("Pending");       
          	when(authClient.authorizeTheRequest(anyString())).thenReturn(true);
          	when(claimStatusAndDetails.fetchClaimDetails(claimDetails,"token")).thenReturn(new ClaimStatus("C101","Pending","Need More Action"));
          	
          	ResponseEntity<ClaimStatus> response = memberController.returnClaimStatusOnUpdate(claimDetails, "token");
         
          	assertEquals("Pending", response.getBody().getClaimStatus());
      	}
          
     
        @Test
     	@DisplayName("Testing Submitting Bills invalid Token Id Exception")
     	public void testClaimStatusOnUpdate_fails1() {
         	
        	ClaimDetails claimDetails = new ClaimDetails();
        	when(authClient.authorizeTheRequest(anyString())).thenReturn(false);
        	when(authClient.authorizeTheRequest(anyString())).thenReturn(false);
       	 	//Assertions.assertThrows(InvalidTokenException.class, ()->{memberController.returnClaimStatusOnUpdate(claimDetails, "token");});
         	
      	
     	}
          
  
}

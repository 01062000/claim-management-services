package com.cts.claim.cms.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.claim.cms.client.AuthClient;
import com.cts.claim.cms.model.Claims;
import com.cts.claim.cms.model.ClaimStatus;
import com.cts.claim.cms.exception.InvalidTokenException;
import com.cts.claim.cms.service.ClaimStatusService;
import com.cts.claim.cms.service.SubmitClaimService;

@SpringBootTest
class ClaimsControllerTest {

	@Autowired
	ClaimsController claimsController;
	/*
	 * We can use the @MockBean to add mock objects to the Spring application context. 
	 * The mock will replace any existing bean of the same type in the application context.
	 * If no bean of the same type is defined, a new one will be added. 
	 */
	@MockBean
	AuthClient authClient;
	@MockBean
	SubmitClaimService submitClaimService;
	@MockBean
	ClaimStatusService claimStatusService;
	
	@Test
    @DisplayName("Checking for Claims Controller - if it is loading or not for @GetMapping")
    void componentProcessingControllerNotNullTest(){
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
        assertThat(claimsController).isNotNull();
    }
	
	/*@Test
	@DisplayName("Checking getClaimStatus Endpoint with Invalid Token")
	void testGetClaimStatusEndpointWithInvalidToken() {
		String token="AAA";
		when(authClient.authorizeTheRequest(token)).thenReturn(true);
		
		//assertThrows(InvalidTokenException.class, () -> claimsController.getClaimStatus("C101",token));
	}
	
	@Test
	@DisplayName("Checking submit Claim Endpoint with Invalid Token")
	void testSubmitClaimEndpointWithInvalidToken() {
		String token="AAA";
		when(authClient.authorizeTheRequest(token)).thenReturn(true);
		Claims claims=new Claims();
		//assertThrows(InvalidTokenException.class, () -> claimsController.submitClaim(claims,token));
	}
*/
	
	@Test
	@DisplayName("Checking the working of get claim Status Endpoint")
	void testGetClaimStatusWithValidParameters() {
		String token="AAA";
		
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("C101");
		claimStatus.setClaimStatus("Pending");
		claimStatus.setClaimDescription("Verified");
		
		when(authClient.authorizeTheRequest(token)).thenReturn(true);
		when(claimStatusService.getClaimStatus("C101")).thenReturn(new ResponseEntity<ClaimStatus>(claimStatus,HttpStatus.OK));
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals(claimStatus.getClaimId(), claimsController.getClaimStatus("C101",token).getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), claimsController.getClaimStatus("C101",token).getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(), claimsController.getClaimStatus("C101",token).getBody().getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking the working of Submit claim  Endpoint")
	void testSubmitClaimWithValidParameters() {
		String token="AAA";
		Claims claims=new Claims();
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("C101");
		claimStatus.setClaimStatus("Pending");
		claimStatus.setClaimDescription("Verified");
		
		/* 
		 * The thenReturn() methods lets you define the return value when a particular method of the mocked object is been called. 
		 * The below snippet shows how we use thenReturn to check for multiple values.
		*/
		when(authClient.authorizeTheRequest(token)).thenReturn(true);
		when(submitClaimService.submitClaim(claims,token)).thenReturn(new ResponseEntity<ClaimStatus>(claimStatus,HttpStatus.OK));
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals(claimStatus.getClaimId(), claimsController.submitClaim(claims,token).getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), claimsController.submitClaim(claims,token).getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),claimsController.submitClaim(claims,token).getBody().getClaimDescription()); 
	}
}

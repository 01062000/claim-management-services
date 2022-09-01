package com.cts.claim.cms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cts.claim.cms.model.ClaimStatus;
import com.cts.claim.cms.exception.InvalidClaimIdException;
import com.cts.claim.cms.repository.ClaimRepo;

@SpringBootTest
class ClaimStatusServiceTest {

	@Autowired
	ClaimStatusService claimStatusService;
	@MockBean
	ClaimRepo claimRepo;
	@Test
	@DisplayName("Checking if ClaimStatusService class is loading or not")
	void claimStatusServiceIsLoadedOrNot() {
		assertThat(claimStatusService).isNotNull();
	}
	
	@Test
	@DisplayName("Checking if getClaimStatus method is working or not with valid Id")
	void getClaimStatusTestWithValidId() {
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("C101");
		claimStatus.setClaimStatus("Pending");
		claimStatus.setClaimDescription("Verified");
		
		/* 
		 * The thenReturn() methods lets you define the return value when a particular method of the mocked object is been called. 
		 * The below snippet shows how we use thenReturn to check for multiple values.
		*/
		when(claimRepo.getStatus("C101")).thenReturn("Pending");
		when(claimRepo.getDescription("C101")).thenReturn("Verified");
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals(claimStatus.getClaimId(), claimStatusService.getClaimStatus("C101").getBody().getClaimId()); 
		//assertEquals(claimStatus.getClaimStatus(), claimStatusService.getClaimStatus("").getBody().getClaimStatus()); 
		//assertEquals(claimStatus.getClaimDescription(), claimStatusService.getClaimStatus("").getBody().getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking if getClaimStatus method is working or not with Invalid Id")
	void testGetClaimStatusWithInvalidValidId() {
		
		/* 
		 * You can use assertThrows(), which allows you to test multiple exceptions within the same test. 
		 * With support for lambdas in Java 8, this is the canonical way to test for exceptions in JUnit.
		*/
		//assertThrows(InvalidClaimIdException.class, () -> claimStatusService.getClaimStatus("Pending"));
	}
}

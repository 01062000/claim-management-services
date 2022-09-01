package com.cts.claim.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClaimStatusTest {

	ClaimStatus claimStatus=new ClaimStatus();
	
	@Test
	@DisplayName("Checking if claimStatusDTO class is loaded or not")
	void claimStatusDTOIsLoadingOrNot() {
		assertThat(claimStatus).isNotNull();
	}
	
	@Test
	@DisplayName("Checking if all the getter and setter are working ")
	void checkClaimStatusDTO() {
		
		ClaimStatus claimStatus=new ClaimStatus("C101","Pending","Verified");
		
		claimStatus.setClaimId("C102");
		claimStatus.setClaimDescription("Invalid Details");
		claimStatus.setClaimStatus("Rejected");
		
		assertEquals("C102", claimStatus.getClaimId());
		assertEquals("Rejected", claimStatus.getClaimStatus());
		assertEquals("Invalid Details", claimStatus.getClaimDescription());
	}
	
}

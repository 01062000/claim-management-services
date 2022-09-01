package com.cts.claim.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClaimsTest {

	
Claims claims=new Claims();
	
	@Test
	@DisplayName("checking if ClaimDTO Class is loading or not")
	void ClaimIsLoadedOrNot() {
		assertThat(claims).isNotNull();
	}
	
	@Test
	@DisplayName("Checking every getter and setter of ClaimDTO Class")
	void testingClaim() {
		Claims claims=new Claims("C101","Pending","Verified","Nothing",10200.0,"H101","B101","P1001","M101");
		
		claims.setClaimId("C102");
		claims.setStatus("Rejected");
		claims.setDescription("Invalid Details");
		claims.setRemarks("Nothing");
		claims.setClaimAmount(10000.0);
		claims.setHospitalId("H102");
		claims.setBenefitId("B102");
		claims.setPolicyId("P1002");
		claims.setMemberId("M102");
		
		
		assertEquals("C102",claims.getClaimId());
		assertEquals("Rejected",claims.getStatus());
		assertEquals("Invalid Details",claims.getDescription());
		assertEquals("Nothing",claims.getRemarks());
		assertEquals(10000.0,claims.getClaimAmount());
		assertEquals("H102",claims.getHospitalId());
		assertEquals("B102",claims.getBenefitId());
		assertEquals("P1002",claims.getPolicyId());
		assertEquals("M102",claims.getMemberId());
	
	}
}

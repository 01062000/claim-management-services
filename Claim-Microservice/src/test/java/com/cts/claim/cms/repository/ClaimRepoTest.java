package com.cts.claim.cms.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class ClaimRepoTest {

	@MockBean
	ClaimRepo claimRepo;
	
	@Test
	@DisplayName("Checking if Claim Repo methods Are working or not")
	void testClaimRepo() {
		when(claimRepo.getStatus("C101")).thenReturn("Pending");
		when(claimRepo.getDescription("C101")).thenReturn("Verified");
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals("Pending", claimRepo.getStatus("C101"));
		assertEquals("Verified", claimRepo.getDescription("C101"));
	}
}

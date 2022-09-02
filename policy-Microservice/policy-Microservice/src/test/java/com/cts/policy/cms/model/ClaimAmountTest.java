package com.cts.policy.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimAmountTest {

	ClaimAmount claimAmountObj = new ClaimAmount();
	
	@Test
    @DisplayName("Checking if Claim Amount class is loading or not.")
     void processingRequestIsLoadedOrNot() {
        assertThat(claimAmountObj).isNotNull();
    }
	
	@Test
	@DisplayName("Checking if Claim Amount class is responding correctly or not.")
	void testingClaimAmounts() throws ParseException{
		double amt = 123400.00;
		claimAmountObj.setEligibleAmount(amt);
		
		assertEquals(amt,claimAmountObj.getEligibleAmount());
	}
}
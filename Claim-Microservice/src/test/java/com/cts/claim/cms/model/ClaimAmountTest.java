package com.cts.claim.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class ClaimAmountTest {

	 ClaimAmount claimAmount=new ClaimAmount(0);
	 
	 @Test
	 @DisplayName("Checking if claimAmountDTO class is loading or not")
	 void claimAmountDTOisLoadingOrNot() {
		 /* 
		  * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		  * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		  * It will then try to compare this two and returns a boolean result if its a match or not. 
		  */
		 assertThat(claimAmount).isNotNull();
	 }
	 
	 @Test
	 @DisplayName("Checking all the getter and setter method")
	 void checkClaimAmountDTO() {
		 
		 ClaimAmount claimAmount=new ClaimAmount(10000.0);
		 claimAmount.setEligibleAmount(12000.0);
		 
		 /*
		  * If you want to test equality of two objects, you have the following methods
		  * assertEquals(expected, actual)
		  * It will return true if: expected.equals( actual ) returns true.
		 */
		 assertEquals(12000.0, claimAmount.getEligibleAmount());
	 }
}

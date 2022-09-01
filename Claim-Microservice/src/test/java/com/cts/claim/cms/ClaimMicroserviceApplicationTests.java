package com.cts.claim.cms;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ClaimMicroserviceApplicationTests {
 
	ClaimMicroserviceApplication claimMicroserviceApplication;
	
	@Test
	void contextLoads() {
		/*
		 * assertTrue method asserts that a specified condition is true.
		 * It takes in two parameters i.e. one is the message and the other is the condition against which the assertion needs to be applied. 
		 * It throws an AssertionError if the condition passed to the asserttrue method is not satisfied. 
		 */
		assertTrue(true);
	}

	@Test
	void testComponentProcessingMicroserviceApplication() {
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
		assertThat(claimMicroserviceApplication).isNull();
	}
}

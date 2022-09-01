package com.cts.claim.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class BenefitsTest {

	Benefits benefits=new Benefits();
	
	@Test
    @DisplayName("Checking if Benefits class is loading or not.")
     void benefitsIsLoadedOrNot() {
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
        assertThat(benefits).isNotNull();
    }
	
	@DisplayName("Checking if Benefits class is responding correctly or not.")
    @Test
     void testingBenefits(){
		Benefits benefits=new Benefits("B101","Coverage for COVID-19");
    	benefits.setBenefitId("B102");
    	benefits.setBenefitName("Coverage for hospitalization at home");
       
    	/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
        assertEquals("B102",benefits.getBenefitId());
        assertEquals("Coverage for hospitalization at home",benefits.getBenefitName());
    }
} 

package com.cts.claim.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
 class HospitalTest {

	Hospital hospital=new Hospital();
	
	@Test
	@DisplayName("Checking if Hospital class is Loading or not")
	void hospitalIsLoadingOrNot() {
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
		assertThat(hospital).isNotNull();
	}
	
	@Test
	@DisplayName("Checking of all the getter and setter are working")
	void checkHospital() {
		Hospital hospital=new Hospital("H101","Apollo Hospital","Delhi-Indraprastha");
		hospital.setHospitalId("H102");
		hospital.setName("Artemis Hospital");
		hospital.setLocation("Gurgaon");
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals("H102", hospital.getHospitalId());
		assertEquals("Artemis Hospital",hospital.getName());
		assertEquals("Gurgaon",hospital.getLocation());
		
	}
}

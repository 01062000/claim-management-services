package com.cts.claim.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
 class ListOfBenefitsTest {
	ListOfBenefits listOfBenefits=new ListOfBenefits();
	
	@Test
	@DisplayName("Checking if benefitsDTO is loading or not")
	void benefitsDTOIsLoadedOrNot() {
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
		assertThat(listOfBenefits).isNotNull();
	}
	
	@Test
	@DisplayName("Checking BenefitsDTO setter and getter")
	void checkBenefitsDTO() {
		List<Benefits> list=new ArrayList<Benefits>();
		list.add(new Benefits("B101","Coverage for COVID-19"));
		list.add(new Benefits("B102","Coverage for hospitalization at home"));
		list.add(new Benefits("B103","Ambulance charges upto 2000 covered"));
		ListOfBenefits listOfBenefits=new ListOfBenefits(list);
		
		listOfBenefits.setBenefits(list);
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals(3, listOfBenefits.getBenefits().size());
	}
}

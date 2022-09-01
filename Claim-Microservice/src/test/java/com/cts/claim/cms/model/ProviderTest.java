package com.cts.claim.cms.model;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ProviderTest {

	Provider provider=new Provider();
	
	@Test
	@DisplayName("Checking if providerDTO is loading or not")
	void providerDTOIsLoadingOrNot() {
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
		assertThat(provider).isNotNull();
	}
	
	@Test
	@DisplayName("Checking if all the getter and setters working")
	void checkProviderDTO() {
		List<Hospital> list=new ArrayList<>();
		list.add( new Hospital("H1","Apollo Hospital","Delhi-Indraprastha"));
		list.add( new Hospital("H2","Artemis Hospital","Gurgaon"));
		Provider provider =new Provider(list);
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals(2,provider.getProviders().size());
	}
	
}

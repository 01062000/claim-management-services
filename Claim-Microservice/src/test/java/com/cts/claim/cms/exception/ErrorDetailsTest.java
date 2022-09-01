package com.cts.claim.cms.exception;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ErrorDetailsTest {

	ErrorDetails errorDetails=new ErrorDetails();
	
	@Test
	@DisplayName("Checking if ErroDetails Class is loading or not")
	void errorDetailsIsLoadingOrNot() {
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
		assertThat(errorDetails).isNotNull();
	}
	
	@Test
	@DisplayName("Checking if all the setters and getters is working")
	void checkErrorDetails() {
		ErrorDetails errorDetails=new ErrorDetails(new Date(),"Message","SomeURL");
		errorDetails.setDate(new Date(02));
		errorDetails.setMessage("There is an error");
		errorDetails.setRequestURL("URL");
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals(new Date(02),errorDetails.getDate());
		assertEquals("There is an error", errorDetails.getMessage());
		assertEquals("URL", errorDetails.getRequestURL());
	}
	
}

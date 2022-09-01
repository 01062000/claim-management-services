package com.cts.claim.cms.exception;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@SpringBootTest
class GlobalExceptionHandlerTest {

	@Autowired
	GlobalExceptionHandler globalExceptionHandler;
	@MockBean
	GlobalExceptionHandler gloabalExceptionHandlerMockBean;
	
	@Test
	@DisplayName("Checking if GlobalExceptionHandler class is loading or not")
	void globalExceptionHandlerIsLoadingOrNot() {
		/* 
		 * The assertThat is one of the JUnit methods from the Assert object that can be used to check if a specific value match to an 
		 * expected one.It primarily accepts 2 parameters. First one if the actual value and the second is a matcher object. 
		 * It will then try to compare this two and returns a boolean result if its a match or not. 
		*/
		assertThat(globalExceptionHandler).isNotNull();
	}
	
	
	
}

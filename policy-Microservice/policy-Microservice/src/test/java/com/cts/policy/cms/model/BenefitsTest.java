package com.cts.policy.cms.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BenefitsTest {

	Benefits benefitsObj = new Benefits();
	
	@Test
    @DisplayName("Checking if Benefits class is loading or not.")
     void processingRequestIsLoadedOrNot() {
        assertThat(benefitsObj).isNotNull();
    }
	
	@Test
	@DisplayName("Checking if Benefits class is responding correctly or not.")
	void testingBenefits() throws ParseException{
		benefitsObj.setBenefitId("B101");
		benefitsObj.setBenefitName("Coverage for COVID-19");
		
		assertEquals("B101", benefitsObj.getBenefitId());
		assertEquals("Coverage for COVID-19", benefitsObj.getBenefitName());
	}
}

package com.cts.policy.cms.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PolicyTest {

	Policy policyObj  = new Policy();
	
	@Test
    @DisplayName("Checking if Policy class is loading or not.")
     void processingRequestIsLoadedOrNot() {
        assertThat(policyObj).isNotNull();
    }
	
	@Test
	@DisplayName("Checking if Policy class is responding correctly or not.")
	void testingPolicy() throws ParseException{
		
		int p = 500000;
		int s = 15639;
		policyObj.setPolicyId("P1001");
		policyObj.setPolicyType("Health Plus Classic");
		policyObj.setPremium(p);
		policyObj.setSumInsured(s);
		
		assertEquals("P1001", policyObj.getPolicyId());
		assertEquals("Health Plus Classic", policyObj.getPolicyType());
		assertEquals(p, policyObj.getPremium());
		assertEquals(s, policyObj.getSumInsured());
		
	}
}

package com.cts.policy.cms.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberPolicyTest {
	
	MemberPolicy memberPolicyObj = new MemberPolicy();
	
	@Test
    @DisplayName("Checking if Bills class is loading or not.")
     void processingRequestIsLoadedOrNot() {
        assertThat(memberPolicyObj).isNotNull();
    }

	@Test
	@DisplayName("Checking if Member Policy class is responding correctly or not.")
	void testingMemberPolicy() throws ParseException{
		
		int tenure = 5;
		
		memberPolicyObj.setMemberId("M101");
		memberPolicyObj.setPolicyId("P1001");
		memberPolicyObj.setPremiumLastDate("2022-04-04");
		memberPolicyObj.setSubscriptionDate("2022-08-04");
		memberPolicyObj.setTenure(tenure);
		
		assertEquals("M101", memberPolicyObj.getMemberId());
		assertEquals("P1001", memberPolicyObj.getPolicyId());
		assertEquals("2022-04-04", memberPolicyObj.getPremiumLastDate());
		assertEquals("2022-08-04", memberPolicyObj.getSubscriptionDate());
		assertEquals(tenure, memberPolicyObj.getTenure());
	}
}

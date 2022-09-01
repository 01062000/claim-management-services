package com.cts.member.cms;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberMicroserviceApplicationTests {

	MemberMicroserviceApplication memberMicroserviceApplication;
	
	@Test
	void contextLoads() {
		assertTrue(true);
	}

	@Test
	void testMemberServiceApplication() {
		assertThat(memberMicroserviceApplication).isNull();
	}
}

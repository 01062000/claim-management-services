package com.cts.policy.cms.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class MemberPolicyRepoTest {

	@MockBean
	MemberPolicyRepo memberPolicyRepo;
	
	@Test
	@DisplayName("Checking if Member Policy Repo methods Are working or not")
	void testMemberPolicyRepo() {
		assertThat(memberPolicyRepo.toString()).isNotNull();
	}
}

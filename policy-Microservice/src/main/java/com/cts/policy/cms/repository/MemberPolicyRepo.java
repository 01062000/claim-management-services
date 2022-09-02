package com.cts.policy.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.policy.cms.model.MemberPolicy;

public interface MemberPolicyRepo extends JpaRepository<MemberPolicy,String>{
	

}

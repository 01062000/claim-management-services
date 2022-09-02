package com.cts.policy.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.policy.cms.model.Policy;

@Repository
public interface PolicyRepo extends JpaRepository<Policy,String>{
	public Optional<Policy> findByPolicyType(String policyType);
}

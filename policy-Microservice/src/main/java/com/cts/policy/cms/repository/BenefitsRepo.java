package com.cts.policy.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.policy.cms.model.Benefits;

@Repository
public interface BenefitsRepo extends JpaRepository<Benefits,String>{
	public Optional<Benefits> findByBenefitName(String benefitName);
}

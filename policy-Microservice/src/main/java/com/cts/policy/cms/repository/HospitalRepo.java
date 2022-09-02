package com.cts.policy.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.policy.cms.model.Hospital;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital,String>{
	public Optional<Hospital> findByName(String name);
}

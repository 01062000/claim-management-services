package com.cts.auth.cms.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.auth.cms.model.AuthenticationRequest;

@Repository
public interface AuthRequestRepo extends JpaRepository<AuthenticationRequest, String> {
	public Optional<AuthenticationRequest> findByUsername(String username);
}
 
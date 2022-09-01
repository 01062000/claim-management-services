package com.cts.claim.cms.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cts.claim.cms.model.Claim;

@Repository
@Transactional
public interface ClaimRepo extends JpaRepository<Claim, Integer> {
	@Query(value = "select status from Claim c where c.claimId=?1")
	String getStatus(String id);

	@Query(value = "select description from Claim c where c.claimId=?1")
	String getDescription(String id);
	
	public List<Claim> findAllBymemberId(String memberId);
}

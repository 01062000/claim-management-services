package com.cts.member.cms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.member.cms.model.Bills;

@Repository
public interface BillsRepo extends JpaRepository<Bills, String> {

}
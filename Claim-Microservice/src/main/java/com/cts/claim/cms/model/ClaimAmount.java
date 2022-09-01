package com.cts.claim.cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ClaimAmount {
	
	@JsonProperty
	private double eligibleAmount;

	@JsonIgnore
	public double getEligibleAmount() {
		return eligibleAmount;
	}
	
	@JsonIgnore
	public void setEligibleAmount(double eligibleAmount) {
		this.eligibleAmount = eligibleAmount;
	}
	
	@JsonIgnore
	public ClaimAmount(double eligibleAmount) {
		super();
		this.eligibleAmount = eligibleAmount;
	}
	
	
	
	
	

}
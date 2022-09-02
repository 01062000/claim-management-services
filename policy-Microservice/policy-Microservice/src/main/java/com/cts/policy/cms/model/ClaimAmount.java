package com.cts.policy.cms.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClaimAmount {
	
	@JsonProperty
	private double eligibleAmount;

	/*@JsonIgnore 
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
	}*/
}

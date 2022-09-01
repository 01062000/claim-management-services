package com.cts.claim.cms.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ListOfBenefits {
	
	@JsonProperty
	 private List<Benefits> benefits;

	@JsonIgnore
	public List<Benefits> getBenefits() {
		return benefits;
	}

	@JsonIgnore
	public void setBenefits(List<Benefits> benefits) {
		this.benefits = benefits;
	}

	@JsonIgnore
	public ListOfBenefits(List<Benefits> benefits) {
		super();
		this.benefits = benefits;
	}
}
package com.cts.policy.cms.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ListOfBenefits {
	
	@JsonProperty
	 private Set<Benefits> benefits;

	/*@JsonIgnore
	public Set<Benefits> getBenefits() {
		return benefits;
	}

	@JsonIgnore
	public void setBenefits(Set<Benefits> benefits) {
		this.benefits = benefits;
	}
*/
	@JsonIgnore
	public ListOfBenefits(Set<Benefits> benefits) {
		super();
		this.benefits = benefits;
	}
}

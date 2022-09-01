package com.cts.claim.cms.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Provider {
	
	@JsonProperty
	private List<Hospital> providers;
		@JsonIgnore
		public Provider(List<Hospital> providers) {
			this.providers=providers;
		}
		@JsonIgnore
		public List<Hospital> getProviders() {
			return providers;
		}
		@JsonIgnore
		public void setProviders(List<Hospital> providers) {
			this.providers = providers;
		}
		
		
		

}
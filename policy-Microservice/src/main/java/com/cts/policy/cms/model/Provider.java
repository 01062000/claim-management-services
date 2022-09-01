package com.cts.policy.cms.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Provider {
	
	@JsonProperty
	private Set<Hospital> providers;
		@JsonIgnore
		public Provider(Set<Hospital> providers) {
			this.providers=providers;
		}
		@JsonIgnore
		public Set<Hospital> getProviders() {
			return providers;
		}
		@JsonIgnore
		public void setProviders(Set<Hospital> providers) {
			this.providers = providers;
		}
		
		
		

}

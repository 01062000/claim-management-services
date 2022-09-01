package com.cts.claim.cms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClaimStatus {
	private String claimId;
	private String claimStatus;
	private String claimDescription;

}
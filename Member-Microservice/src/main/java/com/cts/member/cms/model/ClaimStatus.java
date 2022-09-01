package com.cts.member.cms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimStatus {
	
	private String claimId;
	private String claimStatus;
	private String claimDescription;
}

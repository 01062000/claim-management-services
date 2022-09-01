package com.cts.member.cms.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDetails {
	
	static int d = 101;
	private String claimId = generateClaimId();
	private String status;
	private String description;
	private String remarks;
	private double claimAmount;
    private String hospitalId;
    private String benefitId;
    private String policyId;
    private String memberId;
	
    public String generateClaimId(){
		 String s = "C";
		 s=s+d;
		 d++;
		 return s;
    }
    
}

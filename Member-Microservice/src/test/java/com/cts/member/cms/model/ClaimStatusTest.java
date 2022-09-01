package com.cts.member.cms.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClaimStatusTest {

    
    ClaimStatus claimStatusDTO;
    @Test
     void testClaimStatusDTO() {
        claimStatusDTO = new ClaimStatus();
        claimStatusDTO.setClaimId("ABBB-hdh4h-yryyr");
        assertEquals("ABBB-hdh4h-yryyr",claimStatusDTO.getClaimId());
       
        claimStatusDTO = new ClaimStatus();
        claimStatusDTO.setClaimStatus("Pending");
        assertEquals("Pending",claimStatusDTO.getClaimStatus());
        
        claimStatusDTO = new ClaimStatus();
        claimStatusDTO.setClaimDescription("Wait for the Approval");
        assertEquals("Wait for the Approval",claimStatusDTO.getClaimDescription());
        
        claimStatusDTO = new ClaimStatus("ATATTA-YYYY", "Approved", "Your claims has been approved");
        assertEquals("ATATTA-YYYY",claimStatusDTO.getClaimId());
        assertEquals("Approved",claimStatusDTO.getClaimStatus());
        assertEquals("Your claims has been approved",claimStatusDTO.getClaimDescription());
        		
    }
}

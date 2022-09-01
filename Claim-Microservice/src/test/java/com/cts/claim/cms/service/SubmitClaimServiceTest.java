package com.cts.claim.cms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.claim.cms.client.PolicyClient;
import com.cts.claim.cms.model.ListOfBenefits;
import com.cts.claim.cms.model.ClaimAmount;
import com.cts.claim.cms.model.Claims;
import com.cts.claim.cms.model.ClaimStatus;
import com.cts.claim.cms.model.Provider;
import com.cts.claim.cms.exception.PolicyException;
import com.cts.claim.cms.model.Benefits;
import com.cts.claim.cms.model.Hospital;
import com.cts.claim.cms.repository.ClaimRepo;



@SpringBootTest
class SubmitClaimServiceTest {

	@Autowired
	SubmitClaimService submitClaimService;
	@MockBean
	ClaimRepo claimRepo;
	@MockBean
	PolicyClient policyClient;
	
	@Test
	@DisplayName("Checking if SubmitClaimService class is loading or not")
	void submitClaimServiceIsLoadedOrNot() {
		assertThat(submitClaimService).isNotNull();
	}
	
	@Test
	@DisplayName("Checking if SubmitClaim method is working or not with valid claim object")
	void testSubmitClaimMethodWithValidClaimObject() {
		Claims claims=new Claims("C101","Pending","Verified","Nothing",12000.0,"H1","B101","P1001","M101");
		
		String token="AAA";
		Hospital hospital1 = new Hospital("H1","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H2","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H3","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		Benefits b3 = new Benefits("B103","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits("B104","Ambulance charges upto 3000 covered");
		Benefits b5 = new Benefits("B105","Ambulance charges upto 4000 covered");
		List<Benefits> benefitList=new ArrayList<>();
		benefitList.add(b1);
		benefitList.add(b2);
		benefitList.add(b3);
		benefitList.add(b4);
		benefitList.add(b5);
		
		Provider provider=new Provider(hospitalList);
		ListOfBenefits listOfBenefits=new ListOfBenefits(benefitList);
		ClaimAmount claimAmount=new ClaimAmount(120000);
		
		when(policyClient.getChainOfProviders("P1001", token)).thenReturn(new ResponseEntity<Provider>(provider,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P1001", "M101", token)).thenReturn(new ResponseEntity<ListOfBenefits>(listOfBenefits,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P1001", "M101", token)).thenReturn(new ResponseEntity<ClaimAmount>(claimAmount,HttpStatus.OK));
		
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("C101");
		claimStatus.setClaimStatus("Pending Action");
		claimStatus.setClaimDescription("All the fields are successfully verified! Please wait for furthur action");
		
		
		assertEquals(claimStatus.getClaimId(), submitClaimService.submitClaim(claims, token).getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), submitClaimService.submitClaim(claims, token).getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),submitClaimService.submitClaim(claims, token).getBody().getClaimDescription()); 
		
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with Wrong Policy ID")
	void testSubmitClaimWithInvalidPolicyId() {
		Claims claims=new Claims("C101","Pending","Verified","Nothing",12000.0,"H1","B101","P1001","M101");
		String token="AAA";
		when(policyClient.getChainOfProviders("P1001", token)).thenThrow(PolicyException.class);
		assertThrows(PolicyException.class, () -> submitClaimService.submitClaim(claims,token));
		
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with valid Policy ID But Null record")
	void testSubmitClaimWithNullProvider() {
		Claims claims=new Claims("C101","Pending","Verified","Nothing",12000.0,"H1","B101","P1001","M101");
		String token="AAA";
		
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		Benefits b3 = new Benefits("B103","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits("B104","Ambulance charges upto 3000 covered");
		Benefits b5 = new Benefits("B105","Ambulance charges upto 4000 covered");
		List<Benefits> benefitList=new ArrayList<>();
		benefitList.add(b1);
		benefitList.add(b2);
		benefitList.add(b3);
		benefitList.add(b4);
		benefitList.add(b5);
		
		Provider provider=new Provider();
		ListOfBenefits listOfBenefits=new ListOfBenefits(benefitList);
		ClaimAmount claimAmount=new ClaimAmount(120000);
		
		when(policyClient.getChainOfProviders("P1001", token)).thenReturn(new ResponseEntity<Provider>(provider,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P1001", "M101", token)).thenReturn(new ResponseEntity<ListOfBenefits>(listOfBenefits,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P1001", "M101", token)).thenReturn(new ResponseEntity<ClaimAmount>(claimAmount,HttpStatus.OK));
		
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("C101");
		claimStatus.setClaimStatus("Claim Rejected");
		claimStatus.setClaimDescription("Providers Details are not valid");
		
		assertEquals(claimStatus.getClaimId(), submitClaimService.submitClaim(claims, token).getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), submitClaimService.submitClaim(claims, token).getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),submitClaimService.submitClaim(claims, token).getBody().getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with valid Benefit ID But Null record")
	void testSubmitClaimWithNullBenefits() {
		Claims claims=new Claims("C101","Pending","Verified","Nothing",12000.0,"H1","B101","P1001","M101");
		String token="AAA";
		
		Hospital hospital1 = new Hospital("H1","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H2","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H3","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		
		
		
		Provider provider=new Provider(hospitalList);
		ListOfBenefits listOfBenefits=new ListOfBenefits();
		ClaimAmount claimAmount=new ClaimAmount(120000.0);
		
		/* 
		 * The thenReturn() methods lets you define the return value when a particular method of the mocked object is been called. 
		 * The below snippet shows how we use thenReturn to check for multiple values.
		*/
		when(policyClient.getChainOfProviders("P1001", token)).thenReturn(new ResponseEntity<Provider>(provider,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P1001", "M101", token)).thenReturn(new ResponseEntity<ListOfBenefits>(listOfBenefits,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P1001", "M101", token)).thenReturn(new ResponseEntity<ClaimAmount>(claimAmount,HttpStatus.OK));
		
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("C101");
		claimStatus.setClaimStatus("Claim Rejected");
		claimStatus.setClaimDescription("Benefits Details are not valid");
		
		/*
		 * If you want to test equality of two objects, you have the following methods
		 * assertEquals(expected, actual)
		 * It will return true if: expected.equals( actual ) returns true.
		*/
		assertEquals(claimStatus.getClaimId(), submitClaimService.submitClaim(claims, token).getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), submitClaimService.submitClaim(claims, token).getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),submitClaimService.submitClaim(claims, token).getBody().getClaimDescription()); 
	}
	
	@Test
	@DisplayName("Checking Submit Claim Method with Invalid Amount record")
	void testSubmitClaimWithInvalidAmount() {
		Claims claims=new Claims("C101","Pending","Verified","Nothing",12000.0,"H1","B101","P1001","M101");
		String token="AAA";
		
		Hospital hospital1 = new Hospital("H1","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H2","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H3","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		Benefits b3 = new Benefits("B103","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits("B104","Ambulance charges upto 3000 covered");
		Benefits b5 = new Benefits("B105","Ambulance charges upto 4000 covered");
		List<Benefits> benefitList=new ArrayList<>();
		benefitList.add(b1);
		benefitList.add(b2);
		benefitList.add(b3);
		benefitList.add(b4);
		benefitList.add(b5);
		
		Provider provider=new Provider(hospitalList);
		ListOfBenefits listOfBenefits=new ListOfBenefits(benefitList);
		ClaimAmount claimAmount=new ClaimAmount(120.0);
		
		when(policyClient.getChainOfProviders("P1001", token)).thenReturn(new ResponseEntity<Provider>(provider,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P1001", "M101", token)).thenReturn(new ResponseEntity<ListOfBenefits>(listOfBenefits,HttpStatus.OK));
		when(policyClient.getEligibleAmount("P1001", "M101", token)).thenReturn(new ResponseEntity<ClaimAmount>(claimAmount,HttpStatus.OK));
		
		ClaimStatus claimStatus=new ClaimStatus();
		claimStatus.setClaimId("C101");
		claimStatus.setClaimStatus("Claim Rejected");
		claimStatus.setClaimDescription("Claim amount is not valid");
		
		assertEquals(claimStatus.getClaimId(), submitClaimService.submitClaim(claims, token).getBody().getClaimId()); 
		assertEquals(claimStatus.getClaimStatus(), submitClaimService.submitClaim(claims, token).getBody().getClaimStatus()); 
		assertEquals(claimStatus.getClaimDescription(),submitClaimService.submitClaim(claims, token).getBody().getClaimDescription()); 
	}
	
	
	@Test
	@DisplayName("Checking Submit Claim Method with Wrong Member ID")
	void testSubmitClaimWithInvalidMemeberId() {
		Claims claims=new Claims("C101","Pending","Verified","Nothing",12000.0,"H1","B101","P1001","M101");
		String token="AAA";
		Hospital hospital1 = new Hospital("H1","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H2","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H3","Fortis Escorts Heart Institute","Delhi-Okhla");
		List<Hospital> hospitalList=new ArrayList<>();
		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		Provider provider=new Provider(hospitalList);
		when(policyClient.getChainOfProviders("P1001", token)).thenReturn(new ResponseEntity<Provider>(provider,HttpStatus.OK));
		when(policyClient.getEligibleBenefits("P1001","M101", token)).thenThrow(PolicyException.class);
		
		/* 
		 * You can use assertThrows(), which allows you to test multiple exceptions within the same test. 
		 * With support for lambdas in Java 8, this is the canonical way to test for exceptions in JUnit.
		*/
		assertThrows(PolicyException.class, () -> submitClaimService.submitClaim(claims,token));
		
	}
	
	
	
	
	
	
}

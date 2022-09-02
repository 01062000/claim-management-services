package com.cts.policy.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import com.cts.policy.cms.model.Benefits;
import com.cts.policy.cms.model.Hospital;
import com.cts.policy.cms.model.MemberPolicy;
import com.cts.policy.cms.model.Policy;
import com.cts.policy.cms.repository.BenefitsRepo;
import com.cts.policy.cms.repository.HospitalRepo;
import com.cts.policy.cms.repository.MemberPolicyRepo;
import com.cts.policy.cms.repository.PolicyRepo;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan(basePackages = "com.cts.policy.cms")
@EnableFeignClients
@Slf4j
public class PolicyMicroserviceApplication implements CommandLineRunner{

	public static void main(String[] args) {
		log.info("Inside Policy Microservice...");
		SpringApplication.run(PolicyMicroserviceApplication.class, args);
	}
	
	@Autowired
	private HospitalRepo hospitalRepo;
	
	@Autowired
	private PolicyRepo policyRepo;
	
	@Autowired
	private BenefitsRepo benefitsRepo;
	
	@Autowired
	private MemberPolicyRepo memberRepo;
	
	@Override
	public void run(String... args) throws Exception{
				
		Policy policy1 = new Policy("P1001","Health Plus Classic",500000,15639);
		Policy policy2 = new Policy("P1002","Health Plus Enhanced",3000000,17361);
		Policy policy3 = new Policy("P1003","Health Plus Premium",1000000,22085);
		
		Hospital hospital1 = new Hospital("H1","Apollo Hospital","Delhi-Indraprastha");
		Hospital hospital2 = new Hospital("H2","Artemis Hospital","Gurgaon");
		Hospital hospital3 = new Hospital("H3","Fortis Escorts Heart Institute","Delhi-Okhla");
		Hospital hospital4 = new Hospital("H4","BLK Super Speciality Hospital","Delhi-New Delhi");
		Hospital hospital5 = new Hospital("H5","Max Superspecialty Hospital, Saket","Delhi-New Delhi");
		Hospital hospital6 = new Hospital("H6","Fortis Memorial Research Institute","Gurgaon");
		
		policy1.getHospitals().add(hospital5);
		policy1.getHospitals().add(hospital1);
		policy1.getHospitals().add(hospital2);
		policy1.getHospitals().add(hospital3);
		policy1.getHospitals().add(hospital4);
		policy1.getHospitals().add(hospital6);
		
		policy2.getHospitals().add(hospital1);
		policy2.getHospitals().add(hospital2);
		policy2.getHospitals().add(hospital3);
		policy2.getHospitals().add(hospital4);
		policy2.getHospitals().add(hospital5);
		policy2.getHospitals().add(hospital6);
		
		policy3.getHospitals().add(hospital5);
		policy3.getHospitals().add(hospital1);
		policy3.getHospitals().add(hospital6);
		policy3.getHospitals().add(hospital3);
		policy3.getHospitals().add(hospital4);
		policy3.getHospitals().add(hospital2);
		
		Benefits b1 = new Benefits("B101","Coverage for COVID-19");
		Benefits b2 = new Benefits("B102","Coverage for hospitalization at home");
		Benefits b3 = new Benefits("B103","Ambulance charges upto 2000 covered");
		Benefits b4 = new Benefits("B104","Ambulance charges upto 3000 covered");
		Benefits b5 = new Benefits("B105","Ambulance charges upto 4000 covered");
		Benefits b6 = new Benefits("B106","Hospitalization charges for Premium Twin Sharing room covered");
		Benefits b7 = new Benefits("B107","Hospitalization charges for Deluxe room covered");
		Benefits b8 = new Benefits("B108","Hospitalization charges for Premium Deluxe room covered");
		
		policy1.getBenefits().add(b1);
		policy1.getBenefits().add(b2);
		policy1.getBenefits().add(b3);
		policy1.getBenefits().add(b4);
		policy1.getBenefits().add(b5);
		policy1.getBenefits().add(b6);
		policy1.getBenefits().add(b7);
		policy1.getBenefits().add(b8);
		
		MemberPolicy m1 = new MemberPolicy("M101","P1001",2,"01/07/2022","01/08/2022");
		MemberPolicy m2 = new MemberPolicy("M102","P1001",13,"02/07/2022","02/08/2022");
		MemberPolicy m3 = new MemberPolicy("M103","P1001",2,"10/11/2022","10/12/2022");
		MemberPolicy m4 = new MemberPolicy("M104","P1001",13,"10/04/2019","10/05/2019");
		MemberPolicy m5 = new MemberPolicy("M105","P1001",13,"10/04/2022","10/05/2022");
		
		policy2.getBenefits().add(b1);
		policy2.getBenefits().add(b2);
		policy2.getBenefits().add(b3);
		policy2.getBenefits().add(b4);
		policy2.getBenefits().add(b5);
		policy2.getBenefits().add(b6);
		policy2.getBenefits().add(b7);
		policy2.getBenefits().add(b8);
		
		policy3.getBenefits().add(b1);
		policy3.getBenefits().add(b2);
		policy3.getBenefits().add(b3);
		policy3.getBenefits().add(b4);
		policy3.getBenefits().add(b5);
		policy3.getBenefits().add(b6);
		policy3.getBenefits().add(b7);
		policy3.getBenefits().add(b8);
		
		hospital1.getPolicies().add(policy1);
		hospital1.getPolicies().add(policy2);
		hospital1.getPolicies().add(policy3);
		
		hospital2.getPolicies().add(policy1);
		hospital2.getPolicies().add(policy2);
		hospital2.getPolicies().add(policy3);
		
		hospital3.getPolicies().add(policy2);
		hospital3.getPolicies().add(policy3);
		hospital3.getPolicies().add(policy1);
		
		hospital4.getPolicies().add(policy1);
		hospital4.getPolicies().add(policy2);
		hospital4.getPolicies().add(policy3);
		
		hospital5.getPolicies().add(policy1);
		hospital5.getPolicies().add(policy2);
		hospital5.getPolicies().add(policy3);
		
		hospital6.getPolicies().add(policy2);
		hospital6.getPolicies().add(policy3);
		hospital6.getPolicies().add(policy1);
		
		b1.getPolicyBenefits().add(policy1);
		b1.getPolicyBenefits().add(policy2);
		b1.getPolicyBenefits().add(policy3);
		
		b2.getPolicyBenefits().add(policy1);
		b2.getPolicyBenefits().add(policy2);
		b2.getPolicyBenefits().add(policy3);
		
		b3.getPolicyBenefits().add(policy1);
		b3.getPolicyBenefits().add(policy2);
		b3.getPolicyBenefits().add(policy3);
		
		b4.getPolicyBenefits().add(policy1);
		b4.getPolicyBenefits().add(policy2);
		b4.getPolicyBenefits().add(policy3);
		
		b5.getPolicyBenefits().add(policy1);
		b5.getPolicyBenefits().add(policy2);
		b5.getPolicyBenefits().add(policy3);
		
		b6.getPolicyBenefits().add(policy1);
		b6.getPolicyBenefits().add(policy2);
		b6.getPolicyBenefits().add(policy3);
		
		b7.getPolicyBenefits().add(policy1);
		b7.getPolicyBenefits().add(policy2);
		b7.getPolicyBenefits().add(policy3);
		
		b8.getPolicyBenefits().add(policy1);
		b8.getPolicyBenefits().add(policy2);
		b8.getPolicyBenefits().add(policy3);
		
		memberRepo.save(m1);
		memberRepo.save(m2);
		memberRepo.save(m3);
		memberRepo.save(m4);
		memberRepo.save(m5);
		
		policyRepo.save(policy1);
		policyRepo.save(policy2);
		policyRepo.save(policy3);
		
		benefitsRepo.save(b1);
		benefitsRepo.save(b2);
		benefitsRepo.save(b3);
		benefitsRepo.save(b4);
		benefitsRepo.save(b5);
		benefitsRepo.save(b6);
		benefitsRepo.save(b7);
		benefitsRepo.save(b8);
		
		hospitalRepo.save(hospital1);
		hospitalRepo.save(hospital2);
		hospitalRepo.save(hospital3);
		hospitalRepo.save(hospital4);
		hospitalRepo.save(hospital5);
		hospitalRepo.save(hospital6);
	}
}

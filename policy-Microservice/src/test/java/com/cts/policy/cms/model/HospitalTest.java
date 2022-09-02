package com.cts.policy.cms.model;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HospitalTest {

	Hospital hospitalObj = new Hospital();
	
	@Test
    @DisplayName("Checking if Hospital class is loading or not.")
     void processingRequestIsLoadedOrNot() {
        assertThat(hospitalObj).isNotNull();
    }
	
	@Test
	@DisplayName("Checking if Hospital class is responding correctly or not.")
	void testingHospital() throws ParseException{
		hospitalObj.setHospitalId("H1");
		hospitalObj.setName("Apollo Hospital");
		hospitalObj.setLocation("Delhi-Indraprastha");
		
		assertEquals("H1", hospitalObj.getHospitalId());
		assertEquals("Apollo Hospital", hospitalObj.getName());
		assertEquals("Delhi-Indraprastha", hospitalObj.getLocation());
	}
}

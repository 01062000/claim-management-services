package com.cts.claim.cms.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class Hospital {

	private String hospitalId;
	
	
	public String getHospitalId() {
		return hospitalId;
	}


	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	private String name;

	private String location;
	

	public Hospital(String hospitalId, String name, String location) {
		super();
		this.hospitalId = hospitalId;
		this.name = name;
		this.location = location;
	}
	
}

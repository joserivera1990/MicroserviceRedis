package com.architecture.RedisAWS.service;

import java.sql.Timestamp;

public class Audit {

	private Integer identificationNumber;
	
	private String category;
	
	private String event;
	
	private Timestamp dateRegister;

	public Integer getIdentificationNumber() {
		return identificationNumber;
	}

	public void setIdentificationNumber(Integer identificationNumber) {
		this.identificationNumber = identificationNumber;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public Timestamp getDateRegister() {
		return dateRegister;
	}

	public void setDateRegister(Timestamp dateRegister) {
		this.dateRegister = dateRegister;
	}


}

package com.capgemini.dnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Distributor")
public class DistributorEntity {

	@Id
	@Column(name = "DistributorId")
	private String distributorId;
	@Column(name = "DistributorName")
	private String name;
	@Column(name = "Address")
	private String address;
	@Column(name = "Email")
	private String emailId;
	@Column(name = "PhoneNumber")
	private String phoneNo;

	public DistributorEntity() {

	}

	public DistributorEntity(String distributorId, String name, String address, String emailId, String phoneNo) {
		super();
		this.distributorId = distributorId;
		this.name = name;
		this.address = address;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
	}



	public String getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}

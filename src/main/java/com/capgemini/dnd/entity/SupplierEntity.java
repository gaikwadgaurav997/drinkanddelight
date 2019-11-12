package com.capgemini.dnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Supplier")
public class SupplierEntity {

	@Id
	@Column(name = "SupplierId")
	private String supplierId;
	
	@Column(name = "SupplierName")
	private String name;
	
	@Column(name = "Address")
	private String address;
	
	@Column(name = "Email")
	private String emailId;
	
	@Column(name = "PhoneNumber")
	private int phoneNo;

	public SupplierEntity(String supplierId, String name, String address, String emailId, int phoneNo) {
		this.supplierId = supplierId;
		this.name = name;
		this.address = address;
		this.emailId = emailId;
		this.phoneNo = phoneNo;
	}

	public SupplierEntity() {
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
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

	public int getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(int phoneNo) {
		this.phoneNo = phoneNo;
	}

}

package com.capgemini.dnd.dto;

public class Distributor {
	private String distributorId;
	private String name;
	private int addressId;
	private String emailId;
	private String phoneNo;
	private Address address;
	

	public Distributor() {
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

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Distributor [distributorId=" + distributorId + ", name=" + name + ", addressId=" + addressId
				+ ", emailId=" + emailId + ", phoneNo=" + phoneNo + ", address=" + address + "]";
	}

}

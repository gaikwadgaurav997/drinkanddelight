package com.capgemini.dnd.dto;

public class Address {
	private int addressId;
	private int plotNo;
	private String buildingName;	
	private String streetName;	
	private String landmark;
	private String city;
	private String pincode;
	private String state;
	private boolean defaultAddress;	
	
	
	public Address() {
	}
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public int getPlotNo() {
		return plotNo;
	}
	public void setPlotNo(int plotNo) {
		this.plotNo = plotNo;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getLandmark() {
		return landmark;
	}
	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public boolean isDefaultAddress() {
		return defaultAddress;
	}
	public void setDefaultAddress(boolean defaultAddress) {
		this.defaultAddress = defaultAddress;
	}
	
	@Override
	public String toString() {
		return "Address [addressId=" + addressId + ", plotNo=" + plotNo + ", buildingName=" + buildingName
				+ ", streetName=" + streetName + ", landmark=" + landmark + ", city=" + city + ", pincode=" + pincode
				+ ", state=" + state + ", defaultAddress=" + defaultAddress + "]";
	}
	
}
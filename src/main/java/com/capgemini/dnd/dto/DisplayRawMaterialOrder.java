package com.capgemini.dnd.dto;

public class DisplayRawMaterialOrder {
	
private String deliveryStatus;
private String supplierid;
private String startdate;
private String endDate;

	public String getDeliveryStatus() {
	return deliveryStatus;
}

public void setDeliveryStatus(String deliveryStatus) {
	this.deliveryStatus = deliveryStatus;
}

public String getSupplierid() {
	return supplierid;
}

public void setSupplierid(String supplierid) {
	this.supplierid = supplierid;
}

public String getStartdate() {
	return startdate;
}

public void setStartdate(String startdate) {
	this.startdate = startdate;
}

public String getEndDate() {
	return endDate;
}

public void setEndDate(String endDate) {
	this.endDate = endDate;
}

	@Override
public String toString() {
	return "DisplayRawMaterialOrder [deliveryStatus=" + deliveryStatus + ", supplierid=" + supplierid + ", startdate="
			+ startdate + ", endDate=" + endDate + "]";
}

	public DisplayRawMaterialOrder() {
	
	}
	public DisplayRawMaterialOrder(String deliveryStatus,String supplierid,String startdate,String endDate) 
	
	{ this.deliveryStatus = deliveryStatus;
	  this.supplierid = supplierid;
	  this.startdate = startdate;
	  this.endDate = endDate;
		
	}

}



package com.capgemini.dnd.dto;

public class DisplayProductOrder {

	private String deliveryStatus;
	private String distributorid;
	private String startdate;
	private String endDate;

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getDistributorid() {
		return distributorid;
	}

	public void setDistributorid(String distributorid) {
		this.distributorid = distributorid;
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
		return "DisplayProductOrder [deliveryStatus=" + deliveryStatus + ", distributorid=" + distributorid
				+ ", startdate=" + startdate + ", endDate=" + endDate + "]";
	}

	public DisplayProductOrder() {

	}

	public DisplayProductOrder(String deliveryStatus, String distributorid, String startdate, String endDate)

	{
		this.deliveryStatus = deliveryStatus;
		this.distributorid = distributorid;
		this.startdate = startdate;
		this.endDate = endDate;

	}
}
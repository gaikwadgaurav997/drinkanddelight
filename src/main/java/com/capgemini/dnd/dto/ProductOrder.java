package com.capgemini.dnd.dto;

import java.util.Date;




public class ProductOrder {

	private String orderId;
	private static int orderIdCount = 1;
	private String name;
	private String distributorId;
	private double quantityValue; 
	private String quantityUnit;
	private Date dateOfOrder;
	private Date dateofDelivery;
	private double pricePerUnit;
	private double totalPrice;
	private String deliveryStatus;
	private String warehouseId;

	public ProductOrder(String name, String distributorId, double quantityValue, String quantityUnit,
			Date dateofDelivery, double pricePerUnit, String warehouseId) {
		super();
		this.orderId = "PID" + orderIdCount++;
		this.name = name;
		this.distributorId = distributorId;
		this.quantityValue = quantityValue;
		this.quantityUnit = quantityUnit;
		this.dateOfOrder = new Date();
		this.dateofDelivery = dateofDelivery;
		this.deliveryStatus = "pending";
		this.pricePerUnit = pricePerUnit;
//		this.totalPrice = this.quantityValue * this.pricePerUnit;
		this.warehouseId = warehouseId;
	}

	public ProductOrder(String orderId, String name, String pid, String distributorId, double quantityValue,
			String quantityUnit, Date dateOfOrder, Date dateofDelivery, double pricePerUnit, double totalPrice,
			String deliveryStatus, String warehouseId) {
		super();
		this.orderId = orderId;
		this.name = name;
		this.distributorId = distributorId;
		this.quantityValue = quantityValue;
		this.quantityUnit = quantityUnit;
		this.dateOfOrder = dateOfOrder;
		this.dateofDelivery = dateofDelivery;
		this.pricePerUnit = pricePerUnit;
		this.totalPrice = totalPrice;
		this.deliveryStatus = deliveryStatus;
		this.warehouseId = warehouseId;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public static void setOrderIdCount(int orderIdCount) {
		ProductOrder.orderIdCount = orderIdCount;
	}

	public String getDistributorId() {
		return this.distributorId;
	}

	public void setDistributorId(String distributorId) {
		this.distributorId = distributorId;
	}

	public double getQuantityValue() {
		return this.quantityValue;
	}

	public void setQuantityValue(double quantityValue) {
		this.quantityValue = quantityValue;
	}

	public String getQuantityUnit() {
		return this.quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public Date getDateOfOrder() {
		return this.dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Date getDateofDelivery() {
		return this.dateofDelivery;
	}

	public void setDateofDelivery(Date dateofDelivery) {
		this.dateofDelivery = dateofDelivery;
	}

	public double getPricePerUnit() {
		return this.pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getWarehouseId() {
		return this.warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getDeliveryStatus() {
		return this.deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public String toString() {
		return "ProductOrder [orderId=" + this.orderId + ", name=" + this.name + ", distributorId="
				+ this.distributorId + ", quantityValue=" + this.quantityValue + ", quantityUnit=" + this.quantityUnit
				+ ", dateOfOrder=" + this.dateOfOrder + ", dateofDelivery=" + this.dateofDelivery + ", pricePerUnit="
				+ this.pricePerUnit + ", totalPrice=" + this.totalPrice + ", deliveryStatus=" + this.deliveryStatus + ", warehouseId="
				+ this.warehouseId + "]";
	}
}
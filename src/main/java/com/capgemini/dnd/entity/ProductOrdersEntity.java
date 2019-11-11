package com.capgemini.dnd.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "ProductOrders", uniqueConstraints = {
		@UniqueConstraint(columnNames = "orderId"),
		})
public class ProductOrdersEntity {

	public ProductOrdersEntity() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderId")
	private int orderId;
	
    @Column(name = "name")
	private String name;
    
    @Column(name = "distributorid")
	private String distributorId;
    
    @Column(name = "quantityvalue")
	private double quantityValue; 
	
    @Column(name = "quantityunit")
    private String quantityUnit;
    
    @Column(name = "dateoforder")
	private Date dateOfOrder;
    
    @Column(name = "dateofdelivery")
	private Date dateofDelivery;
    
    @Column(name = "priceperunit")
	private double pricePerUnit;
    
    @Column(name = "totalprice")
	private double totalPrice;
    
    @Column(name = "deliverystatus")
	private String deliveryStatus;
    
    @Column(name = "warehouseid")
	private String warehouseId;

	public ProductOrdersEntity(String name, String distributorId, double quantityValue, String quantityUnit,
			Date dateofDelivery, double pricePerUnit, String warehouseId) {
		super();
		this.name = name;
		this.distributorId = distributorId;
		this.quantityValue = quantityValue;
		this.quantityUnit = quantityUnit;
		this.dateOfOrder = new Date();
		this.dateofDelivery = dateofDelivery;
		this.deliveryStatus = "pending";
		this.pricePerUnit = pricePerUnit; 
		this.totalPrice = this.pricePerUnit * this.quantityValue;
		this.warehouseId = warehouseId;
	}

	public ProductOrdersEntity(String name, String distributorId, double quantityValue,
			String quantityUnit, Date dateOfOrder, Date dateofDelivery, double pricePerUnit, double totalPrice,
			String deliveryStatus, String warehouseId) {
		super();
		this.name = name;
		this.distributorId = distributorId;
		this.quantityValue = quantityValue;
		this.quantityUnit = quantityUnit;
		this.dateOfOrder = dateOfOrder;
		this.dateofDelivery = dateofDelivery;
		this.pricePerUnit = pricePerUnit;
		this.totalPrice = this.quantityValue * this.pricePerUnit;
		this.deliveryStatus = deliveryStatus;
		this.warehouseId = warehouseId;
	}

	public int getOrderId() {
		return this.orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
}

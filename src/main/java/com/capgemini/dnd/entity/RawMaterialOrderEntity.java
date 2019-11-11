package com.capgemini.dnd.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;


@Component
@Entity
@Table(name = "RawmaterialOrders")
public class RawMaterialOrderEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderid")
	private int orderId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "supplierid")
	private String supplierId;
	
	@Column(name = "quantityvalue")
	private double quantityValue;
	
	@Column(name = "quantityunit")
	private String quantityUnit;
	
	@Column(name = "dateoforder")
	private Date dateOfOrder;
	
	@Column(name = "dateofdelivery")
	private Date dateOfDelivery;
	
	@Column(name = "priceperunit")
	private double pricePerUnit;
	
	@Column(name = "totalprice")
	private double totalPrice;
	
	@Column(name = "deliverystatus")
	private String deliveryStatus;
	
	@Column(name = "warehouseid")
	private String warehouseId;

	public RawMaterialOrderEntity(String name, String supplierId, double quantityValue, String quantityUnit,
			Date dateOfDelivery, double pricePerUnit, String warehouseId) {
		super();
		this.name = name;
		this.supplierId = supplierId;
		this.quantityValue = quantityValue;
		this.quantityUnit = quantityUnit;
		this.dateOfOrder = new Date();
		this.dateOfDelivery = dateOfDelivery;
		this.pricePerUnit = pricePerUnit;
		this.totalPrice = this.quantityValue * this.pricePerUnit;
		this.deliveryStatus = "PENDING";
		this.warehouseId = warehouseId;
	}

	public RawMaterialOrderEntity() {
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public double getQuantityValue() {
		return quantityValue;
	}

	public void setQuantityValue(double quantityValue) {
		this.quantityValue = quantityValue;
	}

	public String getQuantityUnit() {
		return quantityUnit;
	}

	public void setQuantityUnit(String quantityUnit) {
		this.quantityUnit = quantityUnit;
	}

	public Date getDateOfOrder() {
		return dateOfOrder;
	}

	public void setDateOfOrder(Date dateOfOrder) {
		this.dateOfOrder = dateOfOrder;
	}

	public Date getDateOfDelivery() {
		return dateOfDelivery;
	}

	public void setDateOfDelivery(Date dateOfDelivery) {
		this.dateOfDelivery = dateOfDelivery;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public String getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
}

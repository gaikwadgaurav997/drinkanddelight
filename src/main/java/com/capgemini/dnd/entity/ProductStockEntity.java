package com.capgemini.dnd.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "ProductStock")
public class ProductStockEntity {
	
	@Id
	@Column(name = "orderId")
	private int orderId;
	
	@Column(name = "exitDate")
	private Date exitDate;
	
	@Column(name = "manufacturingDate")
	private Date manufacturingDate;
	
	@Column(name = "expiryDate")
	private Date expiryDate;
	
	@Column(name = "warehouseId")
	private String warehouseId;
	
	@Column(name = "name")
	private String name;
        
    @Column(name = "quantityValue")
	private double quantityValue; 

    @Column(name = "quantityUnit")
    private String quantityUnit;
    
     
    @Column(name = "deliveryDate")
	private Date dateofDelivery;
    
    @Column(name = "price_per_unit")
	private double pricePerUnit;
    
    @Column(name = "price")
	private double totalPrice;
    
    @Column(name = "qualityCheck")
    private String qualityCheck;
    
  
    
 
	
	

//	public ProductStockEntity() {
//		
//	}







	@Override
	public String toString() {
		return "ProductStockEntity [orderId=" + orderId + ", exitDate=" + exitDate + ", manufacturingDate="
				+ manufacturingDate + ", expiryDate=" + expiryDate + ", warehouseId=" + warehouseId + ", name=" + name
				+ ", quantityValue=" + quantityValue + ", quantityUnit=" + quantityUnit + ", dateofDelivery="
				+ dateofDelivery + ", pricePerUnit=" + pricePerUnit + ", totalPrice=" + totalPrice + ", qualityCheck="
				+ qualityCheck + "]";
	}







	public int getOrderId() {
		return orderId;
	}







	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}







	public Date getExitDate() {
		return exitDate;
	}







	public void setExitDate(Date exitDate) {
		this.exitDate = exitDate;
	}







	public Date getManufacturingDate() {
		return manufacturingDate;
	}







	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}







	public Date getExpiryDate() {
		return expiryDate;
	}







	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}







	public String getWarehouseId() {
		return warehouseId;
	}







	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}







	public String getName() {
		return name;
	}







	public void setName(String name) {
		this.name = name;
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







	public Date getDateofDelivery() {
		return dateofDelivery;
	}







	public void setDateofDelivery(Date dateofDelivery) {
		this.dateofDelivery = dateofDelivery;
	}







	public double getPricePerUnit() {
		return pricePerUnit;
	}







	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}







	public double getTotalPrice() {
		return totalPrice;
	}







	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}







	public String getQualityCheck() {
		return qualityCheck;
	}







	public void setQualityCheck(String qualityCheck) {
		this.qualityCheck = qualityCheck;
	}



	



	

}

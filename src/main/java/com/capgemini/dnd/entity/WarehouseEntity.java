package com.capgemini.dnd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "Warehouse")
public class WarehouseEntity {

	@Id
	@Column(name = "warehouseId")
	private String warehouseId;
	
	@Column(name = "warehouseName")
	private String warehouseName;
	
	@Column(name = "managerId")
	private String managerId;
	
	@Column(name = "address")
	private String address;
	
	public WarehouseEntity(String warehouseId, String warehouseName, String managerId, String address) {
		this.warehouseId = warehouseId;
		this.warehouseName = warehouseName;
		this.managerId = managerId;
		this.address = address;
	}
	
	public WarehouseEntity() {
	}
	
	public String getWarehouseId() {
		return this.warehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		this.warehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return this.warehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		this.warehouseName = warehouseName;
	}
	public String getManagerId() {
		return this.managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getAddress() {
		return this.address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}

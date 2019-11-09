package com.capgemini.dnd.dto;

public class Warehouse {
	private String WarehouseId;
	private String WarehouseName;
	private String managerId;
	private String address;
	
	public Warehouse() {
		
	}
	
	public Warehouse(String warehouseId, String warehouseName, String managerId, String address) {
		WarehouseId = warehouseId;
		WarehouseName = warehouseName;
		this.managerId = managerId;
		this.address = address;
	}
	
	public String getWarehouseId() {
		return WarehouseId;
	}
	public void setWarehouseId(String warehouseId) {
		WarehouseId = warehouseId;
	}
	public String getWarehouseName() {
		return WarehouseName;
	}
	public void setWarehouseName(String warehouseName) {
		WarehouseName = warehouseName;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "Warehouse [WarehouseId=" + WarehouseId + ", WarehouseName=" + WarehouseName + ", managerId=" + managerId
				+ ", address=" + address + "]";
	}

}

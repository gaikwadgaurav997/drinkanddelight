package com.capgemini.dnd.service;


import java.util.ArrayList;
import java.util.List;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.customexceptions.DoesNotExistException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.DisplayRawMaterialOrder;
import com.capgemini.dnd.dto.Distributor;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;
import com.capgemini.dnd.dto.Supplier;
import com.capgemini.dnd.entity.RawMaterialOrderEntity;


public interface RawMaterialService {
	
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock);

	public boolean doesRawMaterialOrderIdExist(String id) throws RMOrderIDDoesNotExistException;

	public boolean processDateCheck(RawMaterialStock rawMaterialStock) throws ProcessDateException, IncompleteDataException;

	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock);

	public String updateRawMaterialStock(RawMaterialStock rawMaterialStock);
	public String displayRawmaterialOrders(DisplayRawMaterialOrder displayRawMaterialOrderObject) throws DisplayException, BackEndException;

	public boolean doesRawMaterialOrderIdExistInStock(String orderId);

	public String updateStatusRawMaterialOrder(String id, String status);

	public String placeRawMaterialOrder(RawMaterialOrder rawMaterialOrder);

	public ArrayList<String> fetchWarehouseIds();

	public ArrayList<String> fetchSupplierIds();

	public ArrayList<String> fetchRawMaterialNames();

	 public String fetchSupplierDetail(Supplier supplierDetails) throws DisplayException;
}


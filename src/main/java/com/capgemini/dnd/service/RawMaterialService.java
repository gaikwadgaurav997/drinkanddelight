package com.capgemini.dnd.service;


import java.util.Date;

import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.RawMaterialStock;


public interface RawMaterialService {
	
//	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock);
//
//	public boolean doesRawMaterialOrderIdExist(String id) throws RMOrderIDDoesNotExistException;
//	
//	boolean doesRawMaterialOrderIdExistInStock(String orderId);
//
//	public boolean processDateCheck(RawMaterialStock rawMaterialStock) throws ProcessDateException, IncompleteDataException;
//
//	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock);
//
//	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException;
//
//	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException;
//
//	public String updateRawMaterialStock(RawMaterialStock rawMaterialStock);	
}


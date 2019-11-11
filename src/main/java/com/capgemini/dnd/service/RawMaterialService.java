package com.capgemini.dnd.service;


import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.RawMaterialStock;


public interface RawMaterialService {
	
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock);

	public boolean doesRawMaterialOrderIdExist(String id) throws RMOrderIDDoesNotExistException;

	public boolean processDateCheck(RawMaterialStock rawMaterialStock) throws ProcessDateException, IncompleteDataException;

	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock);

	public String updateRawMaterialStock(RawMaterialStock rawMaterialStock);	
}


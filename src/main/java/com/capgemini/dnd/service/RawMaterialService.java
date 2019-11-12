package com.capgemini.dnd.service;


import java.util.List;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.DisplayRawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;
import com.capgemini.dnd.entity.RawMaterialOrderEntity;


public interface RawMaterialService {
	
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock);

	public boolean doesRawMaterialOrderIdExist(String id) throws RMOrderIDDoesNotExistException;

	public boolean processDateCheck(RawMaterialStock rawMaterialStock) throws ProcessDateException, IncompleteDataException;

	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock);

	public String updateRawMaterialStock(RawMaterialStock rawMaterialStock);
	public String displayRawmaterialOrders(DisplayRawMaterialOrder displayRawMaterialOrderObject) throws DisplayException, BackEndException;

	public boolean doesRawMaterialOrderIdExistInStock(String orderId);	
}


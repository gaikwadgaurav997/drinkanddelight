package com.capgemini.dnd.service;

import java.util.Date;

import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.ProductStock;

public interface ProductService {

	public String trackProductOrder(ProductStock productStock);

	public boolean doesProductOrderIdExist(String id) throws ProductOrderIDDoesNotExistException;
	
	boolean doesProductOrderIdExistInStock(String orderId);

	public boolean exitDateCheck(ProductStock productStock) throws ExitDateException, IncompleteDataException;

	public String updateExitDateinStock(ProductStock productStock);

	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException;

	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException;

	public String updateProductStock(ProductStock productStock);
	
}

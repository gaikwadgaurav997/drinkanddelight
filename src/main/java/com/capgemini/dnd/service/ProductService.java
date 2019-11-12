package com.capgemini.dnd.service;

import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.dto.ProductStock;

public interface ProductService {

	public String trackProductOrder(ProductStock productStock);

	public boolean doesProductOrderIdExist(String id) throws ProductOrderIDDoesNotExistException;
	
	public boolean exitDateCheck(ProductStock productStock) throws ExitDateException, IncompleteDataException;

	public String updateExitDateinStock(ProductStock productStock);

	public String updateProductStock(ProductStock productStock);

	public String addProductOrder(ProductOrder productOrder);

	public boolean doesProductOrderIdExistInStock(String orderId);
	
	public String updateStatusProductOrder(String orderId, String deliveryStatus);
	
}

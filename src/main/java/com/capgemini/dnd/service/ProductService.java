package com.capgemini.dnd.service;

import java.util.ArrayList;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.customexceptions.DoesNotExistException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.DisplayProductOrder;
import com.capgemini.dnd.dto.Distributor;
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

	public ArrayList<String> fetchProductNames();

	public ArrayList<String> fetchDistributorIds();

	public ArrayList<String> fetchWarehouseIds();

	

	public String fetchDistributorDetail(Distributor distributor)
			throws BackEndException, DoesNotExistException, DisplayException;
	
	String displayProductOrders(DisplayProductOrder displayProductOrderObject)
			throws DisplayException, BackEndException;
}

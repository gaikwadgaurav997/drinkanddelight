package com.capgemini.dnd.service;

import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dao.ProductStockDAO;
import com.capgemini.dnd.dto.ProductStock;
import com.capgemini.dnd.entity.ProductStockEntity;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.ServiceUtil;

@Service
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	Scanner scanner = new Scanner(System.in);

	@Autowired
	private ProductStockDAO productStockDAO;

	@Override
	public String trackProductOrder(ProductStock productStock) {
//		String message = productDAO.trackProductOrder(productStock);
//		System.out.println("in service");
		int id = Integer.parseInt(productStock.getOrderId());
//		System.out.println(id);
		Optional<ProductStockEntity> productStockEntityObject = productStockDAO.findById(id);
		
		ProductStockEntity productStockEntity = productStockEntityObject.get();
		
		Date exitDate = productStockEntity.getExitDate();

		Date manDate = productStockEntity.getManufacturingDate();

		String warehouseId = productStockEntity.getWarehouseId();

		if (exitDate == null || manDate == null) {
			return JsonUtil.convertJavaToJson(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
		}

		String message = "The order ID had been in the warehouse with warehouseID = " + warehouseId + " from "
				+ manDate.toString() + " to " + exitDate.toString() + "("
				+ ServiceUtil.diffBetweenDays(exitDate, manDate) + " days)";
		
		String jsonMessage = JsonUtil.convertJavaToJson(message);
		
//		System.out.println(message);
		
		return jsonMessage;
	}

//	@Override
//	public boolean doesProductOrderIdExist(String id) throws ProductOrderIDDoesNotExistException {
//		return productDAO.doesProductOrderIdExist(id);
//	}
//
//	@Override
//	public boolean exitDateCheck(ProductStock productStock) throws ExitDateException, IncompleteDataException {
//		return productDAO.exitDateCheck(productStock);
//	}
//
//	@Override
//	public String updateExitDateinStock(ProductStock productStock) {
//		String message = productDAO.updateExitDateinStock(productStock);
//
//		String jsonMessage = JsonUtil.convertJavaToJson(message);
//		return jsonMessage;
//	}
//	
//	@Override
//	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException {
//		Date today = new Date();
//		if (manufacturing_date.before(today)) {
//			return true;
//		}
//		throw new ManufacturingDateException("You cant enter a future manufacturing date");
//	}
//
//	@Override
//	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException {
//		if (expiry_date.after(manufacturing_date))
//			return true;
//		throw new ExpiryDateException("You cant enter expiry date before manufacturing date");
//
//	}
//
//	@Override
//	public String updateProductStock(ProductStock productStock) {
//		String message = productDAO.updateProductStock(productStock);
//		String jsonMessage = JsonUtil.convertJavaToJson(message);
//		return jsonMessage;
//	}
//
//	@Override
//	public boolean doesProductOrderIdExistInStock(String orderId) {
//		return productDAO.doesProductOrderIdExistInStock(orderId);
//	}
}

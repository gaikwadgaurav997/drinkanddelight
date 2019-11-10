package com.capgemini.dnd.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.log4j.Logger;

import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dao.ProductOrdersDAO;
import com.capgemini.dnd.dao.ProductStockDAO;
import com.capgemini.dnd.dto.ProductStock;
import com.capgemini.dnd.entity.ProductOrdersEntity;
import com.capgemini.dnd.entity.ProductStockEntity;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.ServiceUtil;



@Service
public class ProductServiceImpl implements ProductService {

	Logger logger = Logger.getRootLogger();

	@Autowired
	private ProductStockDAO productStockDAO;
	
	@Autowired
	private ProductOrdersDAO productOrderDAO;

	@Override
	public String trackProductOrder(ProductStock productStock) {

		int id = Integer.parseInt(productStock.getOrderId());
		
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
		
		return jsonMessage;
		
	}

	@Override
	public boolean doesProductOrderIdExist(String orderId) throws ProductOrderIDDoesNotExistException {

		boolean productOrderIdFound = false;
		
		try {
		Optional<ProductOrdersEntity> productOrderEntityObject = productOrderDAO.findById(Integer.parseInt(orderId));
		
		if(productOrderEntityObject.isPresent()) {
			productOrderIdFound = true;
			return productOrderIdFound;
		}
		
		else {
			logger.error(Constants.PRODUCT_ID_DOES_NOT_EXISTS_EXCEPTION);
			
			throw new ProductOrderIDDoesNotExistException(Constants.PRODUCT_ID_DOES_NOT_EXISTS_EXCEPTION);
		}
		}
		catch(NumberFormatException exception) {
			logger.error(Constants.INVALID_INPUT_FORMAT);
			throw new ProductOrderIDDoesNotExistException(Constants.INVALID_INPUT_FORMAT);
		}
			
	}

	@Override
	public boolean exitDateCheck(ProductStock productStock) throws ExitDateException, IncompleteDataException {
		
		boolean datecheck = false;
		int id = Integer.parseInt(productStock.getOrderId());
		
		Optional<ProductStockEntity> productStockEntityObject = productStockDAO.findById(id);
		if(productStockEntityObject.isPresent()) {
		
			ProductStockEntity productStockEntity = productStockEntityObject.get();
		
			Date manufacturingDate = productStockEntity.getManufacturingDate();

			Date expiryDate = productStockEntity.getExpiryDate();

			if (productStock.getExitDate().after(manufacturingDate) && productStock.getExitDate().before(expiryDate)) {
				datecheck = true;
				return datecheck;
			}
			
			else {
				logger.error(Constants.EXIT_DATE_EXCEPTION);
				throw new ExitDateException(Constants.EXIT_DATE_EXCEPTION);
			}
		
		}
		
		else {
			logger.error(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
			throw new IncompleteDataException(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
		}
		
		
	}

	@Override
	public String updateExitDateinStock(ProductStock productStock) {
		
		
		int id = Integer.parseInt(productStock.getOrderId());
		
		Optional<ProductStockEntity> productStockEntityObject = productStockDAO.findById(id);
		
		ProductStockEntity productStockEntity = productStockEntityObject.get();
		
		productStockEntity.setExitDate(productStock.getExitDate());
		
		productStockDAO.saveAndFlush(productStockEntity);
		
		String jsonMessage = JsonUtil.convertJavaToJson(Constants.DATA_INSERTED_MESSAGE);
		
		return jsonMessage;
	}
	
	@Override
	public boolean validateManufacturingDate(Date manufacturing_date) throws ManufacturingDateException {
		boolean datecheck = false;
		Date today = new Date();
		if (manufacturing_date.before(today)) {
			datecheck = true;
			return datecheck;
		}
		else {
			logger.error(Constants.INVALID_MANUFACTURING_DATE);
			throw new ManufacturingDateException(Constants.INVALID_MANUFACTURING_DATE);
		}
	}

	@Override
	public boolean validateExpiryDate(Date manufacturing_date, Date expiry_date) throws ExpiryDateException {
		
		boolean datecheck = false;
		
		if (expiry_date.after(manufacturing_date)) {
			datecheck = true;
			return datecheck;
		}
		else {
			logger.error(Constants.INVALID_EXPIRY_DATE);
			throw new ExpiryDateException(Constants.INVALID_EXPIRY_DATE);
		}
		
	}

	
	@Override
	public String updateProductStock(ProductStock productStock) {
		
		
		int id = Integer.parseInt(productStock.getOrderId());
		
		boolean orderIdcheckInStock = false;
		ProductStockEntity productStockEntity = null;

		orderIdcheckInStock = doesProductOrderIdExistInStock(productStock.getOrderId());

		if (orderIdcheckInStock == false) {
			
			Optional<ProductOrdersEntity> productOrderEntityObject = productOrderDAO.findById(id);
			ProductOrdersEntity productOrderEntity = productOrderEntityObject.get();
			System.out.println(productOrderEntity.getOrderId());
			newEntryIntoProductStock(productOrderEntity.getOrderId());
//			productStockEntity.setOrderId(productOrderEntity.getOrderId());
			
			Optional<ProductStockEntity> productStockEntityObject = productStockDAO.findById(id);
			productStockEntity = productStockEntityObject.get();
			
			productStockEntity.setName(productOrderEntity.getName());
			productStockEntity.setPricePerUnit(productOrderEntity.getPricePerUnit());
			productStockEntity.setQuantityValue(productOrderEntity.getQuantityValue());
			productStockEntity.setQuantityUnit(productOrderEntity.getQuantityUnit());
			productStockEntity.setTotalPrice(productOrderEntity.getTotalPrice());
			productStockEntity.setWarehouseId(productOrderEntity.getWarehouseId());
			productStockEntity.setDateofDelivery(productOrderEntity.getDateofDelivery());
						
			productStockEntity.setManufacturingDate(productStock.getManufacturingDate());
			productStockEntity.setExpiryDate(productStock.getExpiryDate());
			productStockEntity.setQualityCheck(productStock.getQualityCheck());

			productStockDAO.saveAndFlush(productStockEntity);
			String jsonMessage = JsonUtil.convertJavaToJson(Constants.DATA_INSERTED_MESSAGE);
			return jsonMessage;
		}
		
		else {

		Optional<ProductStockEntity> productStockEntityObject = productStockDAO.findById(id);
		productStockEntity = productStockEntityObject.get();
		
		productStockEntity.setManufacturingDate(productStock.getManufacturingDate());
		productStockEntity.setExpiryDate(productStock.getExpiryDate());
		productStockEntity.setQualityCheck(productStock.getQualityCheck());

		productStockDAO.saveAndFlush(productStockEntity);
				
		String jsonMessage = JsonUtil.convertJavaToJson(Constants.DATA_INSERTED_MESSAGE);
		return jsonMessage;
		
		}
	}

	@SuppressWarnings("null")
	private void newEntryIntoProductStock(int orderId) {
		
		ProductStockEntity productStockEntity = null;
		productStockEntity.setOrderId(orderId);
		productStockDAO.saveAndFlush(productStockEntity);
		
		
	}

	@Override
	public boolean doesProductOrderIdExistInStock(String orderId) {
		
		boolean productOrderIdFoundInStock = false;
		
		try {
			Optional<ProductStockEntity> productStockEntityObject = productStockDAO.findById(Integer.parseInt(orderId));
			
			if(productStockEntityObject.isPresent()) {
				productOrderIdFoundInStock = true;
				return productOrderIdFoundInStock;
			}
			
			else {
				logger.error(Constants.PRODUCT_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
				return productOrderIdFoundInStock;
			}
		}
		
		catch(NumberFormatException exception) {
				logger.error(Constants.INVALID_INPUT_FORMAT);
				return productOrderIdFoundInStock;
			}
			
	}
}

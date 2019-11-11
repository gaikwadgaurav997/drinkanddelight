package com.capgemini.dnd.service;

import java.util.Date;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dao.RawMaterialOrdersDAO;
import com.capgemini.dnd.dao.RawMaterialStockDAO;
import com.capgemini.dnd.dto.RawMaterialStock;
import com.capgemini.dnd.entity.RawMaterialOrderEntity;
import com.capgemini.dnd.entity.RawMaterialStockEntity;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.ServiceUtil;


@Service
public class RawMaterialServiceImpl implements RawMaterialService {


	Logger logger = Logger.getRootLogger();

	@Autowired
	private RawMaterialStockDAO rawMaterialStockDAO;
	
	@Autowired
	private RawMaterialOrdersDAO rawMaterialOrderDAO;
	
	@Autowired
	RawMaterialStockEntity rawMaterialStockEntity;
	
	@Autowired
	RawMaterialOrderEntity rawMaterialOrderEntity;

	@Override
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock) {
		
		int id = Integer.parseInt(rawMaterialStock.getOrderId());
		
		Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO.findById(id);
		
		RawMaterialStockEntity rawMaterialStockEntity = rawMaterialStockEntityObject.get();
		
		Date processDate = rawMaterialStockEntity.getProcessDate();
	      
			Date deliveryDate = rawMaterialStockEntity.getDateofDelivery();

			String warehouseId = rawMaterialStockEntity.getWarehouseId();

		if(processDate == null || deliveryDate == null) {
			return JsonUtil.convertJavaToJson(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
		}

		String message = "The order ID had been in the warehouse with warehouseID = " + warehouseId + " from "
				+ deliveryDate.toString() + " to " + processDate.toString() + "("
				+ ServiceUtil.diffBetweenDays(processDate, deliveryDate) + " days)";
			
		String jsonMessage = JsonUtil.convertJavaToJson(message);
		
		return jsonMessage;
	}

	@Override
	public boolean doesRawMaterialOrderIdExist(String orderId) throws RMOrderIDDoesNotExistException {
		
		boolean rawMaterialOrderIdFound = false;
		
		try {
		Optional<RawMaterialOrderEntity> rawMaterialOrderEntityObject = rawMaterialOrderDAO.findById(Integer.parseInt(orderId));
		
		if(rawMaterialOrderEntityObject.isPresent()) {
			rawMaterialOrderIdFound = true;
			return rawMaterialOrderIdFound;
		}
		
		else {
			logger.error(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
			throw new RMOrderIDDoesNotExistException(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
		}
		}
		catch(NumberFormatException exception) {
			logger.error(Constants.INVALID_INPUT_FORMAT);
			throw new RMOrderIDDoesNotExistException(Constants.INVALID_INPUT_FORMAT);
			
		}
			

	}

	@Override
	public boolean processDateCheck(RawMaterialStock rawMaterialStock)
			throws ProcessDateException, IncompleteDataException {


		boolean datecheck = false;
		int id = Integer.parseInt(rawMaterialStock.getOrderId());
		
		Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO.findById(id);
		
		if(rawMaterialStockEntityObject.isPresent()) {
			RawMaterialStockEntity rawMaterialStockEntity = rawMaterialStockEntityObject.get();
			
			try {
			
			Date manufacturingDate = rawMaterialStockEntity.getManufacturingDate();
		      
		    Date expiryDate = rawMaterialStockEntity.getExpiryDate();
		      
		      if (rawMaterialStock.getProcessDate().after(manufacturingDate) && rawMaterialStock.getProcessDate().before(expiryDate)) {
		      		datecheck = true;
		      		return datecheck;
		      }
		      
		      else {
		      		logger.error(Constants.PROCESS_DATE_EXCEPTION_MESSAGE);
		      		throw new ProcessDateException(Constants.PROCESS_DATE_EXCEPTION_MESSAGE);
		      }
			}
			catch(NullPointerException exception) {
				logger.error(Constants.INCOMPLETE_INFORMATION_UPDATE_DATABASE);
				throw new IncompleteDataException(Constants.INCOMPLETE_INFORMATION_UPDATE_DATABASE);
			}
		}
		else {
			logger.error(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
			throw new IncompleteDataException(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
		}

	}

	@Override
	public String updateProcessDateinStock(RawMaterialStock rawMaterialStock) {


		int id = Integer.parseInt(rawMaterialStock.getOrderId());
		
		Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO.findById(id);
		
		RawMaterialStockEntity rawMaterialStockEntity = rawMaterialStockEntityObject.get();
		
		rawMaterialStockEntity.setProcessDate(rawMaterialStock.getProcessDate());

		rawMaterialStockDAO.saveAndFlush(rawMaterialStockEntity);
		
		String jsonMessage = JsonUtil.convertJavaToJson(Constants.DATA_INSERTED_MESSAGE);
		
		return jsonMessage;
		

	}

	@Override
	public String updateRawMaterialStock(RawMaterialStock rawMaterialStock) {

		
		int id = Integer.parseInt(rawMaterialStock.getOrderId());
		
		boolean orderIdcheckInStock = false;

		orderIdcheckInStock = doesRawMaterialOrderIdExistInStock(rawMaterialStock.getOrderId());

		if (orderIdcheckInStock == false) {
			
			Optional<RawMaterialOrderEntity> rawMaterialOrderEntityObject = rawMaterialOrderDAO.findById(id);
	        RawMaterialOrderEntity rawMaterialOrderEntity = rawMaterialOrderEntityObject.get();
			
		     	rawMaterialStockEntity.setOrderId(rawMaterialOrderEntity.getOrderId());
	        	rawMaterialStockEntity.setName(rawMaterialOrderEntity.getName());
		        rawMaterialStockEntity.setPricePerUnit(rawMaterialOrderEntity.getPricePerUnit());
		        rawMaterialStockEntity.setQuantityValue(rawMaterialOrderEntity.getQuantityValue());
		        rawMaterialStockEntity.setQuantityUnit(rawMaterialOrderEntity.getQuantityUnit());
		        rawMaterialStockEntity.setTotalPrice(rawMaterialOrderEntity.getTotalPrice());
		        rawMaterialStockEntity.setWarehouseId(rawMaterialOrderEntity.getWarehouseId());
		        rawMaterialStockEntity.setDateofDelivery(rawMaterialOrderEntity.getDateOfDelivery());
		        
		        rawMaterialStockEntity.setManufacturingDate(rawMaterialStock.getManufacturingDate());
				rawMaterialStockEntity.setExpiryDate(rawMaterialStock.getExpiryDate());
				rawMaterialStockEntity.setQualityCheck(rawMaterialStock.getQualityCheck());
		        
		        rawMaterialStockDAO.saveAndFlush(rawMaterialStockEntity);
                
		        String jsonMessage = JsonUtil.convertJavaToJson(Constants.DATA_INSERTED_MESSAGE);
		        return jsonMessage;
			
		}
		
		else {
		Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO.findById(id);
		
		RawMaterialStockEntity rawMaterialStockEntity = rawMaterialStockEntityObject.get();
		
		rawMaterialStockEntity.setManufacturingDate(rawMaterialStock.getManufacturingDate());
		rawMaterialStockEntity.setExpiryDate(rawMaterialStock.getExpiryDate());
		rawMaterialStockEntity.setQualityCheck(rawMaterialStock.getQualityCheck());
		
		rawMaterialStockDAO.saveAndFlush(rawMaterialStockEntity);
		
		String jsonMessage = JsonUtil.convertJavaToJson(Constants.DATA_INSERTED_MESSAGE);
		return jsonMessage;
		}

	}
	
    @Override
	public boolean doesRawMaterialOrderIdExistInStock(String orderId) {

		boolean rawMaterialOrderIdFound = false;
		try {
	        Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO.findById(Integer.parseInt(orderId));
	        
	        if(rawMaterialStockEntityObject.isPresent()) {
	            rawMaterialOrderIdFound = true;
	            return rawMaterialOrderIdFound;
	        }
	        
	        else {
	            logger.error(Constants.RAWMATERIAL_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
	            return rawMaterialOrderIdFound;
	            
	        }
	        }
	        catch(NumberFormatException exception) {
	        	logger.error(Constants.INVALID_INPUT_FORMAT);
	        	return rawMaterialOrderIdFound;
	        }
		
	}
	
}

package com.capgemini.dnd.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.UpdateException;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dao.RawMaterialOrdersDAO;
import com.capgemini.dnd.dao.RawMaterialSpecsDAO;
import com.capgemini.dnd.dao.RawMaterialStockDAO;
import com.capgemini.dnd.dao.SupplierDAO;
import com.capgemini.dnd.dao.WarehouseDAO;
import com.capgemini.dnd.dto.DisplayRawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;
import com.capgemini.dnd.dto.Supplier;
import com.capgemini.dnd.entity.RawMaterialOrderEntity;
import com.capgemini.dnd.entity.RawMaterialSpecsEntity;
import com.capgemini.dnd.entity.RawMaterialStockEntity;
import com.capgemini.dnd.entity.SupplierEntity;
import com.capgemini.dnd.entity.WarehouseEntity;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.ServiceUtil;

@Service
public class RawMaterialServiceImpl implements RawMaterialService {

	Logger logger = Logger.getRootLogger();

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	private RawMaterialStockDAO rawMaterialStockDAO;

	@Autowired
	private RawMaterialOrdersDAO rawMaterialOrderDAO;

	@Autowired
	private RawMaterialSpecsDAO rawMaterialSpecsDAO;

	@Autowired
	private SupplierDAO supplierDAO;

	@Autowired
	private WarehouseDAO warehouseDAO;

	@Autowired
	RawMaterialOrderEntity rawMaterialOrdersEntity;

	@Autowired
	RawMaterialStockEntity rawMaterialStockEntity;

	@Autowired
	RawMaterialSpecsEntity rawMaterialSpecsEntity;

	@Autowired
	SupplierEntity supplierEntity;

	@Autowired
	WarehouseEntity warehouseEntity;

	
	/*******************************************************************************************************
	 - Function Name	:	trackRawMaterialOrder
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	String
	 - Throws			:  	No exception
	 - Author			:	Gaurav Gaikwad, Capgemini
	 - Creation Date	:	05/11/2019
	 - Description		:	Track a particular order and calculate its shelf life 
	 ********************************************************************************************************/
	@Override
	public String trackRawMaterialOrder(RawMaterialStock rawMaterialStock) {

		int id = Integer.parseInt(rawMaterialStock.getOrderId());

		Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO.findById(id);

		RawMaterialStockEntity rawMaterialStockEntity = rawMaterialStockEntityObject.get();

		Date processDate = rawMaterialStockEntity.getProcessDate();

		Date deliveryDate = rawMaterialStockEntity.getDateofDelivery();

		String warehouseId = rawMaterialStockEntity.getWarehouseId();

		if (processDate == null || deliveryDate == null) {
			return JsonUtil.convertJavaToJson(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
		}

		String message = "The order ID had been in the warehouse with warehouseID = " + warehouseId + " from "
				+ deliveryDate.toString() + " to " + processDate.toString() + "("
				+ ServiceUtil.diffBetweenDays(processDate, deliveryDate) + " days)";

		String jsonMessage = JsonUtil.convertJavaToJson(message);

		return jsonMessage;
	}

	
	/*******************************************************************************************************
	 - Function Name	:	doesRawMaterialOrderIdExist
	 - Input Parameters	:	String orderId
	 - Return Type		:	boolean
	 - Throws			:  	RMOrderIDDoesNotExistException
	 - Author			:	Gaurav Gaikwad, Capgemini
	 - Creation Date	:	05/11/2019
	 - Description		:	Checks if the Order ID exists in the Orders Table
	 ********************************************************************************************************/
	@Override
	public boolean doesRawMaterialOrderIdExist(String orderId) throws RMOrderIDDoesNotExistException {

		boolean rawMaterialOrderIdFound = false;

		try {
			Optional<RawMaterialOrderEntity> rawMaterialOrderEntityObject = rawMaterialOrderDAO
					.findById(Integer.parseInt(orderId));

			if (rawMaterialOrderEntityObject.isPresent()) {
				rawMaterialOrderIdFound = true;
				return rawMaterialOrderIdFound;
			}

			else {
				logger.error(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
				throw new RMOrderIDDoesNotExistException(Constants.RAWMATERIAL_ID_DOES_NOT_EXISTS_EXCEPTION);
			}
		} catch (NumberFormatException exception) {
			logger.error(Constants.INVALID_INPUT_FORMAT);
			throw new RMOrderIDDoesNotExistException(Constants.INVALID_INPUT_FORMAT);

		}
	}

	/*******************************************************************************************************
	 - Function Name	:	doesRawMaterialOrderExistInStock
	 - Input Parameters	:	orderId
	 - Return Type		:	boolean
	 - Throws			:  	No exception
	 - Author			:	Gaurav Gaikwad, Capgemini
	 - Creation Date	:	05/11/2019
	 - Description		:	Checks if Raw Material Order ID exists in Stock
	 ********************************************************************************************************/
	@Override
	public boolean doesRawMaterialOrderIdExistInStock(String orderId) {

		boolean rawMaterialOrderIdFound = false;

		try {
			Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO
					.findById(Integer.parseInt(orderId));

			if (rawMaterialStockEntityObject.isPresent()) {
				rawMaterialOrderIdFound = true;
				return rawMaterialOrderIdFound;
			}

			else {
				logger.error(Constants.RAWMATERIAL_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
				return rawMaterialOrderIdFound;

			}
		} catch (NumberFormatException exception) {
			logger.error(Constants.INVALID_INPUT_FORMAT);
			return rawMaterialOrderIdFound;
		}

	}

	
	/*******************************************************************************************************
	 - Function Name	:	processDateCheck
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	boolean
	 - Throws			:  	ProcessDateException, IncompleteDataException
	 - Author			:	Gaurav Gaikwad, Capgemini
	 - Creation Date	:	05/11/2019
	 - Description		:	Checks if the process date entered is valid or not
	 ********************************************************************************************************/
	@Override
	public boolean processDateCheck(RawMaterialStock rawMaterialStock)
			throws ProcessDateException, IncompleteDataException {

		boolean datecheck = false;
		int id = Integer.parseInt(rawMaterialStock.getOrderId());

		Optional<RawMaterialStockEntity> rawMaterialStockEntityObject = rawMaterialStockDAO.findById(id);

		if (rawMaterialStockEntityObject.isPresent()) {
			RawMaterialStockEntity rawMaterialStockEntity = rawMaterialStockEntityObject.get();

			try {

				Date manufacturingDate = rawMaterialStockEntity.getManufacturingDate();

				Date expiryDate = rawMaterialStockEntity.getExpiryDate();

				if (rawMaterialStock.getProcessDate().after(manufacturingDate)
						&& rawMaterialStock.getProcessDate().before(expiryDate)) {
					datecheck = true;
					return datecheck;
				}

				else {
					logger.error(Constants.PROCESS_DATE_EXCEPTION_MESSAGE);
					throw new ProcessDateException(Constants.PROCESS_DATE_EXCEPTION_MESSAGE);
				}
			} catch (NullPointerException exception) {
				logger.error(Constants.INCOMPLETE_INFORMATION_UPDATE_DATABASE);
				throw new IncompleteDataException(Constants.INCOMPLETE_INFORMATION_UPDATE_DATABASE);
			}
		} else {
			logger.error(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
			throw new IncompleteDataException(Constants.INCOMPLETE_INFORMATION_IN_DATABASE);
		}

	}

	
	/*******************************************************************************************************
	 - Function Name	:	updateProcessDateInStock
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	String
	 - Throws			:  	No exception
	 - Author			:	Gaurav Gaikwad, Capgemini
	 - Creation Date	:	05/11/2019
	 - Description		:	Updates Details of Process Date in Database 
	 ********************************************************************************************************/
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
	public String displayRawmaterialOrders(DisplayRawMaterialOrder displayRawMaterialOrderObject)
			throws DisplayException, BackEndException {

		Session session = null;
//		Criteria cr = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<RawMaterialOrderEntity> list = new ArrayList<RawMaterialOrderEntity>();
		String jsonMessage;

		try {

			session = sessionFactory.openSession();
			session.beginTransaction();

			String deliveryStatus = displayRawMaterialOrderObject.getDeliveryStatus();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<RawMaterialOrderEntity> criteria = builder.createQuery(RawMaterialOrderEntity.class);
			Root<RawMaterialOrderEntity> root = criteria.from(RawMaterialOrderEntity.class);

			if (deliveryStatus.equals("ALL")) {

				;
			} else {

				criteria.select(root).where(builder.equal(root.get("deliveryStatus"), deliveryStatus));

			}
			String supplierId = displayRawMaterialOrderObject.getSupplierid();

			if (supplierId.equals("ALL"))
				;
			else
				criteria.select(root).where(builder.equal(root.get("supplierId"), supplierId));

			String startDate = displayRawMaterialOrderObject.getStartdate();
			String endDate = displayRawMaterialOrderObject.getEndDate();

			if (startDate != null && endDate != null) {
				criteria.select(root)
						.where(builder.between(root.get("dateOfDelivery"), sdf.parse(startDate), sdf.parse(endDate)));

			}

			Query<RawMaterialOrderEntity> q = session.createQuery(criteria);
			list = q.list();
			if (list.isEmpty()) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_NO_RECORDS_FOUND);
			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}
		} catch (Exception e) {

			throw new DisplayException(Constants.DISPLAY_EXCEPTION_NO_RECORDS_FOUND);
		}

		finally {

			session.close();
		}
		jsonMessage = JsonUtil.convertJavaToJson1(list);
		return jsonMessage;

	}

	
	/*******************************************************************************************************
	 - Function Name	:	updateRawMaterialStock
	 - Input Parameters	:	RawMaterialStock rawMaterialStock
	 - Return Type		:	String
	 - Throws			:  	No exception
	 - Author			:	Gaurav Gaikwad, Capgemini
	 - Creation Date	:	05/11/2019
	 - Description		:	Updates Details of Stock in Database 
	 ********************************************************************************************************/
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

	public String updateStatusRawMaterialOrder(String orderId, String deliveryStatus) {
		try {
			RawMaterialOrderEntity rawMaterialOrdersEntity = rawMaterialOrderDAO.findById(Integer.parseInt(orderId))
					.orElse(new RawMaterialOrderEntity());
			rawMaterialOrdersEntity.setDeliveryStatus(deliveryStatus);
			rawMaterialOrderDAO.saveAndFlush(rawMaterialOrdersEntity);
			logger.info(Constants.UPADTED_SUCCESSFULLY_MESSAGE);
			return JsonUtil.convertJavaToJson(Constants.UPADTED_SUCCESSFULLY_MESSAGE);
		} catch (Exception e) {

			logger.error(e);

			try {
				throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_FAILURE_DELIVERY);
			} catch (UpdateException ex) {
				return ex.getMessage();
			}
		}

	}


	@Override
	public String placeRawMaterialOrder(RawMaterialOrder rawMaterialOrder) {

		rawMaterialOrdersEntity.setName(rawMaterialOrder.getName());
		rawMaterialOrdersEntity.setSupplierId(rawMaterialOrder.getSupplierId());
		rawMaterialOrdersEntity.setQuantityValue(rawMaterialOrder.getQuantityValue());
		rawMaterialOrdersEntity.setQuantityUnit(rawMaterialOrder.getQuantityUnit());
		rawMaterialOrdersEntity.setDateOfOrder(rawMaterialOrder.getDateOfOrder());
		rawMaterialOrdersEntity.setDateOfDelivery(rawMaterialOrder.getDateOfDelivery());
		rawMaterialOrdersEntity.setPricePerUnit(rawMaterialOrder.getPricePerUnit());
		rawMaterialOrdersEntity.setTotalPrice(rawMaterialOrder.getQuantityValue() * rawMaterialOrder.getPricePerUnit());
		rawMaterialOrdersEntity.setTotalPrice(rawMaterialOrder.getTotalPrice());
		rawMaterialOrdersEntity.setDeliveryStatus(rawMaterialOrder.getDeliveryStatus());
		rawMaterialOrdersEntity.setWarehouseId(rawMaterialOrder.getWarehouseId());

		rawMaterialOrderDAO.saveAndFlush(rawMaterialOrdersEntity);
		String jsonMessage = JsonUtil.convertJavaToJson(Constants.RM_ORDER_ADDED);
		logger.info(jsonMessage);
		return jsonMessage;
	}

	@Override
	public ArrayList<String> fetchRawMaterialNames() {
		ArrayList<String> rawMaterialNamesList = new ArrayList<String>();
		List<RawMaterialSpecsEntity> rawMaterialSpecsEntityObject = rawMaterialSpecsDAO.findAll();

		for (RawMaterialSpecsEntity rawMaterialSpecsEntity : rawMaterialSpecsEntityObject) {
			rawMaterialNamesList.add(rawMaterialSpecsEntity.getName());
		}

		if(rawMaterialSpecsEntityObject.size()>0)
            logger.info(Constants.RM_NAMES_FETCHED);
        else {
            logger.error(Constants.RM_NAMES_NOT_FETCHED);
        }
		return rawMaterialNamesList;
	}

	@Override
	public ArrayList<String> fetchSupplierIds() {
		ArrayList<String> supplierNamesList = new ArrayList<String>();
		List<SupplierEntity> supplierEntityObject = supplierDAO.findAll();

		for (SupplierEntity supplierEntity : supplierEntityObject) {
			supplierNamesList.add(supplierEntity.getSupplierId());
		}

		if(supplierEntityObject.size()>0)
            logger.info(Constants.SUPPLIER_IDS_FETCHED);
        else {
            logger.error(Constants.SUPPLIER_IDS_NOT_FETCHED);
        }
		return supplierNamesList;
	}

	@Override
	public ArrayList<String> fetchWarehouseIds() {
		ArrayList<String> warehouseIdsList = new ArrayList<String>();
		List<WarehouseEntity> warehouseEntityObject = warehouseDAO.findAll();

		for (WarehouseEntity warehouseEntity : warehouseEntityObject) {
			warehouseIdsList.add(warehouseEntity.getWarehouseId());
		}

		if(warehouseEntityObject.size()>0)
            logger.info(Constants.WID_NAMES_FETCHED);
        else {
            logger.error(Constants.WID_NAMES_NOT_FETCHED);
        }
		return warehouseIdsList;
	}

	@Override
	public String fetchSupplierDetail(Supplier supplierDetails) throws DisplayException {
		Session session = null;
		List<SupplierEntity> supplierlist = new ArrayList<SupplierEntity>();
		String jsonMessage = "";
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();

			String supplierId = supplierDetails.getSupplierId();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<SupplierEntity> criteria = builder.createQuery(SupplierEntity.class);
			Root<SupplierEntity> root = criteria.from(SupplierEntity.class);

			criteria.select(root).where(builder.equal(root.get("supplierId"), supplierId));

			Query<SupplierEntity> query = session.createQuery(criteria);
			supplierlist = query.list();

			if (supplierlist.isEmpty()) {
				logger.error(Constants.LOGGER_ERROR_FETCH_FAILED);
				throw new DisplayException(Constants.DISPLAY_EXCEPTION_NO_RECORDS_FOUND);

			} else {
				logger.info(Constants.LOGGER_INFO_DISPLAY_SUCCESSFUL);

			}
		} catch (Exception e) {

			e.printStackTrace();
			throw new DisplayException(Constants.DISPLAY_EXCEPTION_NO_RECORDS_FOUND);
		}

		finally {

			session.close();
		}
		jsonMessage = JsonUtil.convertJavaToJson1(supplierlist);
		return jsonMessage;

	}
}

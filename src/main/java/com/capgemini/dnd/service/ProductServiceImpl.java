package com.capgemini.dnd.service;

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
import com.capgemini.dnd.customexceptions.DoesNotExistException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.customexceptions.UpdateException;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dao.DistributorDAO;
import com.capgemini.dnd.dao.ProductOrdersDAO;
import com.capgemini.dnd.dao.ProductStockDAO;
import com.capgemini.dnd.dao.WarehouseDAO;
import com.capgemini.dnd.dao.ProductSpecsDAO;
import com.capgemini.dnd.dto.Distributor;
import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.dto.ProductStock;
import com.capgemini.dnd.entity.DistributorEntity;
import com.capgemini.dnd.entity.ProductOrdersEntity;
import com.capgemini.dnd.entity.ProductSpecsEntity;
import com.capgemini.dnd.entity.ProductStockEntity;
import com.capgemini.dnd.entity.WarehouseEntity;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.ServiceUtil;

 

 

@Service
public class ProductServiceImpl implements ProductService {

 

    Logger logger = Logger.getRootLogger();

    @Autowired
    SessionFactory sessionFactory;

    @Autowired
    private ProductStockDAO productStockDAO;
    
    @Autowired
    private ProductOrdersDAO productOrderDAO;
    
    @Autowired
    private ProductSpecsDAO productSpecsDAO;
    
    @Autowired
    private DistributorDAO distributorDAO;
    
    @Autowired
    private WarehouseDAO warehouseDAO;
    
    @Autowired
    ProductOrdersEntity productOrdersEntity;
    
    @Autowired
    ProductStockEntity productStockEntity;
    
    @Autowired
    ProductSpecsEntity productSpecsEntity;
    
    @Autowired
    DistributorEntity distributorEntity;
    
    @Autowired
    WarehouseEntity warehouseEntity;
    
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
        
            try {
            
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
    public String updateProductStock(ProductStock productStock) {
        
        
        int id = Integer.parseInt(productStock.getOrderId());
        
        boolean orderIdcheckInStock = false;

		orderIdcheckInStock = doesProductOrderIdExistInStock(productStock.getOrderId());

		if (orderIdcheckInStock == false) {
			
			Optional<ProductOrdersEntity> productOrderEntityObject = productOrderDAO.findById(id);
	        ProductOrdersEntity productOrderEntity = productOrderEntityObject.get();
			
		     	productStockEntity.setOrderId(productOrderEntity.getOrderId());
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
        ProductStockEntity productStockEntity = productStockEntityObject.get();
        
        productStockEntity.setManufacturingDate(productStock.getManufacturingDate());
        productStockEntity.setExpiryDate(productStock.getExpiryDate());
        productStockEntity.setQualityCheck(productStock.getQualityCheck());

 

        productStockDAO.saveAndFlush(productStockEntity);
                
        String jsonMessage = JsonUtil.convertJavaToJson(Constants.DATA_INSERTED_MESSAGE);
        return jsonMessage;
        
        }
        
    }
    
    @Override
	public boolean doesProductOrderIdExistInStock(String orderId) {

		boolean productOrderIdFound = false;
		try {
	        Optional<ProductStockEntity> productStockEntityObject = productStockDAO.findById(Integer.parseInt(orderId));
	        
	        if(productStockEntityObject.isPresent()) {
	            productOrderIdFound = true;
	            return productOrderIdFound;
	        }
	        
	        else {
	            logger.error(Constants.PRODUCT_ID_DOES_NOT_EXIST_IN_STOCK_EXCEPTION);
	            return productOrderIdFound;
	            
	        }
	        }
	        catch(NumberFormatException exception) {
	        	logger.error(Constants.INVALID_INPUT_FORMAT);
	        	return productOrderIdFound;
	        }
		
	}

 
    @Override
    public String addProductOrder(ProductOrder productOrder) {

    productOrdersEntity.setName(productOrder.getName());
    productOrdersEntity.setDistributorId(productOrder.getDistributorId());
    productOrdersEntity.setQuantityValue(productOrder.getQuantityValue());
    productOrdersEntity.setQuantityUnit(productOrder.getQuantityUnit());
    productOrdersEntity.setDateOfOrder(productOrder.getDateOfOrder());
    productOrdersEntity.setDateofDelivery(productOrder.getDateofDelivery());
    productOrdersEntity.setPricePerUnit(productOrder.getPricePerUnit());
    productOrdersEntity.setTotalPrice(productOrder.getQuantityValue()*productOrder.getPricePerUnit());
    productOrdersEntity.setTotalPrice(productOrder.getTotalPrice());
    productOrdersEntity.setDeliveryStatus(productOrder.getDeliveryStatus());
    productOrdersEntity.setWarehouseId(productOrder.getWarehouseId());

    productOrderDAO.saveAndFlush(productOrdersEntity);
    
    String jsonMessage = JsonUtil.convertJavaToJson(Constants.PRODUCT_ORDER_ADDED);
    return jsonMessage;
    
    }
    
    @Override
    public String updateStatusProductOrder(String orderId, String deliveryStatus) {
        
        try {
            ProductOrdersEntity productOrdersEntity = productOrderDAO.findById(Integer.parseInt(orderId)).orElse(new ProductOrdersEntity());
            productOrdersEntity.setDeliveryStatus(deliveryStatus);
            productOrderDAO.saveAndFlush(productOrdersEntity);
            logger.info(Constants.UPADTED_SUCCESSFULLY_MESSAGE);
            return JsonUtil.convertJavaToJson(Constants.UPADTED_SUCCESSFULLY_MESSAGE);
        } catch (Exception e) {
            
                logger.error(e);
                
            }
            try {
                throw new UpdateException(Constants.UPDATE_EXCEPTION_MESSAGE_FAILURE_DELIVERY);
            } catch (UpdateException ex) {
                return ex.getMessage();
            }
        }




	@Override
	public ArrayList<String> fetchProductNames() {
		ArrayList<String> productNamesList = new ArrayList<String>();
		List<ProductSpecsEntity> productSpecsEntityObject = productSpecsDAO.findAll();
		
		for (ProductSpecsEntity productSpecsEntity : productSpecsEntityObject) {
			productNamesList.add(productSpecsEntity.getName());
		}
		
		return productNamesList;
	}
	
	@Override
	public ArrayList<String> fetchDistributorIds() {
		ArrayList<String> distributorNamesList = new ArrayList<String>();
		List<DistributorEntity> distributorEntityObject = distributorDAO.findAll();
		
		for (DistributorEntity distributorEntity : distributorEntityObject) {
			distributorNamesList.add(distributorEntity.getDistributorId());
		}
		
		return distributorNamesList;
	}

	@Override
	public ArrayList<String> fetchWarehouseIds() {
		ArrayList<String> warehouseIdsList = new ArrayList<String>();
		List<WarehouseEntity> warehouseEntityObject = warehouseDAO.findAll();
		
		for (WarehouseEntity warehouseEntity : warehouseEntityObject) {
			warehouseIdsList.add(warehouseEntity.getWarehouseId());
		}
		
		return warehouseIdsList;
	}



	@Override
	
		public String fetchDistributorDetail(Distributor distributor) throws DisplayException
				{
			
		    Session session = null;
			List<DistributorEntity> distributorlist = new ArrayList<DistributorEntity>();
			String jsonMessage= "";
			
			try {
				session = sessionFactory.openSession();
				String distributorId = distributor.getDistributorId();
				CriteriaBuilder builder = session.getCriteriaBuilder();
				CriteriaQuery<DistributorEntity> criteria = builder.createQuery(DistributorEntity.class);
				Root<DistributorEntity> root = criteria.from(DistributorEntity.class);

				criteria.select(root).where(builder.equal(root.get("distributorId"), distributorId));

				Query<DistributorEntity> query = session.createQuery(criteria);
				distributorlist = query.list();
				if (distributorlist.isEmpty()) {
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
			jsonMessage = JsonUtil.convertJavaToJson1(distributorlist);
			return jsonMessage ;
		}
}
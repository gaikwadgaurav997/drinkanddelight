package com.capgemini.dnd.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.ExpiryDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ManufacturingDateException;
import com.capgemini.dnd.dto.ProductStock;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.service.ProductService;


@RestController
public class ProductStockController {
	
	@Autowired
	private ProductService productService;
	

	
	@CrossOrigin(origins = "*")
	@GetMapping("/ProductStock/TrackProduct/{id}")
	public String trackProductOrder(@PathVariable("id") String id) {
		
		try {
			productService.doesProductOrderIdExist(id);
			String message = productService.trackProductOrder(new ProductStock(id));
			

			return message;
		}
		
		catch (ProductOrderIDDoesNotExistException exception) {

				String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
				return errorJsonMessage;
			}
		
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/ProductStock/UpdateExitDate/{id}")
	public String updateExitDate(@PathVariable("id") String id,  @RequestBody Map<String, Object> paramMap) {
		
		String errorMessage = "";
		Date exitDate = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = (String) paramMap.get("ExitDate");

		try {
			productService.doesProductOrderIdExist(id);
			try {
				exitDate = sdf.parse(date);
				
			} catch (ParseException exception) {
				errorMessage = exception.getMessage();
			}
		} catch (ProductOrderIDDoesNotExistException exception) {
			errorMessage = exception.getMessage();
		}

		try {
			if (errorMessage.isEmpty()) {
				if (productService.exitDateCheck(new ProductStock(id, exitDate))) {
					String exitDateJsonMessage = productService.updateExitDateinStock(new ProductStock(id, exitDate));

					return exitDateJsonMessage;

				}
			} else {
				String errorJsonMessage = JsonUtil.convertJavaToJson(errorMessage);
				return errorJsonMessage;
			}
		} catch (ExitDateException | IncompleteDataException exception) {
			errorMessage += exception.getMessage();
			String errorJsonMessage = JsonUtil.convertJavaToJson(errorMessage);
			return errorJsonMessage;		
			}
		return errorMessage;
	}
	
	@CrossOrigin(origins = "*")
	@PutMapping("/ProductStock/UpdateProductStockDetails/{id}")
	public String updateProductStockDetails(@PathVariable("id") String id,  @RequestBody Map<String, Object> paramMap) {
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String message = null;

		Date manufacturingDate = null;
		Date expiryDate = null;
		
		String manDate  = (String) paramMap.get("ManufacturingDate");
		String expDate  = (String) paramMap.get("ExpiryDate");
		String qaStatus = (String) paramMap.get("QAStatus");

		try {
			if (productService.doesProductOrderIdExist(id)) {
				try {
					manufacturingDate = sdf.parse(manDate);
					if (productService.validateManufacturingDate(manufacturingDate)) {
						try {
							expiryDate = sdf.parse(expDate);
							if (productService.validateExpiryDate(manufacturingDate, expiryDate)) {
								
								message = productService.updateProductStock(
										new ProductStock(id, manufacturingDate, expiryDate, qaStatus));
								return message;

							}
						} catch (ParseException | ExpiryDateException exception) {

							String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
							return errorJsonMessage;

						}
					}
				} catch (ParseException | ManufacturingDateException exception) {

					String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
					return errorJsonMessage;

				}
			}
		} catch (ProductOrderIDDoesNotExistException exception) {

			String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
			return errorJsonMessage;

		}
		return message;

	}

		
		
	}
	

		

	



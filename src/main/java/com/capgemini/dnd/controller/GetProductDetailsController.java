package com.capgemini.dnd.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.dnd.customexceptions.ConnectionException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.service.ProductService;
import com.capgemini.dnd.util.JsonUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/GetProductDetails")
public class GetProductDetailsController {

	@Autowired
	private ProductService productService;

	@RequestMapping(method = RequestMethod.GET)
	public String trackProductOrder(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {

		ArrayList<ArrayList<String>> productDetailsList = new ArrayList<>();

//		try {
			productDetailsList.add(productService.fetchProductNames());
			productDetailsList.add(productService.fetchDistributorIds());
			productDetailsList.add(productService.fetchWarehouseIds());

			return(JsonUtil.convertJavaToJson1(productDetailsList));

//		} catch (DisplayException | ConnectionException exception) {
//			String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
//			return(errorJsonMessage);
//		}
	}
}
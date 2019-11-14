package com.capgemini.dnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.dto.DisplayRawMaterialOrder;
import com.capgemini.dnd.service.RawMaterialService;
import com.capgemini.dnd.util.MappingUtil;

@Controller
@RequestMapping("/RawmaterialOrder")
@ComponentScan(basePackages = {
	    "com.capgemini.*"
	})
public class DisplayRawMaterialController {
	
	@Autowired
	RawMaterialService rawmaterialServiceObject;
	
	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.POST)
	    public void display(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    
	    	String jsonMessage = "";
			String errorMessage = "";
			PrintWriter out = response.getWriter();
			DisplayRawMaterialOrder displayRawMaterialOrderObject = new DisplayRawMaterialOrder();
			Map<String, String> fieldValueMap = new HashMap<String, String>();
			fieldValueMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
		
			String DeliveryStatusVar = fieldValueMap.get("deliveryStatus");
			String SupplierIDVar = fieldValueMap.get("supplierid");
			String date1Var = fieldValueMap.get("startdate");
			String date2Var = fieldValueMap.get("endDate");

			displayRawMaterialOrderObject.setDeliveryStatus(DeliveryStatusVar);
			displayRawMaterialOrderObject.setSupplierid(SupplierIDVar);
			displayRawMaterialOrderObject.setStartdate(date1Var);
			displayRawMaterialOrderObject.setEndDate(date2Var);

			
				try {
					jsonMessage = rawmaterialServiceObject.displayRawmaterialOrders(displayRawMaterialOrderObject);
				} catch (DisplayException | BackEndException e) {     
					errorMessage = e.getMessage();
				}
				
			if (errorMessage.isEmpty()) {
				
				

				System.out.println(jsonMessage);
				out.write(jsonMessage);
			}
			
			
		
		}
}
	
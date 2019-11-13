package com.capgemini.dnd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.capgemini.dnd.dto.Supplier;
import com.capgemini.dnd.service.RawMaterialService;
import com.capgemini.dnd.util.MappingUtil;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/DisplaySupplier")
public class SupplierConroller {
			@Autowired
			RawMaterialService RawMatertialServiceObject;
			
			@RequestMapping(method = RequestMethod.POST)
			    public void display(HttpServletRequest request, HttpServletResponse response) throws IOException {
			    
			    	String jsonMessage = "";
					String errorMessage = "";
					PrintWriter out = response.getWriter();
					
				
					Map<String, String> fieldValueMap = new HashMap<String, String>();
					fieldValueMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
					Supplier supplierdetails = new Supplier();
					String supplierId = fieldValueMap.get("supplierId");
					supplierdetails.setSupplierId(supplierId);
					
					try {
						jsonMessage = RawMatertialServiceObject.fetchSupplierDetail(supplierdetails);
						
					} catch (Exception e) {

						e.printStackTrace();
					}
					if (errorMessage.isEmpty()) {
						out.write(jsonMessage);
					}
				}

	}




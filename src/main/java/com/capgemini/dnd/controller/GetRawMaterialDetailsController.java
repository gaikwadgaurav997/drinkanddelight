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
import com.capgemini.dnd.service.RawMaterialService;
import com.capgemini.dnd.util.JsonUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/GetRawMaterialDetails")
public class GetRawMaterialDetailsController {

	@Autowired
	private RawMaterialService rawMaterialService;

	@RequestMapping(method = RequestMethod.GET)
	public String trackRawMaterialOrder(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {

		ArrayList<ArrayList<String>> rawMaterialDetailsList = new ArrayList<>();
		
		rawMaterialDetailsList.add(rawMaterialService.fetchRawMaterialNames());
		rawMaterialDetailsList.add(rawMaterialService.fetchSupplierIds());
		rawMaterialDetailsList.add(rawMaterialService.fetchWarehouseIds());

		return(JsonUtil.convertJavaToJson1(rawMaterialDetailsList));
	}
}
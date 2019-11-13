package com.capgemini.dnd.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.service.RawMaterialService;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.MappingUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/placeRawMaterialOrder")
public class PlaceRawMaterialOrderController {

	@Autowired
	private RawMaterialService rawMaterialService;
	@RequestMapping(value = "/placeOrder",method = RequestMethod.POST)
	public String trackRawMaterialOrder(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {

		Map<String, String> myMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialOrder rawMaterialOrder;
		try {
			rawMaterialOrder = new RawMaterialOrder(myMap.get("name"), myMap.get("supplierId"),Double.parseDouble(myMap.get("quantityValue")), myMap.get("quantityUnit"),sdf.parse(myMap.get("dateOfDelivery")), Double.parseDouble(myMap.get("pricePerUnit")),	myMap.get("warehouseId"));
		} catch (NumberFormatException | ParseException exception) {
			String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
			return errorJsonMessage;
		}

		Date today = new Date();
		rawMaterialOrder.setDateOfOrder(today);
		rawMaterialOrder.setDeliveryStatus("Pending");
		
			String jsonMessage = rawMaterialService.placeRawMaterialOrder(rawMaterialOrder);
			return jsonMessage;
	}
}
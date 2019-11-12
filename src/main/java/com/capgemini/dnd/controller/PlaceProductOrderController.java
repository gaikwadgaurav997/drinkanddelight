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

import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.service.ProductService;
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.MappingUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

 

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/placeProductOrder")
public class PlaceProductOrderController {

 

    @Autowired
    private ProductService productService;

 

    @RequestMapping(method = RequestMethod.POST)
    public String placeProductOrder(HttpServletRequest request) throws JsonParseException, JsonMappingException, IOException {

    	Map<String, String> fieldValueMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ProductOrder productOrder;
        try {
            productOrder = new ProductOrder(fieldValueMap.get("name"), fieldValueMap.get("distributorId"), Double.parseDouble(fieldValueMap.get("quantityValue")), fieldValueMap.get("quantityUnit"),sdf.parse(fieldValueMap.get("dateOfDelivery")), Double.parseDouble(fieldValueMap.get("pricePerUnit")), fieldValueMap.get("warehouseId"));
            System.out.println(productOrder);
        } catch (NumberFormatException | ParseException exception) {
            String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
            return errorJsonMessage;
        }

        Date today = new Date();
        productOrder.setDateOfOrder(today);
        productOrder.setDeliveryStatus("Pending");
        
//        try {
            String jsonMessage = productService.addProductOrder(productOrder);
            return jsonMessage;

//        } catch (ProductOrderNotAddedException | ConnectionException | SQLException | DisplayException exception) {
//            String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
//            return errorJsonMessage;
//        }
    }
}
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
import com.capgemini.dnd.dto.Distributor;
import com.capgemini.dnd.service.ProductService;
import com.capgemini.dnd.util.MappingUtil;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/DisplayDistributor")
public class DisplayDistributor {

		@Autowired
		ProductService ProductServiceObject;
		
		@RequestMapping(method = RequestMethod.POST)
		    public void display(HttpServletRequest request, HttpServletResponse response) throws IOException {
		    
		    	String jsonMessage = "";
				String errorMessage = "";
				PrintWriter out = response.getWriter();
				
				//RawMaterialService rawmaterialServiceObject =new RawMaterialServiceImpl();
				Map<String, String> fieldValueMap = new HashMap<String, String>();
				fieldValueMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
				Distributor distributorDetails = new Distributor();	
				String DistributorID = fieldValueMap.get("distributorId");
				distributorDetails.setDistributorId(DistributorID);
				
				try {
					jsonMessage = ProductServiceObject.fetchDistributorDetail(distributorDetails);

				} catch (Exception e) {

					e.printStackTrace();
				}
				if (errorMessage.isEmpty()) {
					out.write(jsonMessage);
				}
			}

}

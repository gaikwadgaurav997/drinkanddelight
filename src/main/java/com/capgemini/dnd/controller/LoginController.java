package com.capgemini.dnd.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.dto.Employee;
import com.capgemini.dnd.service.EmployeeService;
import com.capgemini.dnd.controller.ControllerConstants;
import com.capgemini.dnd.util.MappingUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/")
public class LoginController {
	@Autowired
	private EmployeeService employeeService;

	private Employee employee=null;

	@RequestMapping(method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws BackEndException, JsonParseException, JsonMappingException, IOException {
		//Map<String, String> fieldValueMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
		System.out.println("hi "+request.getParameter("username"));
		employee=new Employee();
		employee.setUsername(request.getParameter("username"));
		employee.setPassword(request.getParameter("password"));

		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		try {
			if (employeeService.login(employee)) {
				((ObjectNode) dataResponse).put("message", ControllerConstants.LOGIN_SUCCESSFUL_MESSAGE);
				((ObjectNode) dataResponse).put("username", employee.getUsername());
			}
		} catch (Exception exception) {
			((ObjectNode) dataResponse).put("message", exception.getMessage());
		}
		response.getWriter().print(dataResponse);
	}
}
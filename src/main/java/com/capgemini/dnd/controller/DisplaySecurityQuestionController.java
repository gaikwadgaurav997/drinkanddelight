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
import com.capgemini.dnd.util.JsonUtil;
import com.capgemini.dnd.util.MappingUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/display-question")
public class DisplaySecurityQuestionController {
	@Autowired
	private EmployeeService employeeService;

	private Employee employee = null;

	private Employee idealEmployee = null;

	@RequestMapping(method = RequestMethod.POST)
	public void getSecurityQuestion(HttpServletRequest request, HttpServletResponse response)
			throws BackEndException, JsonParseException, JsonMappingException, IOException {
		Map<String, String> fieldValueMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
		
		employee = new Employee();
		employee.setUsername(fieldValueMap.get("username"));
		try {
			idealEmployee = new Employee();
			idealEmployee = employeeService.fetchOneConfidentialDetail(employee);
			String jsonQuestion = JsonUtil.convertJavaToJson(idealEmployee.getSecurityQuestion());
			response.getWriter().write(jsonQuestion);
		} catch (Exception exception) {
			String errorJsonMessage = JsonUtil.convertJavaToJson(exception.getMessage());
			response.getWriter().write(errorJsonMessage);
		}
	}
}
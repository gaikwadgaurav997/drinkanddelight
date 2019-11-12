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
import com.capgemini.dnd.customexceptions.InvalidPasswordException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.RowNotFoundException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongSecurityAnswerException;
import com.capgemini.dnd.dto.Employee;
import com.capgemini.dnd.service.EmployeeService;
import com.capgemini.dnd.util.MappingUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@CrossOrigin(origins = "*")
@RequestMapping("/change-password")
public class ChangePasswordController {
	@Autowired
	private EmployeeService employeeService;

	private Employee tempEmployee = null;

	private Employee idealEmployee = null;

	private Employee actualEmployee = null;

	@RequestMapping(method = RequestMethod.POST)
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws BackEndException, JsonParseException, JsonMappingException, IOException {
		Map<String, String> fieldValueMap = MappingUtil.convertJsonObjectToFieldValueMap(request);
		
		tempEmployee = new Employee();
		tempEmployee.setUsername(fieldValueMap.get("username"));
		idealEmployee = null;
		try {
			idealEmployee = new Employee();
			idealEmployee = employeeService.fetchOneConfidentialDetail(tempEmployee);
		} catch (BackEndException e) {
		}

		actualEmployee = new Employee();
		actualEmployee.setUsername(fieldValueMap.get("username"));
		actualEmployee.setSecurityAnswer(fieldValueMap.get("answer"));
		actualEmployee.setPassword(fieldValueMap.get("newPassword"));
		actualEmployee.setConfirmPassword(fieldValueMap.get("confirmPassword"));

		ObjectMapper mapper = new ObjectMapper();
		JsonNode dataResponse = mapper.createObjectNode();
		try {
			if (employeeService.changePassword(idealEmployee, actualEmployee)) {
				((ObjectNode) dataResponse).put("message", ControllerConstants.PASSWORD_CHANGE_SUCCESSFUL_MESSAGE);
				((ObjectNode) dataResponse).put("username", actualEmployee.getUsername());
			}
		} catch (UnregisteredEmployeeException | WrongSecurityAnswerException | PasswordException | BackEndException
				| InvalidPasswordException | RowNotFoundException exception) {
			((ObjectNode) dataResponse).put("message", exception.getMessage());
		} finally {
			response.getWriter().print(dataResponse);
		}
	}
}

package com.capgemini.dnd.service;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongSecurityAnswerException;
import com.capgemini.dnd.dto.Employee;

public interface EmployeeService {

	public boolean login(Employee employee) throws UnregisteredEmployeeException, PasswordException;

	boolean employeeExists(Employee employee) throws UnregisteredEmployeeException;

	Employee fetchOneConfidentialDetail(Employee employee) throws BackEndException;

	boolean changePassword(Employee idealEmployee, Employee actualEmployee) throws WrongSecurityAnswerException, PasswordException;
}

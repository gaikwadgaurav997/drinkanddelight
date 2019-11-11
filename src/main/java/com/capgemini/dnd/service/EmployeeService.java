package com.capgemini.dnd.service;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.InvalidPasswordException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.RowNotFoundException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongPasswordException;
import com.capgemini.dnd.customexceptions.WrongSecurityAnswerException;
import com.capgemini.dnd.dto.Employee;

public interface EmployeeService {
	
	
	
	public boolean login(Employee employee) throws UnregisteredEmployeeException, WrongPasswordException, BackEndException;
	
	boolean employeeExists(Employee employee) throws BackEndException, RowNotFoundException;

	Employee fetchOneConfidentialDetail(Employee employee) throws BackEndException;

	boolean changePassword(Employee idealEmployee, Employee actualEmployee)
			throws UnregisteredEmployeeException, WrongSecurityAnswerException, PasswordException, BackEndException,
			InvalidPasswordException, RowNotFoundException;

}

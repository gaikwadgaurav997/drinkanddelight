package com.capgemini.dnd.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.RowNotFoundException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongPasswordException;
import com.capgemini.dnd.customexceptions.WrongSecurityAnswerException;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dao.EmployeeDAO;
import com.capgemini.dnd.dto.Employee;
import com.capgemini.dnd.entity.EmployeeCredentialEntity;
import com.capgemini.dnd.util.CryptoFunction;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	private Logger logger = Logger.getRootLogger();

	@Autowired
	EmployeeDAO employeeDAO;
	
	

	/*******************************************************************************************************
	 * - Function Name : login - Input Parameters : Employee (DTO; its username and
	 * password variables are set) - Return Type : boolean - Throws :
	 * UnregisteredEmployeeException, WrongPasswordException, BackEndException -
	 * Author : Akash Deep, Capgemini - Description : To check wheteher an employee
	 * with the given username and password is eligible to login or not, uses
	 * employeeExists and setLoggedIn
	 ********************************************************************************************************/
	@Override
	public boolean login(Employee employee) throws UnregisteredEmployeeException, WrongPasswordException, BackEndException {
		boolean eligibleToLogin = false;
		if (employeeDAO.existsByUserName(employee.getUsername())) {
			EmployeeCredentialEntity empCredEntity = employeeDAO.findByUserName(employee.getUsername()).get(0);
			if (CryptoFunction.isExpectedPassword(employee.getPassword(), empCredEntity)) {
				eligibleToLogin = true;
			} else {
				logger.error(Constants.INCORRECT_PASSWORD_MESSAGE);
				throw new WrongPasswordException(Constants.INCORRECT_PASSWORD_MESSAGE);
			}
		} else {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_UNREGISTERED_USER);
			throw new UnregisteredEmployeeException(Constants.LOGGER_ERROR_MESSAGE_UNREGISTERED_USER);
		}
		
		return eligibleToLogin;
	}

	@Override
	public boolean employeeExists(Employee employee) throws BackEndException, RowNotFoundException {
		return employeeDAO.existsByUserName(employee.getUsername());
	}

	/*******************************************************************************************************
	 - Function Name	:	fetchOneConfidential
	 - Input Parameters	:	Employee (DTO; its username variable is set)
	 - Return Type		:	Employee with all its variables filled from database
	 - Throws			:  	BackEndException
	 - Author			:	Akash Deep, Capgemini
	 - Description		:	To fetch all the securityQuestion, securityAnswer, Hash and Salt 
	 						of the employee with the given username.
	 ********************************************************************************************************/
	@Override
	public Employee fetchOneConfidentialDetail(Employee employee) throws BackEndException {
		EmployeeCredentialEntity empCredEntity = employeeDAO.findByUserName(employee.getUsername()).get(0);
		employee.setEmpId(empCredEntity.getEmpId());
		employee.setSecurityQuestion(empCredEntity.getSecurityQuestion());
		employee.setSecurityAnswer(empCredEntity.getSecurityAnswer());
		employee.setHash(empCredEntity.getHash());
		employee.setSalt(empCredEntity.getSalt());
		return employee;
	}

	/*******************************************************************************************************
	 - Function Name	:	changePassword
	 - Input Parameters	:	Employee, Employee (DTOs)
	 						First Employee parameter has the correct details corresponding to its username.
	 						Second Employee parameter has the details input by the user which have to be checked against 
	 						the details of the first Employee parameter.
	 - Return Type		:	boolean
	 - Throws			:  	WrongSecurityAnswerException, PasswordException, BackEndException, RowNotFoundException
	 - Author			:	Akash Deep, Capgemini
	 - Description		:	To change password of the employee with given username if it has correct details 
	 						else throw appropriate exceptions.
	 ********************************************************************************************************/
	@Override
	public boolean changePassword(Employee idealEmployee, Employee actualEmployee)
			throws WrongSecurityAnswerException, PasswordException, BackEndException, RowNotFoundException {
		boolean passwordChanged = false;

		if (idealEmployee.getSecurityAnswer().equals(actualEmployee.getSecurityAnswer())) {
			if (!CryptoFunction.isExpectedPassword(actualEmployee.getPassword(), idealEmployee.getHash(),
					idealEmployee.getSalt())) {
				if (actualEmployee.getPassword().equals(actualEmployee.getConfirmPassword())) {
					EmployeeCredentialEntity empCredEntity = employeeDAO.findById(idealEmployee.getEmpId()).get();
					empCredEntity.setSalt(CryptoFunction.getNextSalt());
					empCredEntity.setHash(CryptoFunction.hash(actualEmployee.getPassword(), empCredEntity.getSalt()));
					employeeDAO.saveAndFlush(empCredEntity);
					passwordChanged = true;
				} else {
					logger.error(Constants.LOGGER_ERROR_MESSAGE_PASSWORD_MISMATCH);
					throw new PasswordException(Constants.LOGGER_ERROR_MESSAGE_PASSWORD_MISMATCH);
				}
			} else {
				logger.error(Constants.LOGGER_ERROR_MESSAGE_PASSWORD_UNCHANGED);
				throw new PasswordException(Constants.LOGGER_ERROR_MESSAGE_PASSWORD_UNCHANGED);
			}
		} else {
			logger.error(Constants.LOGGER_ERROR_MESSAGE_WRONG_ANSWER);
			throw new WrongSecurityAnswerException(Constants.LOGGER_ERROR_MESSAGE_WRONG_ANSWER);
		}
		return passwordChanged;
	}
}

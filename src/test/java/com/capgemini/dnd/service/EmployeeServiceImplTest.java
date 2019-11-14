package com.capgemini.dnd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dnd.customexceptions.BackEndException;
import com.capgemini.dnd.customexceptions.PasswordException;
import com.capgemini.dnd.customexceptions.UnregisteredEmployeeException;
import com.capgemini.dnd.customexceptions.WrongSecurityAnswerException;
import com.capgemini.dnd.dao.Constants;
import com.capgemini.dnd.dto.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
class EmployeeServiceImplTest {

	@Autowired
	private EmployeeService employeeService;

	@Test
	public void testLogin1() throws PasswordException {
		Employee employee = new Employee();
		employee.setUsername("asdfa");
		employee.setPassword("hello");
		assertThrows(UnregisteredEmployeeException.class, () -> {
			employeeService.login(employee);
		});

		try {
			employeeService.login(employee);
		} catch (UnregisteredEmployeeException exception) {
			assertEquals(Constants.LOGGER_ERROR_MESSAGE_UNREGISTERED_USER, exception.getMessage());
		}
	}

	@Test
	public void testLogin2() throws UnregisteredEmployeeException {
		Employee employee = new Employee();
		employee.setUsername("ankit_40");
		employee.setPassword("dfssf");
		assertThrows(PasswordException.class, () -> {
			employeeService.login(employee);
		});

		try {
			employeeService.login(employee);
		} catch (PasswordException exception) {
			assertEquals(Constants.INCORRECT_PASSWORD_MESSAGE, exception.getMessage());
		}
	}

	@Test
	public void testLogin3() throws UnregisteredEmployeeException, PasswordException {
		Employee employee = new Employee();
		employee.setUsername("ankit_40");
		employee.setPassword("abcd");

		assertTrue(employeeService.login(employee));
	}

	@Test
	public void testEmployeeExists1() {
		Employee employee = new Employee();
		employee.setUsername("abcdef");
		assertThrows(UnregisteredEmployeeException.class, () -> {
			employeeService.employeeExists(employee);
		});

		try {
			employeeService.employeeExists(employee);
		} catch (UnregisteredEmployeeException exception) {
			assertEquals(Constants.LOGGER_ERROR_MESSAGE_UNREGISTERED_USER, exception.getMessage());
		}
	}

	@Test
	public void testEmployeeExists2() throws UnregisteredEmployeeException {
		Employee employee = new Employee();
		employee.setUsername("ram_40");
		assertTrue(employeeService.employeeExists(employee));
	}

	@Test
	public void testFetchOneConfidentialDetail1() {
		Employee employee = new Employee();
		employee.setUsername("abcdef");
		assertThrows(BackEndException.class, () -> {
			employeeService.fetchOneConfidentialDetail(employee);
		});

		try {
			employeeService.fetchOneConfidentialDetail(employee);
		} catch (BackEndException exception) {
			assertEquals(Constants.SERVER_ERROR_MESSAGE, exception.getMessage());
		}
	}

	@Test
	public void testFetchOneConfidentialDetail2() throws BackEndException {
		Employee employee = new Employee();
		employee.setUsername("ram_40");
		assertTrue(employeeService.fetchOneConfidentialDetail(employee) instanceof Employee);
	}

	@Test
	public void testChangePassword1() throws BackEndException, PasswordException {
		Employee tempEmployee = new Employee();
		tempEmployee.setUsername("ram_40");
		Employee idealEmployee = employeeService.fetchOneConfidentialDetail(tempEmployee);

		Employee actualEmployee = new Employee();
		actualEmployee.setSecurityAnswer("Ram");
		actualEmployee.setPassword("abcd");
		actualEmployee.setConfirmPassword("abcd");
		assertThrows(WrongSecurityAnswerException.class, () -> {
			employeeService.changePassword(idealEmployee, actualEmployee);
		});

		try {
			employeeService.changePassword(idealEmployee, actualEmployee);
		} catch (WrongSecurityAnswerException exception) {
			assertEquals(Constants.LOGGER_ERROR_MESSAGE_WRONG_ANSWER, exception.getMessage());
		}
	}

	@Test
	public void testChangePassword2() throws BackEndException, WrongSecurityAnswerException {
		Employee tempEmployee = new Employee();
		tempEmployee.setUsername("ram_40");
		Employee idealEmployee = employeeService.fetchOneConfidentialDetail(tempEmployee);

		Employee actualEmployee = new Employee();
		actualEmployee.setSecurityAnswer("Ramu");
		actualEmployee.setPassword("hello");
		actualEmployee.setConfirmPassword("hello");

		assertThrows(PasswordException.class, () -> {
			employeeService.changePassword(idealEmployee, actualEmployee);
		});

		try {
			employeeService.changePassword(idealEmployee, actualEmployee);
		} catch (PasswordException exception) {
			assertEquals(Constants.LOGGER_ERROR_MESSAGE_PASSWORD_UNCHANGED, exception.getMessage());
		}
	}

	@Test
	public void testChangePassword3() throws BackEndException, WrongSecurityAnswerException {
		Employee tempEmployee = new Employee();
		tempEmployee.setUsername("ram_40");
		Employee idealEmployee = employeeService.fetchOneConfidentialDetail(tempEmployee);

		Employee actualEmployee = new Employee();
		actualEmployee.setSecurityAnswer("Ramu");
		actualEmployee.setPassword("abcde");
		actualEmployee.setConfirmPassword("abcd");

		assertThrows(PasswordException.class, () -> {
			employeeService.changePassword(idealEmployee, actualEmployee);
		});

		try {
			employeeService.changePassword(idealEmployee, actualEmployee);
		} catch (PasswordException exception) {
			assertEquals(Constants.LOGGER_ERROR_MESSAGE_PASSWORD_MISMATCH, exception.getMessage());
		}
	}

	@Test
	public void testChangePassword4() throws BackEndException, WrongSecurityAnswerException, PasswordException {
		Employee tempEmployee = new Employee();
		tempEmployee.setUsername("ram_40");
		Employee idealEmployee = employeeService.fetchOneConfidentialDetail(tempEmployee);

		Employee actualEmployee = new Employee();
		actualEmployee.setSecurityAnswer("Ramu");
		actualEmployee.setPassword("abcd");
		actualEmployee.setConfirmPassword("abcd");

		assertTrue(employeeService.changePassword(idealEmployee, actualEmployee));

		idealEmployee = employeeService.fetchOneConfidentialDetail(tempEmployee);
		Employee correctiveEmployee = new Employee();
		correctiveEmployee.setSecurityAnswer("Ramu");
		correctiveEmployee.setPassword("hello");
		correctiveEmployee.setConfirmPassword("hello");
		employeeService.changePassword(idealEmployee, correctiveEmployee);
		
	}
}

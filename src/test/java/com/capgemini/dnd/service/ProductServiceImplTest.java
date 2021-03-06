package com.capgemini.dnd.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.capgemini.dnd.customexceptions.DisplayException;
import com.capgemini.dnd.customexceptions.ExitDateException;
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProductOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.DisplayProductOrder;
import com.capgemini.dnd.dto.ProductOrder;
import com.capgemini.dnd.dto.ProductStock;

@RunWith(SpringRunner.class)
@SpringBootTest
class ProductServiceImplTest {
	
	@Autowired
	private ProductService productService;

	@Test
	public void testTrackProductOrder1() {
		ProductStock productStock = new ProductStock("2");
		String actualMessage = null;
		try {
			if(productService.doesProductOrderIdExist(productStock.getOrderId())) {
			actualMessage = productService.trackProductOrder(productStock);
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "{\"message\":\"The order ID had been in the warehouse with warehouseID = w01 from 2019-08-16 05:30:00.0 to 2019-10-23 05:30:00.0(68 days)\"}"; 
			
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	public void testTrackProductOrder2() {

		ProductStock productStock = new ProductStock("500");
		String actualMessage = null;
		try {
			if(productService.doesProductOrderIdExist(productStock.getOrderId())) {
			actualMessage = productService.trackProductOrder(productStock);
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "Product Order ID does not exist";
		assertEquals(expectedMessage, actualMessage);
		
	}

	@Test
	public void testDoesProductOrderIdExist1() throws ProductOrderIDDoesNotExistException {
		assertTrue(productService.doesProductOrderIdExist("5"));
	}
	
	@Test
	public void testDoesProductOrderIdExist2() throws ProductOrderIDDoesNotExistException {
		
		assertThrows(ProductOrderIDDoesNotExistException.class, () -> {
			productService.doesProductOrderIdExist("500");
			});
	}
	
	@Test
	public void testDoesProductOrderIdExist3() throws ProductOrderIDDoesNotExistException {
		
		assertThrows(ProductOrderIDDoesNotExistException.class, () -> {
			productService.doesProductOrderIdExist("5OQ");
			});
	}

	@Test
	public void testExitDateCheck1() throws IncompleteDataException, ParseException, ExitDateException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductStock productStock = new ProductStock("1",sdf.parse("2019-10-09"));
		
		
			 boolean actualMessage = productService.exitDateCheck(productStock);
			 assertTrue(actualMessage);
			
	}
	
	@Test
	public void testExitDateCheck2() throws IncompleteDataException, ParseException, ExitDateException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductStock productStock = new ProductStock("1",sdf.parse("2020-10-09"));
		assertThrows(ExitDateException.class, () -> {
				 productService.exitDateCheck(productStock);
				});
			
	}
	
	@Test
	public void testExitDateCheck3() throws IncompleteDataException, ParseException, ExitDateException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductStock productStock = new ProductStock("1",sdf.parse("2010-10-09"));
		assertThrows(ExitDateException.class, () -> {
				 productService.exitDateCheck(productStock);
				});
			
	}

	
	@Test
	@Rollback(true)
	public void testUpdateExitDateinStock1() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductStock productStock = new ProductStock("5", sdf.parse("2019-10-09"));
		String actualMessage = null;
		try {
			if(productService.doesProductOrderIdExist(productStock.getOrderId())) {
			actualMessage = productService.updateExitDateinStock(productStock);
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "{\"message\":\"Data inserted\"}";
		assertEquals(expectedMessage, actualMessage);
	}

		
	@Test
	@Rollback(true)
	public void testUpdateExitDateinStock2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductStock productStock = new ProductStock("500", sdf.parse("2019-10-09"));
		String actualMessage = null;
		try {
			if(productService.doesProductOrderIdExist(productStock.getOrderId())) {
			actualMessage = productService.updateExitDateinStock(productStock);
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "Product Order ID does not exist";
		assertEquals(expectedMessage, actualMessage);
	}
	
	

	@Test
	@Rollback(true)
	public void testUpdateProductStock1() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductStock productStock = new ProductStock("100", sdf.parse("2019-10-09"), sdf.parse("2019-10-09"), "Passed");
		String actualMessage = null;
		try {
			if(productService.doesProductOrderIdExist(productStock.getOrderId())) {
			actualMessage = productService.updateProductStock(productStock);
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "Product Order ID does not exist";
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Rollback(true)
	public void testUpdateProductStock2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductStock productStock = new ProductStock("5", sdf.parse("2019-09-02"), sdf.parse("2020-02-02"), "Passed");
		String actualMessage = null;
		try {
			if(productService.doesProductOrderIdExist(productStock.getOrderId())) {
			actualMessage = productService.updateProductStock(productStock);
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "{\"message\":\"Data inserted\"}";
		assertEquals(expectedMessage, actualMessage);
	}
	

	@Test
	public void testDoesProductOrderIdExistInStock1() {
		assertTrue(productService.doesProductOrderIdExistInStock("5"));
	}
	
	@Test
	public void testDoesProductOrderIdExistInStock2() {
		assertFalse(productService.doesProductOrderIdExistInStock("500"));
	}
	
	@Test
	public void testDoesProductOrderIdExistInStock3() {
		assertFalse(productService.doesProductOrderIdExistInStock("5OQ"));
	}
	
	@Test
	@Rollback(true)
	public void testAddProductOrder() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ProductOrder productOrder = new ProductOrder("JUICE","d004",25,"litres", sdf.parse("2019-12-12"),50,"w03");
		assertEquals(productService.addProductOrder(productOrder),"{\"message\":\"Product Order added successfully\"}");
	}

	@Test
	@Rollback(true)
	public void testUpdateProductDeliveryStatus1() throws Exception  {
		String actualMessage = null;
		
		try {
			if(productService.doesProductOrderIdExist("5") ){
			actualMessage = productService.updateStatusProductOrder("5","Dispatched");
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "{\"message\":\"Updated succesfully\"}";
		assertEquals(actualMessage, expectedMessage);
	}
	
	@Test
	@Rollback(true)
	public void testUpdateProductDeliveryStatus2() throws Exception  {
		String actualMessage = null;
		try {
			if(productService.doesProductOrderIdExist("1000") ){
			actualMessage = productService.updateStatusProductOrder("5","Dispatched");
			}
		} catch (ProductOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "Product Order ID does not exist";
		assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testDisplayProductOrder() {
		DisplayProductOrder displayProductOrder = new DisplayProductOrder("DISPATCHED","SUP1","2019-11-06","2019-11-06");
		
		assertThrows(DisplayException.class, () -> {
			productService.displayProductOrders(displayProductOrder);
			});
	}
}

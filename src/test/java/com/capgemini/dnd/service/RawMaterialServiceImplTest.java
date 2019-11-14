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
import com.capgemini.dnd.customexceptions.IncompleteDataException;
import com.capgemini.dnd.customexceptions.ProcessDateException;
import com.capgemini.dnd.customexceptions.RMOrderIDDoesNotExistException;
import com.capgemini.dnd.dto.DisplayRawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialOrder;
import com.capgemini.dnd.dto.RawMaterialStock;

@RunWith(SpringRunner.class)
@SpringBootTest
class RawMaterialServiceImplTest {

	@Autowired
	private RawMaterialService rawMaterialService;
	
	@Test
	@Rollback(true)
	public void testTrackRawMaterialOrder1() {

		RawMaterialStock rawMaterialStock = new RawMaterialStock("2");
		String actualMessage = null;
		try {
			if(rawMaterialService.doesRawMaterialOrderIdExist(rawMaterialStock.getOrderId())) {
			actualMessage = rawMaterialService.trackRawMaterialOrder(rawMaterialStock);
			}
		} catch (RMOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "{\"message\":\"The order ID had been in the warehouse with warehouseID = w01 from 2019-08-03 05:30:00.0 to 2019-10-07 05:30:00.0(65 days)\"}";
		assertEquals(expectedMessage, actualMessage);
		
	}
	
	@Test
	@Rollback(true)
	public void testTrackRawMaterialOrder2() {

		RawMaterialStock rawMaterialStock = new RawMaterialStock("500");
		String actualMessage = null;
		try {
			if(rawMaterialService.doesRawMaterialOrderIdExist(rawMaterialStock.getOrderId())) {
			actualMessage = rawMaterialService.trackRawMaterialOrder(rawMaterialStock);
			}
		} catch (RMOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "RawMaterial Order ID does not exist";
		assertEquals(expectedMessage, actualMessage);
		
	}

	@Test
	@Rollback(true)
	public void testDoesRawMaterialOrderIdExist1() throws RMOrderIDDoesNotExistException {
		assertTrue(rawMaterialService.doesRawMaterialOrderIdExist("5"));
	}
	
	@Test
	@Rollback(true)
	public void testDoesRawMaterialOrderIdExist2() throws RMOrderIDDoesNotExistException {
		
		assertThrows(RMOrderIDDoesNotExistException.class, () -> {
			rawMaterialService.doesRawMaterialOrderIdExist("500");
			});
	}
	
	@Test
	@Rollback(true)
	public void testDoesRawMaterialOrderIdExist3() throws RMOrderIDDoesNotExistException {
		
		assertThrows(RMOrderIDDoesNotExistException.class, () -> {
			rawMaterialService.doesRawMaterialOrderIdExist("5OQ");
			});
	}

	@Test
	@Rollback(true)
	public void testProcessDateCheck1() throws ProcessDateException, IncompleteDataException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialStock rawMaterialStock = new RawMaterialStock("1",sdf.parse("2019-10-09"));
		
		
			 boolean actualMessage = rawMaterialService.processDateCheck(rawMaterialStock);
			 assertTrue(actualMessage);
			
	}
	
	@Test
	@Rollback(true)
	public void testProcessDateCheck2() throws ProcessDateException, IncompleteDataException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialStock rawMaterialStock = new RawMaterialStock("1",sdf.parse("2020-10-09"));
		assertThrows(ProcessDateException.class, () -> {
				 rawMaterialService.processDateCheck(rawMaterialStock);
				});
			
	}
	
	@Test
	@Rollback(true)
	public void testProcessDateCheck3() throws ProcessDateException, IncompleteDataException, ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialStock rawMaterialStock = new RawMaterialStock("1",sdf.parse("2010-10-09"));
		assertThrows(ProcessDateException.class, () -> {
				 rawMaterialService.processDateCheck(rawMaterialStock);
				});
			
	}

	
	@Test
	@Rollback(true)
	public void testUpdateProcessDateinStock1() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialStock rawMaterialStock = new RawMaterialStock("5", sdf.parse("2019-10-09"));
		String actualMessage = null;
		try {
			if(rawMaterialService.doesRawMaterialOrderIdExist(rawMaterialStock.getOrderId())) {
			actualMessage = rawMaterialService.updateProcessDateinStock(rawMaterialStock);
			}
		} catch (RMOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "{\"message\":\"Data inserted\"}";
		assertEquals(expectedMessage, actualMessage);
	}

		
	@Test
	@Rollback(true)
	public void testUpdateProcessDateinStock2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialStock rawMaterialStock = new RawMaterialStock("500", sdf.parse("2019-10-09"));
		String actualMessage = null;
		try {
			if(rawMaterialService.doesRawMaterialOrderIdExist(rawMaterialStock.getOrderId())) {
			actualMessage = rawMaterialService.updateProcessDateinStock(rawMaterialStock);
			}
		} catch (RMOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "RawMaterial Order ID does not exist";
		assertEquals(expectedMessage, actualMessage);
	}
	
	

	@Test
	@Rollback(true)
	public void testUpdateRawMaterialStock1() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialStock rawMaterialStock = new RawMaterialStock("100", sdf.parse("2019-10-09"), sdf.parse("2019-10-09"), "Passed");
		String actualMessage = null;
		try {
			if(rawMaterialService.doesRawMaterialOrderIdExist(rawMaterialStock.getOrderId())) {
			actualMessage = rawMaterialService.updateRawMaterialStock(rawMaterialStock);
			}
		} catch (RMOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "RawMaterial Order ID does not exist";
		assertEquals(expectedMessage, actualMessage);
	}
	
	@Test
	@Rollback(true)
	public void testUpdateRawMaterialStock2() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialStock rawMaterialStock = new RawMaterialStock("5", sdf.parse("2019-09-02"), sdf.parse("2020-02-02"), "Passed");
		String actualMessage = null;
		try {
			if(rawMaterialService.doesRawMaterialOrderIdExist(rawMaterialStock.getOrderId())) {
			actualMessage = rawMaterialService.updateRawMaterialStock(rawMaterialStock);
			}
		} catch (RMOrderIDDoesNotExistException e) {
			actualMessage = e.getMessage();
		}
		String expectedMessage = "{\"message\":\"Data inserted\"}";
		assertEquals(expectedMessage, actualMessage);
	}
	

	@Test
	@Rollback(true)
	public void testDoesRawMaterialOrderIdExistInStock1() {
		assertTrue(rawMaterialService.doesRawMaterialOrderIdExistInStock("5"));
	}
	
	@Test
	@Rollback(true)
	public void testDoesRawMaterialOrderIdExistInStock2() {
		assertFalse(rawMaterialService.doesRawMaterialOrderIdExistInStock("500"));
	}
	
	@Test
	@Rollback(true)
	public void testDoesRawMaterialOrderIdExistInStock3() {
		assertFalse(rawMaterialService.doesRawMaterialOrderIdExistInStock("5OQ"));
	}
	
	@Test
	@Rollback(true)
	public void testPlaceRawMaterialOrder() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		RawMaterialOrder rawMaterialOrder = new RawMaterialOrder("SALT","sup2",75,"kg", sdf.parse("2019-12-22"),25,"w03");
		assertEquals(rawMaterialService.placeRawMaterialOrder(rawMaterialOrder),"{\"message\":\"Raw Material Order added successfully\"}");
	}

	@Test
    @Rollback(true)
    public void testUpdateRawMaterialDeliveryStatus1() throws Exception{
        String actualMessage = null;
        try {
            if(rawMaterialService.doesRawMaterialOrderIdExist("5") ){
            actualMessage = rawMaterialService.updateStatusRawMaterialOrder("5","Recieved");
            }
        } catch (RMOrderIDDoesNotExistException e) {
            actualMessage = e.getMessage();
        }
        String expectedMessage = "{\"message\":\"Updated succesfully\"}";
        assertEquals(actualMessage, expectedMessage);
    }
	
	@Test
    @Rollback(true)
    public void testUpdateRawMaterialDeliveryStatus2() throws Exception{
        String actualMessage = null;
        try {
            if(rawMaterialService.doesRawMaterialOrderIdExist("1000") ){
            actualMessage = rawMaterialService.updateStatusRawMaterialOrder("5","Recieved");
            }
        } catch (RMOrderIDDoesNotExistException e) {
            actualMessage = e.getMessage();
        }
        String expectedMessage = "RawMaterial Order ID does not exist";
        assertEquals(expectedMessage, actualMessage);
    }
	

	@Test
	@Rollback(true)
	public void testDisplayRawMaterialOrder() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		Date today = new Date();
		DisplayRawMaterialOrder displayRawMaterialOrder = new DisplayRawMaterialOrder("DISPATCHED","SUP1","2019-11-06","2019-11-06");
		
		assertThrows(DisplayException.class, () -> {
			rawMaterialService.displayRawmaterialOrders(displayRawMaterialOrder);
			});
	}
}

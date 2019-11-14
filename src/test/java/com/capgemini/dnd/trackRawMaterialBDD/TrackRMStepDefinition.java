package com.capgemini.dnd.trackRawMaterialBDD;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class TrackRMStepDefinition {

	public TrackRMStepDefinition() {
		
	}
	
	WebDriver driver;
	
	@Given("User is on login page")
	public void user_is_on_login_page() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("start-maximized");
		
//		options.addArguments("disable-web-security");
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\gauragai\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/");
	}

	@Given("User enters login credentials")
	public void user_enters_login_credentials() {
		driver.findElement(By.name("username")).sendKeys("saurabh123");
		driver.findElement(By.name("password")).sendKeys("hello");
		WebElement loginBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-login/body/div/div/div/div[2]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", loginBtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		
	}

	@Given("User selects Track Raw Material Option from Raw Material dropdown")
	public void user_selects_Track_Raw_Material_Option_from_Raw_Material_dropdown() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
		
		WebElement rmBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/button"));
		JavascriptExecutor jsRMButton = (JavascriptExecutor)driver;
		jsRMButton.executeScript("arguments[0].click();", rmBtn);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}

	    
	    WebElement trackBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/div/a[6]"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", trackBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	  
	}
	
	@Given("User is on track raw material order page")
	public void user_is_on_track_raw_material_order_page() {
		String currentUrl = driver.getCurrentUrl();
		 Assert.assertEquals("http://localhost:4200/track-rawmaterial-order", currentUrl);
	}

	@When("User enters number in the order ID field as {string}")
	public void user_enters_number_in_the_order_ID_field_as(String string) {
		driver.findElement(By.name("orderID")).sendKeys(string);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	}

	@When("user clicks on track order button present below")
	public void user_clicks_on_track_order_button_present_below() {
		WebElement randomClick = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/button"));
		JavascriptExecutor jsRandom = (JavascriptExecutor)driver;
		jsRandom.executeScript("arguments[0].click();", randomClick);
		
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			
		}
		
		
		WebElement trackBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-track-rawmaterial-order/div[1]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", trackBtn);
	}

	@Then("The shelf life of the given order Id {int} is displayed below")
	public void the_shelf_life_of_the_given_order_Id_is_displayed_below(Integer int1) {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			}
		
		WebElement outputElement = driver.findElement(By.name("outputbox"));
		
		String outputValue = outputElement.getText();
		
		Assert.assertEquals("The order ID had been in the warehouse with warehouseID = w02 from 2019-07-17 05:30:00.0 to 2019-10-30 05:30:00.0(105 days)",outputValue);
	}

	@Then("A message {string} is displayed in the blank space below")
	public void a_message_is_displayed_in_the_blank_space_below(String string) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			}
		
		WebElement outputElement = driver.findElement(By.name("outputbox"));
		
		String outputValue = outputElement.getText();
		
		Assert.assertEquals(string,outputValue);
	}
	
	@After
	public void tearDown() {
	   driver.quit();
	}

}

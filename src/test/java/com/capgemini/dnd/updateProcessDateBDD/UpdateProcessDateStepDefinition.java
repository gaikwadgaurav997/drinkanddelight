package com.capgemini.dnd.updateProcessDateBDD;

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
public class UpdateProcessDateStepDefinition {

	public UpdateProcessDateStepDefinition() {
	}
	
	WebDriver driver;
	
	@Given("User is on DND login webpage")
	public void user_is_on_DND_login_webpage() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("start-maximized");
		//options.addArguments("disable-web-security");
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\gauragai\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/");
	}

	@Given("User enters login credentials as given to him")
	public void user_enters_login_credentials_as_given_to_him() {
		driver.findElement(By.name("username")).sendKeys("saurabh123");
		driver.findElement(By.name("password")).sendKeys("hello");
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"homepage\"]/div/div/div/div[2]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", loginBtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
	}

	@Given("User selects Update Process Date Option from Raw Material dropdown")
	public void user_selects_Update_Process_Date_Option_from_Raw_Material_dropdown() {
		
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

	    
	    WebElement updateBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/div/a[5]"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", updateBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	}
	
	@Given("User is on update process date page")
	public void user_is_on_update_process_date_page() {
		
		String currentUrl = driver.getCurrentUrl();
		 Assert.assertEquals("http://localhost:4200/set-process-date", currentUrl);
	}

	@When("User enters a number in order ID as {string}")
	public void user_enters_a_number_in_order_ID_as(String string) {
		driver.findElement(By.name("orderID")).sendKeys(string);
	}

	@When("User enters Process Date as {string}")
	public void user_enters_Process_Date_as(String string) {
		WebElement dateInput = driver.findElement(By.xpath("//*[@id=\"processdate\"]"));
		dateInput.click();
		dateInput.clear();
		dateInput.sendKeys(string);
		WebElement randomClick = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/button"));
		JavascriptExecutor jsRandom = (JavascriptExecutor)driver;
		jsRandom.executeScript("arguments[0].click();", randomClick);
		
	}

	@When("user clicks on update button")
	public void user_clicks_on_update_button() {
		WebElement randomClick = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/button"));
		JavascriptExecutor jsRandom = (JavascriptExecutor)driver;
		jsRandom.executeScript("arguments[0].click();", randomClick);
		
		
		WebElement updateBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-set-process-date/div[1]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", updateBtn);
	}

	@Then("The message such as {string} is displayed")
	public void the_message_such_as_is_displayed(String string) {
		try {
			Thread.sleep(9000);
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

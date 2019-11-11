package com.capgemini.dnd.updateExitDateBDD;

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
public class UpdateExitDateStepDefinition {

	public UpdateExitDateStepDefinition() {
		
	}
	
	WebDriver driver;
	
	@Given("User is on DND login page")
	public void user_is_on_DND_login_page() {
		ChromeOptions options=new ChromeOptions();
		options.addArguments("start-maximized");
		
//		options.addArguments("disable-web-security");
		
		
		System.setProperty("webdriver.chrome.driver","C:\\Users\\gauragai\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/");
	}

	@Given("User enters login credentials as given")
	public void user_enters_login_credentials_as_given() {
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

	@Given("User selects Update Exit Date Option from Product dropdown")
	public void user_selects_Update_Exit_Date_Option_from_Product_dropdown() {
		WebElement productBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRMButton = (JavascriptExecutor)driver;
		jsRMButton.executeScript("arguments[0].click();", productBtn);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}

	    
	    WebElement updateBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/div/a[5]"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", updateBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	}
	
	@Given("User is on update exit date page")
	public void user_is_on_update_exit_date_page() {
		
		String currentUrl = driver.getCurrentUrl();
		 Assert.assertEquals("http://localhost:4200/set-exit-date", currentUrl);
	}

	@When("User enters the number in order ID as {string}")
	public void user_enters_the_number_in_order_ID_as(String string) {
		driver.findElement(By.name("orderID")).sendKeys(string);
	}

	@When("User enters Exit Date as {string}")
	public void user_enters_Exit_Date_as(String string) {
		
		WebElement dateInput = driver.findElement(By.xpath("//*[@id=\"exitdate\"]"));
		dateInput.click();
		dateInput.clear();
		dateInput.sendKeys(string);
		WebElement randomClick = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRandom = (JavascriptExecutor)driver;
		jsRandom.executeScript("arguments[0].click();", randomClick);
	}

	@When("user clicks on update button to view output")
	public void user_clicks_on_update_button_to_view_output() {
		WebElement randomClick = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRandom = (JavascriptExecutor)driver;
		jsRandom.executeScript("arguments[0].click();", randomClick);
		
		
		WebElement updateBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-set-exit-date/div[1]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", updateBtn);
	}

	@Then("The message {string} is displayed in the space below")
	public void the_message_is_displayed_in_the_space_below(String string) {
		try {
			Thread.sleep(5000);
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

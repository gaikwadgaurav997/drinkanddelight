package com.capgemini.dnd.displayRawMaterialBDD;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;

public class DisplayRawMaterialStepDefinition {

	public DisplayRawMaterialStepDefinition() {
		// TODO Auto-generated constructor stub
	}

	WebDriver driver;
	
	@Given("User is on drink and deligt login pages")
	public void user_is_on_drink_and_deligt_login_pages() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");

		// options.addArguments("disable-web-security");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\amolugur\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/");
	}

	@Given("User enters his\\/her login credentials")
	public void user_enters_his_her_login_credentials() {
		driver.findElement(By.name("username")).sendKeys("saurabh123");
		driver.findElement(By.name("password")).sendKeys("hello");
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"homepage\"]/div/div/div/div[2]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor) driver;
		jsButton.executeScript("arguments[0].click();", loginBtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
	}

	@Given("User selects Display all orders from RawMaterial dropdown")
	public void user_selects_Display_all_orders_from_RawMaterial_dropdown() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		WebElement rmBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/button"));
		JavascriptExecutor jsRMButton = (JavascriptExecutor)driver;
		jsRMButton.executeScript("arguments[0].click();", rmBtn);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}

	    
	    WebElement trackBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/div/a[2]"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", trackBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	}

	@Given("User is on display raw material order page")
	public void user_is_on_display_raw_material_order_page() {
		String currentUrl = driver.getCurrentUrl();
		 Assert.assertEquals("http://localhost:4200/display-rawmaterial-orders", currentUrl);
	}

	@When("User entered the Delivery Status as {string}")
	public void user_entered_the_Delivery_Status_as(String string) {
		Select drpDownDeliveryStatus = new Select(driver.findElement(By.xpath("/html/body/app-root/body/app-display-rawmaterial-orders/div/div[1]/div[1]/select[1]")));
	    drpDownDeliveryStatus.selectByVisibleText("ALL");
	}

	@When("User selects the SupplierId as {string}")
	public void user_selects_the_SupplierId_as(String string) {
		Select drpDownSupplier = new Select(driver.findElement(By.xpath("/html/body/app-root/body/app-display-rawmaterial-orders/div/div[1]/div[1]/select[2]")));
	    drpDownSupplier.selectByVisibleText("ALL");
	}

	@When("User enters start date as {string}")
	public void user_enters_start_date_as(String string) {

		WebElement calendarCheckbox = driver.findElement(By.xpath("/html/body/app-root/body/app-display-rawmaterial-orders/div/div[1]/div[2]/input[1]"));
		calendarCheckbox.click();
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			
		}
		

		WebElement startDateInput = driver.findElement(By.xpath("/html/body/app-root/body/app-display-rawmaterial-orders/div/div[2]/div/input[1]"));
		startDateInput.click();
		startDateInput.clear();
		startDateInput.sendKeys(string);
		
	    }
	    
	    @When("User enters end date as {string}")
		public void user_enters_end_date_as(String string) {
			WebElement endDateInput = driver.findElement(By.xpath("/html/body/app-root/body/app-display-rawmaterial-orders/div/div[2]/div/input[2]"));
			endDateInput.click();
			endDateInput.clear();
			endDateInput.sendKeys(string);
		   
	}
	    @When("User clicked on submit")
		public void User_clicked_on_submit() {
			WebElement submitbtn = driver.findElement(By.xpath("/html/body/app-root/body/app-display-rawmaterial-orders/div/div[3]/button"));
			JavascriptExecutor jsButton = (JavascriptExecutor)driver;
			jsButton.executeScript("arguments[0].submit();", submitbtn);
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				}
		}
	    
	@Then("{string} is displayed")
	public void is_displayed(String string) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			}
		
		WebElement outputElement = driver.findElement(By.name("displaybox"));
		
		String outputValue = outputElement.getText();
		
		Assert.assertEquals(string, outputValue);
	   
	}
	
//	@After
//	public void tearDown() {
//		driver.quit();
//	}


}

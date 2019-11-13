package com.capgemini.dnd.updateRawMaterialOrderBDD;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class UpdateRawMaterialOrderStepdefinition {
	String chromeDriverPath = "C:\\Users\\akum1031\\Downloads\\chromedriver.exe";
	WebDriver driver = null;

	@Given("I am on drinkanddelight login page")
	public void i_am_on_drinkanddelight_login_page() {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
	    driver.navigate().to(
				"http://localhost:4200/");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}

	}

	@When("I enter username as {string}")
	public void i_enter_username_as(String string) {
		driver.findElement(By.id("username")).sendKeys(string);
		
	}

	@When("I enter password as {string}")
	public void i_enter_password_as(String string) {
		driver.findElement(By.id("password")).sendKeys(string);
	}

	@When("clicks on the submit button")
	public void clicks_on_the_submit_button() {
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"homepage\"]/div/div/div/div[2]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", loginBtn);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		}
	}

	@When("I am on update Product order delivery status page")
	public void i_am_on_update_Product_order_delivery_status_page() {
		
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
		WebElement updateBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[2]/div/div/a[3]"));
		JavascriptExecutor jsRMupdateButton = (JavascriptExecutor)driver;
		jsRMupdateButton.executeScript("arguments[0].click();", updateBtn);
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
	}

	@When("I enter Product order id as {string}")
	public void i_enter_Product_order_id_as(String string) {
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			
		}
		driver.findElement(By.id("orderID")).sendKeys(string);
	}

	@When("I enter Delivery Status as {string}")
	public void i_enter_Delivery_Status_as(String string) {
		Select dropdown = new Select(driver.findElement(By.id("DeliveryStatus")));
		dropdown.selectByVisibleText("Pending");
	}

	@When("I click on Update Order button")
	public void i_click_on_button(String string) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			
		WebElement updateBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-update-rawmaterial-order/div[1]/button"));
		JavascriptExecutor jsRMupdateButton = (JavascriptExecutor)driver;
		jsRMupdateButton.executeScript("arguments[0].click();",updateBtn);
		
		
		}
		
	}
//	@After
//	public void close() {
//		System.out.println("Closing the driver.");
//		if (driver != null) {
//			driver.quit();
//		}
//	}

}

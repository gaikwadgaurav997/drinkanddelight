package com.capgemini.dnd.placeProductBDD;

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

@SuppressWarnings("deprecation")
public class PlaceProductStepDefinition {

	public PlaceProductStepDefinition() {

	}

	WebDriver driver;

	@Given("User is on drink and delight login pages")
	public void user_is_on_drink_and_delight_login_page() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gauragai\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/");
	}

	@Given("User enters login credentials")
	public void user_enters_login_credentials_given() {
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

	@Given("User selects Place Product Option from Product dropdown")
	public void user_selects_Place_Product_Option_from_Product_dropdown() {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		WebElement productBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRMButton = (JavascriptExecutor) driver;
		jsRMButton.executeScript("arguments[0].click();", productBtn);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		WebElement trackBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/div/a[1]"));
		JavascriptExecutor jsButton = (JavascriptExecutor) driver;
		jsButton.executeScript("arguments[0].click();", trackBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
	}

	@Given("User is on place product order page")
	public void user_is_on_place_product_order_page() {

		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://localhost:4200/place-product-order", currentUrl);
	}

	@When("User entered the Product Name as {string}")
	public void user_entered_the_Product_Name_as(String string) {
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

		}
		driver.findElement(By.name("ProductName")).sendKeys(string);
	}
	
	@When("User selects Distributor ID from Dropdown menu as {string}")
	public void user_selects_Distributor_ID_from_Dropdown_menu_as(String string) {
	    Select drpDownQualityStatus = new Select(driver.findElement(By.xpath("/html/body/app-root/body/app-place-product-order/div[1]/form/div/div[1]/div[2]/select")));
	    drpDownQualityStatus.selectByVisibleText(string);
	}
	
	@When("User selects Warehouse ID from Dropdown menu as {string}")
	public void user_selects_Warehouse_ID_from_Dropdown_menu_as(String string) {
	    Select drpDownQualityStatus = new Select(driver.findElement(By.xpath("/html/body/app-root/body/app-place-product-order/div[1]/form/div/div[1]/div[3]/select")));
	    drpDownQualityStatus.selectByVisibleText(string);
	}
	

	@When("User entered the Quantity as {string}")
	public void user_entered_the_Quantity_as(String string) {
		driver.findElement(By.name("quantity")).sendKeys(string);
	}
	

	@When("User selects Quantity Unit from Dropdown menu as {string}")
	public void user_selects_Quantity_Unit_from_Dropdown_menu_as(String string) {
	    Select drpDownQualityStatus = new Select(driver.findElement(By.xpath("/html/body/app-root/body/app-place-product-order/div[1]/form/div/div[2]/div[2]/select")));
	    drpDownQualityStatus.selectByVisibleText(string);
	}
	
	@When("User entered the Price per Unit as {string}")
	public void user_entered_the_Price_per_Unit_as(String string) {
		driver.findElement(By.name("pricePerUnit")).sendKeys(string);
	}
	
	@When("User gives Expected Date of Delivery as {string}")
	public void user_gives_Expected_Date_of_Delivery_as(String string) {
		WebElement dateInput = driver.findElement(By.xpath("//*[@id=\"expecteddateofdelivery\"]"));
		dateInput.click();
		dateInput.clear();
		dateInput.sendKeys(string);

	}
	
	@When("User clicked on place order button")
	public void user_clicked_on_place_order_button() {
		WebElement updateBtn = driver.findElement(By.xpath("/html/body/app-root/body/app-place-product-order/div[1]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor)driver;
		jsButton.executeScript("arguments[0].click();", updateBtn);
	}
	
	@Then("{string} is displayed below")
	public void is_displayed_below(String expectedString) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}

		WebElement outputElement = driver.findElement(By.name("outputbox"));
		String outputValue = outputElement.getText();
		Assert.assertEquals(expectedString,outputValue);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}

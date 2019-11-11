package com.capgemini.dnd.updateProductStockBDD;

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
public class UpdateProductStockStepDefinition {

	public UpdateProductStockStepDefinition() {

	}

	WebDriver driver;

	@Given("User is on DND homepage to login")
	public void user_is_on_DND_homepage_to_login() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");

		// options.addArguments("disable-web-security");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gauragai\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/");
	}

	@Given("User enters his login credentials given to him")
	public void user_enters_his_login_credentials_given_to_him() {
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

	@Given("User selects Update Stock Option from Product dropdown")
	public void user_selects_Update_Stock_Option_from_Product_dropdown() {
		WebElement productBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRMButton = (JavascriptExecutor) driver;
		jsRMButton.executeScript("arguments[0].click();", productBtn);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		WebElement updateBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/div/a[4]"));
		JavascriptExecutor jsButton = (JavascriptExecutor) driver;
		jsButton.executeScript("arguments[0].click();", updateBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
	}

	@Given("user is on update Product Stock page")
	public void user_is_on_update_Product_Stock_page() {

		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://localhost:4200/update-product-stock", currentUrl);
	}

	@When("User enters the order ID as {string}")
	public void user_enters_the_order_ID_as(String string) {
		driver.findElement(By.name("orderID")).sendKeys(string);
	}

	@When("User gives manufacturing Date as {string}")
	public void user_gives_manufacturing_Date_as(String string) {
		WebElement dateInput = driver.findElement(By.xpath("//*[@id=\"manufacturingdate\"]"));
		dateInput.click();
		dateInput.clear();
		dateInput.sendKeys(string);

	}

	@When("User gives expiry Date as {string}")
	public void user_gives_expiry_Date_as(String string) {
		WebElement dateInput = driver.findElement(By.xpath("//*[@id=\"expirydate\"]"));
		dateInput.click();
		dateInput.clear();
		dateInput.sendKeys(string);

	}

	@When("user selects Passed\\/Failed from Dropdown menu")
	public void user_selects_Passed_Failed_from_Dropdown_menu() {
		Select drpDownQualityStatus = new Select(driver.findElement(
				By.xpath("/html/body/app-root/body/app-update-product-stock/div[1]/form/div[4]/div/div[2]/select")));
		drpDownQualityStatus.selectByVisibleText("Passed");
	}

	@When("user clicks update stock button below")
	public void user_clicks_update_stock_button_below() {
		WebElement randomClick = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRandom = (JavascriptExecutor) driver;
		jsRandom.executeScript("arguments[0].click();", randomClick);

		WebElement updateBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-update-product-stock/div[1]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor) driver;
		jsButton.executeScript("arguments[0].click();", updateBtn);
	}

	@Then("The message {string} is shown")
	public void the_message_is_shown(String string) {
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}

		WebElement outputElement = driver.findElement(By.name("outputbox"));

		String outputValue = outputElement.getText();

		Assert.assertEquals(string, outputValue);
	}

	@After
	public void tearDown() {
		driver.quit();
	}

}

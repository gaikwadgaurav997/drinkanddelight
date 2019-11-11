package com.capgemini.dnd.trackProductBDD;

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
public class TrackProductStepDefinition {

	public TrackProductStepDefinition() {

	}

	WebDriver driver;

	@Given("User is on drink and delight login page")
	public void user_is_on_drink_and_delight_login_page() {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");

		// options.addArguments("disable-web-security");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\gauragai\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.get("http://localhost:4200/");
	}

	@Given("User enters login credentials given")
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

	@Given("User selects Track Product Option from Product dropdown")
	public void user_selects_Track_Product_Option_from_Product_dropdown() {
		WebElement productBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRMButton = (JavascriptExecutor) driver;
		jsRMButton.executeScript("arguments[0].click();", productBtn);

		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}

		WebElement trackBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/div/a[6]"));
		JavascriptExecutor jsButton = (JavascriptExecutor) driver;
		jsButton.executeScript("arguments[0].click();", trackBtn);
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {

		}
	}

	@Given("User is on track product order page")
	public void user_is_on_track_product_order_page() {

		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals("http://localhost:4200/track-product-order", currentUrl);
	}

	@When("User entered the order ID as {string}")
	public void user_entered_the_order_ID_as(String string) {
		driver.findElement(By.name("orderID")).sendKeys(string);
	}

	@When("user clicked on track order button")
	public void user_clicked_on_track_order_button() {
		WebElement randomClick = driver
				.findElement(By.xpath("/html/body/app-root/body/app-header/nav/div/ul/li[3]/div/button"));
		JavascriptExecutor jsRandom = (JavascriptExecutor) driver;
		jsRandom.executeScript("arguments[0].click();", randomClick);

		WebElement loginBtn = driver
				.findElement(By.xpath("/html/body/app-root/body/app-track-product-order/div[1]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor) driver;
		jsButton.executeScript("arguments[0].click();", loginBtn);
	}

	@Then("The shelf life of order Id {string} is displayed below")
	public void the_shelf_life_of_order_Id_is_displayed_below(String string) {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
		}

		WebElement outputElement = driver.findElement(By.name("outputbox"));

		String outputValue = outputElement.getText();

		Assert.assertEquals(
				"The order ID had been in the warehouse with warehouseID = w03 from 2019-09-01 05:30:00.0 to 2019-10-03 05:30:00.0(32 days)",
				outputValue);
	}

	@Then("A message {string} is displayed below")
	public void a_message_is_displayed_below(String string) {
		try {
			Thread.sleep(5000);
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

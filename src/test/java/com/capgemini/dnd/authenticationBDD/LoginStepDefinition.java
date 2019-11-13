package com.capgemini.dnd.authenticationBDD;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginStepDefinition {

	String chromeDriverPath = "C:\\Users\\akdeep\\Desktop\\chromedriver.exe";
	WebDriver driver = null;

	@Given("I am on drinkanddelight login page")
	public void i_am_on_drinkanddelight_login_page() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.navigate().to("http://localhost:4200/");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
		}
		System.out.println("Title: " + driver.getTitle());

	}

	@When("^I enter username as \"(.*)\"$")
	public void enterUsername(String arg1) {
		System.out.println(arg1);
		driver.findElement(By.id("username")).sendKeys(arg1);
	}

	@And("^I enter password as \"(.*)\"$")
	public void enterPassword(String arg1) {
		System.out.println(arg1);
		driver.findElement(By.id("password")).sendKeys(arg1);
	}

	@And("clicks on the submit button")
	public void clicks_on_the_submit_button() throws InterruptedException {
		WebElement loginBtn = driver.findElement(By.xpath("//*[@id=\"homepage\"]/div/div/div/div[2]/form/button"));
		JavascriptExecutor jsButton = (JavascriptExecutor) driver;
		jsButton.executeScript("arguments[0].click();", loginBtn);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
		}
	}

	@After
	public void close() {
		System.out.println("Closing the driver.");
		if (driver != null) {
			driver.quit();
		}
	}
}

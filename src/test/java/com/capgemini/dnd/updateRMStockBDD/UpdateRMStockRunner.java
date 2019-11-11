package com.capgemini.dnd.updateRMStockBDD;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class) 
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber"})
public class UpdateRMStockRunner {

	public UpdateRMStockRunner() {
		// TODO Auto-generated constructor stub
	}

}

package com.capgemini.dnd.updateExitDateBDD;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class) 
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber"})
public class UpdateExitDateRunner {

	public UpdateExitDateRunner() {
		// TODO Auto-generated constructor stub
	}

}

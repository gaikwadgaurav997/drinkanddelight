package com.capgemini.dnd.trackRawMaterialBDD;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class) 
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber"})
public class TrackRMRunner {

	public TrackRMRunner() {
		// TODO Auto-generated constructor stub
	}

}

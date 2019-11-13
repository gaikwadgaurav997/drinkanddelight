package com.capgemini.dnd.placeProductBDD;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class) 
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber"}
				
		)
public class PlaceProductRunner {

	public PlaceProductRunner() {
		
	}

}

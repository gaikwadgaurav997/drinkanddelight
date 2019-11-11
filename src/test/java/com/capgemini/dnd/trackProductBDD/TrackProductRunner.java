package com.capgemini.dnd.trackProductBDD;


import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class) 
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber"}
				
		)
public class TrackProductRunner {

	public TrackProductRunner() {
		
	}

}

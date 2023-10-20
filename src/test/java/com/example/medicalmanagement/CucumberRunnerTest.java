package com.example.medicalmanagement;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(plugin = "pretty", features = "src/test/resources", tags = "",
       glue = "com.example.medicalmanagement.stepDefinitions", publish = true)


public class CucumberRunnerTest {


}

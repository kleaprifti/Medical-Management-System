package com.example.medicalmanagement;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports/report.html",
        "json:target/cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
        features = "classpath:features",
        glue = {"com.example.medicalmanagement.stepDefinitions", "steps"}
)
public class CucumberRunnerTest {

}

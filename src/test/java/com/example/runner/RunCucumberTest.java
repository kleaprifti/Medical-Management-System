package com.example.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-reports"},
        monochrome = true,
        features = "src/test/java/features",
        glue = "com.example.stepDefinitions"
)
public class RunCucumberTest {
}

package com.example.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DemoStepDefinitions {


    @Given("a user with valid information")
    public void a_user_with_valid_information() {
        // Set up the userDto object with valid information.
        // Populate userDto with necessary information.
        // Add specialities if applicable (for doctors).
    }

    @When("the user adds the user")
    public void the_user_adds_the_user() {
        // Call the addUser method from UserService with userDto.

    }

    @Then("the user should be successfully added")
    public void the_user_should_be_successfully_added() {
       // Assert that the user was successfully added.
    }
}

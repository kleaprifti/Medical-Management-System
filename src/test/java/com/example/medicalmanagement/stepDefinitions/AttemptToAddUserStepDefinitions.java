package com.example.medicalmanagement.stepDefinitions;


import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.UserRepository;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class AttemptToAddUserStepDefinitions {

    @Autowired
    private UserRepository userRepository;

    private User existingUser;
    private boolean attemptedToAddUser;
    private String errorMessage;
    private User user;

    @Given("an existing user with the following information:")
    public void anExistingUserWithTheFollowingInformation(User user) {
        existingUser = userRepository.save(user);
    }

    @When("I attempt to add the user")
    public void iAttemptToAddTheUser() {
        try {
            userRepository.save(user);
            attemptedToAddUser = true;
        } catch (Exception e) {
            attemptedToAddUser = false;
            errorMessage = e.getMessage();
        }
    }

    @Then("the system should display an error message")
    public void theSystemShouldDisplayAnErrorMessage() {
        assert !attemptedToAddUser : "User should not be added successfully";
        assert errorMessage != null : "An error message should be displayed";
    }

    @DataTableType
    public User userEntry(Map<String, String> entry) {
        User user = new User();
        user.setFullName(entry.get("Full Name"));
        user.setBirthDate(LocalDate.parse(entry.get("Birth Date")));
        user.setIdMedicalCard(entry.get("ID Medical Card"));
        return user;
    }

    @After
    public void cleanUpData() {
        if (existingUser != null) {
            userRepository.delete(existingUser);
        }
        if (user != null) {
            userRepository.delete(user);
        }
    }
}
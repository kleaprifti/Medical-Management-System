//package com.example.medicalmanagement.stepDefinitions;
//
//
//import com.example.medicalmanagement.model.UserDetails;
//import com.example.medicalmanagement.repository.UserDetailsRepository;
//import io.cucumber.java.After;
//import io.cucumber.java.DataTableType;
//import io.cucumber.java.en.Given;
//import io.cucumber.java.en.Then;
//import io.cucumber.java.en.When;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.time.LocalDate;
//import java.util.Map;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@ContextConfiguration
//public class AttemptToAddUserStepDefinitions {
//
//    @Autowired
//    private UserDetailsRepository userDetailsRepository;
//
//    private UserDetails existingUser;
//    private boolean attemptedToAddUser;
//    private String errorMessage;
//    private UserDetails user;
//
//    @Given("an existing user with the following information:")
//    public void anExistingUserWithTheFollowingInformation(UserDetails user) {
//        existingUser = userDetailsRepository.save(user);
//    }
//
//    @When("I attempt to add the user")
//    public void iAttemptToAddTheUser() {
//        try {
//            userDetailsRepository.save(user);
//            attemptedToAddUser = true;
//        } catch (Exception e) {
//            attemptedToAddUser = false;
//            errorMessage = e.getMessage();
//        }
//    }
//
//    @Then("the system should display an error message")
//    public void theSystemShouldDisplayAnErrorMessage() {
//        assert !attemptedToAddUser : "User should not be added successfully";
//        assert errorMessage != null : "An error message should be displayed";
//    }
//
//    @DataTableType
//    public UserDetails userEntry(Map<String, String> entry) {
//        UserDetails user = new UserDetails();
//        user.setFullName(entry.get("Full Name"));
//        user.setBirthDate(LocalDate.parse(entry.get("Birth Date")));
//        user.setIdMedicalCard(entry.get("ID Medical Card"));
//        return user;
//    }
//
//    @After
//    public void cleanUpData() {
//        if (existingUser != null) {
//            userDetailsRepository.delete(existingUser);
//        }
//        if (user != null) {
//            userDetailsRepository.delete(user);
//        }
//    }
//}
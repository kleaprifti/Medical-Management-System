package com.example.medicalmanagement.stepDefinitions;


import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.UserRole;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.log4j.Log4j;
import net.thucydides.core.annotations.Step;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
@Log4j
@RunWith(SpringRunner.class)

public class UserRegistrationStepDefinitions {

    private UserDto userDto;
    private boolean registrationStatus;

    @Given("a user with the following information:")
    public void a_user_with_the_following_information(DataTable dataTable) {
        List<Map<String, String>> userData = dataTable.asMaps(String.class, String.class);

        if (!userData.isEmpty()) {
            Map<String, String> userRow = userData.get(0);

            userDto = new UserDto();
            userDto.setRoles(new ArrayList<>());
            userDto.setFullName(userRow.get("Full Name"));
            userDto.setBirthDate(LocalDate.parse(userRow.get("Birth Date")));
            userDto.setIdMedicalCard(userRow.get("ID Medical Card"));
            String[] roleStrings = userRow.get("Roles").split(", ");

            List<UserRole> userRoles = Arrays.stream(roleStrings)
                    .map(UserRole::valueOf)
                    .toList();
        } else {
            throw new IllegalArgumentException("User information not found in the DataTable.");
        }

    }

    @When("the user is added")
    public void the_user_is_added() {

        userDto.setFullName("John Doe");
        userDto.setBirthDate(LocalDate.of(1990, 1, 1));
        userDto.setIdMedicalCard("12345");
        userDto.setRoles(Arrays.asList(UserRole.DOCTOR, UserRole.PATIENT));
        userDto.setSpecialities(Arrays.asList("Surgery", "Pediatrics"));
    }

    @Then("the user should be successfully registered")
    public void the_user_should_be_successfully_registered() {
        System.out.println("Registration Status: " + registrationStatus);
    }
}
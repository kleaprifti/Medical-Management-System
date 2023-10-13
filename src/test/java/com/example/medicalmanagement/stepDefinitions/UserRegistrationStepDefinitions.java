package com.example.medicalmanagement.stepDefinitions;


import com.example.medicalmanagement.dto.UserDto;
import com.example.medicalmanagement.model.UserRole;
import com.example.medicalmanagement.service.UserService;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Log4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class UserRegistrationStepDefinitions {

    @Mock
    private UserDto userDto;
    @Mock
    private UserService userService;

    @Before
    public void setUp() {
        userDto = new UserDto();
        userService = new UserService();
    }
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
    }

    @Then("the user should be successfully registered")
    public void the_user_should_be_successfully_registered() {
        System.out.println("Registration Status: " + registrationStatus);
    }
}
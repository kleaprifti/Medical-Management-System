package com.example.medicalmanagement.stepDefinitions;

import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.UserRepository;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration



public class MultipleUsersRegistrationValidationStepDefinitions {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    private List<User> usersToAdd;

    private WireMockServer wireMockServer;
    @Autowired
    private EntityManager entityManager;

    @Before
    public void setupWireMockServer() {
        wireMockServer = new WireMockServer(options().port(8080)); // You can use any available port
        wireMockServer.start();
        configureWireMock(wireMockServer);
    }

    private WireMockConfiguration options() {
        return WireMockConfiguration.options(); // This creates a new WireMockConfiguration instance
    }

    private void configureWireMock(WireMockServer wireMockServer) {
        // Configure WireMock stubs here for expected HTTP requests and responses
        wireMockServer.stubFor(post(urlEqualTo("/add"))
                .withRequestBody(matchingJsonPath("$.fullName", equalTo("John Doe")))
                .withRequestBody(matchingJsonPath("$.birthDate", matchingDate("yyyy-MM-dd", "1990-01-01")))
                .withRequestBody(matchingJsonPath("$.idMedicalCard", equalTo("3238294157862056")))
                .willReturn(aResponse().withStatus(200)));
    }

    private StringValuePattern matchingDate(String s, String date) {
        return null;
    }

    @After
    public void shutdownWireMockServer() {
        wireMockServer.stop();
    }


    @Given("a user with the following information:")
    public void aUserWithTheFollowingInformation(List<User> users) {
        usersToAdd = users;
    }

    @Transactional
    @When("the users are added")

    public void theUsersAreAdded() {
        if (usersToAdd != null) {
            System.out.println("Before saving: " + userRepository.findAll()); // Print existing users before saving
            System.out.println("Users to add: " + usersToAdd);
            for (User user : usersToAdd) {

                System.out.println("Saving user to the database: " + user);

                try {

                    userRepository.save(user);

                    System.out.println("User saved successfully: " + user);
                } catch (Exception e) {
                    // Log any exceptions that occur during the save operation
                    System.err.println("Error saving user: " + user);
                    e.printStackTrace();
                }
            }

            System.out.println("After saving: " + userRepository.findAll()); // Print updated users after saving
        }
    }

    @Then("the following users should be present in the database:")
    public void theFollowingUsersShouldBePresentInDatabase(List<User> expectedUsers) {
        List<User> actualUsers = queryDatabase();


        if (actualUsers != null) {

            for (User expectedUser : expectedUsers) {
                assertUserExistsInDatabase(expectedUser, actualUsers);
            }
        } else {

            System.err.println("Error: Actual users list is null.");
        }
    }

    private void assertUserExistsInDatabase(User expectedUser, List<User> actualUsers) {
        assertTrue("User not found in the database", actualUsers.contains(expectedUser));
    }

    private boolean areUsersEqual(User user1, User user2) {
        if (user1 == null || user2 == null) {
            return false;
        }
        return true;
    }

    private List<User> queryDatabase() {
        List<User> users = new ArrayList<>();

        try {
            // Use EntityManager to query the database
            Query query = entityManager.createQuery("SELECT u FROM User u");
            users = query.getResultList();

            System.out.println("Users retrieved from the database: " + users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
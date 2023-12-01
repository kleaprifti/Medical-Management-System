package com.example.medicalmanagement.stepDefinitions;

import com.example.medicalmanagement.model.UserDetails;
import com.example.medicalmanagement.repository.UserDetailsRepository;
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
    private UserDetailsRepository userDetailsRepository;

    private List<UserDetails> usersToAdd;

    @Autowired
    private EntityManager entityManager;


    private boolean wireMockEnabled;

    private WireMockServer wireMockServer;

    @Before
    public void setupWireMockServer() {
        if (wireMockEnabled) {
            wireMockServer = new WireMockServer(options().port(8080));
            wireMockServer.start();
            configureWireMock(wireMockServer);
        }
    }

    private WireMockConfiguration options() {
        return WireMockConfiguration.options();
    }

    private void configureWireMock(WireMockServer wireMockServer) {
        wireMockServer.stubFor(post(urlEqualTo("/add"))
                .withRequestBody(matchingJsonPath("$.fullName", equalTo("John Doe")))
                .withRequestBody(matchingJsonPath("$.birthDate", matchingDate("yyyy-MM-dd", "1990-01-01")))
                .withRequestBody(matchingJsonPath("$.idMedicalCard", equalTo("3238294157862056")))
                .willReturn(aResponse().withStatus(200)));
    }

    private StringValuePattern matchingDate(String s, String date) {
        return null;
    }

    // Other methods...

    @After
    public void shutdownWireMockServer() {
        if (wireMockEnabled && wireMockServer != null) {
            wireMockServer.stop();
        }
    }

    @After
    public void cleanUpData() {
        if (usersToAdd != null) {
            for (UserDetails user : usersToAdd) {
                userDetailsRepository.delete(user);
            }
        }
    }




    @Given("a user with the following information:")
    public void aUserWithTheFollowingInformation(List<UserDetails> users) {
        usersToAdd = users;
    }

    @Transactional
    @When("the users are added")

    public void theUsersAreAdded() {
        if (usersToAdd != null) {
            System.out.println("Before saving: " + userDetailsRepository.findAll()); // Print existing users before saving
            System.out.println("Users to add: " + usersToAdd);
            for (UserDetails user : usersToAdd) {

                System.out.println("Saving user to the database: " + user);

                try {

                    userDetailsRepository.save(user);

                    System.out.println("User saved successfully: " + user);
                } catch (Exception e) {
                    System.err.println("Error saving user: " + user);
                    e.printStackTrace();
                }
            }

            System.out.println("After saving: " + userDetailsRepository.findAll());
        }
    }

    @Then("the following users should be present in the database:")
    public void theFollowingUsersShouldBePresentInDatabase(List<UserDetails> expectedUsers) {
        List<UserDetails> actualUsers = queryDatabase();


        if (actualUsers != null) {

            for (UserDetails expectedUser : expectedUsers) {
                assertUserExistsInDatabase(expectedUser, actualUsers);
            }
        } else {

            System.err.println("Error: Actual users list is null.");
        }
    }

    private void assertUserExistsInDatabase(UserDetails expectedUser, List<UserDetails> actualUsers) {
        assertTrue("User not found in the database", actualUsers.contains(expectedUser));
    }

    private boolean areUsersEqual(UserDetails user1, UserDetails user2) {
        if (user1 == null || user2 == null) {
            return false;
        }
        return true;
    }

    private List<UserDetails> queryDatabase() {
        List<UserDetails> users = new ArrayList<>();

        try {

            Query query = entityManager.createQuery("SELECT u FROM UserDetails u");
            users = query.getResultList();

            System.out.println("Users retrieved from the database: " + users);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return users;
    }
}
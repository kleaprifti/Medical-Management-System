package com.example.medicalmanagement.stepDefinitions;

import com.example.medicalmanagement.model.User;
import com.example.medicalmanagement.repository.UserRepository;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class MultipleUsersRegistrationValidationStepDefinitions {
    @Autowired
    private UserRepository userRepository;

    private List<User> usersToAdd;
    private List<User> existingUsers;

    @Given("a user with the following information:")
    public void aUserWithTheFollowingInformation(List<User> users) {
        usersToAdd = users;
        for (User user : usersToAdd) {
            userRepository.save(user);
        }
    }

    @When("the users are added")
    public void theUsersAreAdded() {
        if (usersToAdd != null) {
            for (User user : usersToAdd) {
                userRepository.save(user);
            }
        }
    }

    @Then("the following users should be present in the database:")
    public void theFollowingUsersShouldBePresentInDatabase(List<User> expectedUsers) {
        existingUsers = userRepository.findAll();

        for (User expectedUser : expectedUsers) {
            assertUserExistsInDatabase(expectedUser);
        }
    }

    private void assertUserExistsInDatabase(User expectedUser) {
        User matchingUser = existingUsers.stream()
                .filter(u -> areUsersEqual(u, expectedUser))
                .findFirst()
                .orElse(null);

        if (matchingUser == null) {
            throw new AssertionError("User not found in the database: " + expectedUser);
        }
    }

    private boolean areUsersEqual(User user1, User user2) {
        return user1.getFullName().equals(user2.getFullName()) &&
                user1.getBirthDate().isEqual(user2.getBirthDate()) &&
                user1.getIdMedicalCard().equals(user2.getIdMedicalCard());
    }

  @After
  public void cleanUpData() {
      if (usersToAdd != null) {
          for (User user : usersToAdd) {
              userRepository.delete(user);
          }
      }
  }
}
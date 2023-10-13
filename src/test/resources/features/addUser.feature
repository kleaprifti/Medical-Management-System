Feature: User Registration

  Scenario: Adding a User
    Given a user with the following information:
      | Full Name        | Birth Date | ID Medical Card | Roles        | Specialities |
      | John Doe         | 1990-01-01 | 12345          | DOCTOR, PATIENT | Surgery, Pediatrics |
    When the user is added
    Then the user should be successfully registered

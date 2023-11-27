Feature: User Registration

  Scenario Outline: Attempt to add an existing user
    Given an existing user with the following information:
      | Full Name   | Birth Date   | ID Medical Card   | Roles   | Specialities   |
      | <Full Name> | <Birth Date> | <ID Medical Card> | <Roles> | <Specialities> |
    When I attempt to add the user
    Then the system should display an error message

    Examples:
      | Full Name  | Birth Date | ID Medical Card  | Roles  | Specialities      |
      | John Doe   | 1990-01-01 | 3168294157862043 | DOCTOR | Surgery           |
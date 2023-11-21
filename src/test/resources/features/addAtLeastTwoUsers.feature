Feature: User Registration

  Scenario Template: Adding users to the database
    Given a user with the following information:
      | Full Name   | Birth Date   | ID Medical Card   | Roles   | Specialities   |
      | <Full Name> | <Birth Date> | <ID Medical Card> | <Roles> | <Specialities> |

    When the users are added

    Then the following users should be present in the database:
      | Full Name   | Birth Date   | ID Medical Card   | Roles   | Specialities   |
      | <Full Name> | <Birth Date> | <ID Medical Card> | <Roles> | <Specialities> |

    Examples:
      | Full Name   | Birth Date | ID Medical Card  | Roles           | Specialities        |
      | John Doe    | 1990-01-01 | 3238294157862007 | DOCTOR, PATIENT | Surgery, Pediatrics |



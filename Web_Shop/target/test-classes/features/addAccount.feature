Feature: Account Creation

  Scenario: Sucessfull Account Creation
    Given the user is on the registration page
    When the user registers with valid details
    Then the user account is created
    

  Scenario: Account Creation Failure with existing email
    Given the user is on the registration page
    When the user registers with an existing email
    Then the registration should fail with an appropriate error message

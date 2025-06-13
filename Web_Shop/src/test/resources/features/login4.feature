Feature: Login

  Scenario Outline: Sucessfully Logged In
    Given the user is on the login page
    When the user enters email as "<email>" and password as "<password>" and click on the login button
    Then the user validates the text message

    Examples: 
      | email                 | password  |
      | jeeva344323@gmail.com | Qwert@123 |

  Scenario Outline: Login with invalid details
    Given the user is on the login page
    When the user enters email as "<email>" and password as "<password>" and click on the login button
    Then the user validates the error message

    Examples: 
      | email             | password  |
      | example@gmail.com | example12 |

  
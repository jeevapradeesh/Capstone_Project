Feature: CRUD operation

  Scenario: GET user by ID
    Given I set the base URI to "https://reqres.in/api"
    When I sent a GET request to endpoint "/users/5"
    Then the response status code should be 200

  Scenario: POST method to add a new user
    Given I set the base URI to "https://reqres.in/api"
    When I send a POST request to endpoint "/users" with body:
      """
      {
      	 "id": 5,
              "email": "jeeva23@gmail.com",
              "first_name": "Jeeva Pradeesh",
              "last_name": "P"
      
      }
      """
    Then the response status code should be 201

  Scenario: PUT method to update a user
    Given I set the base URI to "https://reqres.in/api"
    When I send a PUT request to endpoint "/users/5" with body:
      """
      {
       "email": "jeeva23@gmail.com",
              "first_name": "Jeeva",
              "last_name": "Pradeesh"
      
      }
      """
    Then the response status code should be 200

  Scenario: DELETE a user
    Given I set the base URI to "https://reqres.in/api"
    When I send a DELETE request to endpoint "/users/5"
    Then the response status code should be 204

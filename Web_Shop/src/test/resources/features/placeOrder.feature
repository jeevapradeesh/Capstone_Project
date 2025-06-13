Feature: Select Product

Scenario: Selecting a product 
Given the user is on the login page
When the user enters email as "<email>" and password as "<password>" and click on the login button
Then the user validates the text message
When the user select the product "Fiction"
And the user add the product to the cart and click on add to cart button
Then the product is visible in the cart
Then the user proceeds the checkout
Then the user completes the billing process
Then user verifies the order confirmation
And the user click on logout
 Then the user validate text message

Examples: 
|email|password|
|jeeva344323@gmail.com|Qwert@123|


Scenario: Selecting a non-existent product
  Given the user is on the login page
  When the user enters email as "<email>" and password as "<password>" and click on the login button
  Then the user validates the text message
	Then the product "Headphone" should not be found
  And the user click on logout
  Then the user validate text message

Examples: 
|email|password|
|jeeva344323@gmail.com|Qwert@123|
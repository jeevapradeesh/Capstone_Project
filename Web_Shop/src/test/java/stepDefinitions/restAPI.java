package stepDefinitions;

import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class restAPI {
	   private RequestSpecification request;
	    private Response response;


	@Given("I set the base URI to {string}")
	public void i_set_the_base_uri_to(String base) {
		RestAssured.baseURI = base;
        request = RestAssured
            .given()
            .header("x-api-key", "reqres-free-v1") 
            .header("Content-Type", "application/json");

	}

	@When("I sent a GET request to endpoint {string}")
	public void i_sent_a_get_request_to_endpoint(String endpoint) {
		 response = request.when().get(endpoint);

	}

	@Then("the response status code should be {int}")
	
		 public void the_response_status_code_should_be(Integer expectedStatusCode) {
		        int actualStatusCode = response.getStatusCode();
		        System.out.println("Status Code: " + actualStatusCode);
		        System.out.println("Response Body:\n" + response.getBody().asPrettyString());
		        assertEquals(expectedStatusCode.intValue(), actualStatusCode);

	}

	@When("I send a POST request to endpoint {string} with body:")
	public void i_send_a_post_request_to_endpoint_with_body(String endpoint, String body) {
		 response = request
		            .body(body)
		            .when()
		            .post(endpoint);

	}

	@When("I send a PUT request to endpoint {string} with body:")
	public void i_send_a_put_request_to_endpoint_with_body(String endpoint, String body) {
		 response = request
		            .body(body)
		            .when()
		            .put(endpoint);


	}

	@When("I send a DELETE request to endpoint {string}")
	public void i_send_a_delete_request_to_endpoint(String endpoint) {
		response = request.when().delete(endpoint);
	}

}

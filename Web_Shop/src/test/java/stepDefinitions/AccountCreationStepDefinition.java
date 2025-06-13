package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import objectrepository.Locators;
import pages.AccountCreation;
import pages.LoginPage;

public class AccountCreationStepDefinition {
	WebDriver driver = Hooks.driver;
	WebDriverWait wait;
	ExtentTest test = Hooks.test;
	AccountCreation accountCreation;
	LoginPage loginPage;

	@Given("the user is on the registration page")
	public void the_user_is_on_the_registration_page() {
		accountCreation = new AccountCreation(driver, test);
		accountCreation.navigateToRegistrationPage();
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://demowebshop.tricentis.com/register", actUrl);
	}

	@When("the user registers with valid details")
	public void the_user_registers_with_valid_details() {
		accountCreation = new AccountCreation(driver, test);
		accountCreation.newAccountCreation("male", "jeeva", "pradeesh", "Test@123");
	}

	@Then("the user account is created")
	public void the_user_account_is_created() {
		accountCreation = new AccountCreation(driver, test);
		String actualMessage = accountCreation.getSuccessMessage();
		Assert.assertTrue("Your registration completed", actualMessage.contains("Your registration completed"));
	}


	@When("the user registers with an existing email")
	public void the_user_registers_with_an_existing_email() {
	    accountCreation = new AccountCreation(driver,test);
	    accountCreation.failedAccountCreation("male", "pradeesh", "p", "jeeva344323@gmail.com", "Password", "Password");
	}

	@Then("the registration should fail with an appropriate error message")
	public void the_registration_should_fail_with_an_appropriate_error_message() {
	    String errorMessage = accountCreation.getFailureMessage();
	    Assert.assertTrue("Expected error message not displayed", errorMessage.contains("The specified email already exists"));
	}
}

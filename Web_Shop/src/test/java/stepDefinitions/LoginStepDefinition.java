package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LogOutPage;
import pages.LoginPage;

public class LoginStepDefinition {
	WebDriver driver = Hooks.driver;
	ExtentTest test = Hooks.test;
	LoginPage loginPage;
	LogOutPage logoutPage;

	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() {
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://demowebshop.tricentis.com/", actUrl);
	}

	@When("the user enters email as {string} and password as {string} and click on the login button")
	public void the_user_enters_email_as_and_password_as_and_click_on_the_login_button(String email, String password) {
		loginPage = new LoginPage(driver, test);
		loginPage.validateLogin(email, password);
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://demowebshop.tricentis.com/", actUrl);
	}

	@Then("the user validates the text message")
	public void the_user_validates_the_text_message() {
		boolean isValidation = loginPage.isLogoutVisible();
		Assert.assertTrue(isValidation, "'Log out' link should be visible");
	}

	@Then("the user validates the error message")
	public void the_user_validates_the_error_message() {
		String expectedErrorMessage = "Login was unsuccessful. Please correct the errors and try again.";
		boolean isErrorDisplayed =loginPage.isLoginErrorDisplayed(expectedErrorMessage);
		Assert.assertTrue(isErrorDisplayed, "Error expected not displayed");
	}

}

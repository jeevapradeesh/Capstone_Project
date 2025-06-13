package pages;

import java.time.Duration;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import objectrepository.Locators;
import utils.Base;
import utils.Reporter;

public class AccountCreation {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public AccountCreation(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

	public void navigateToRegistrationPage() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.registerLink)).click();
			Reporter.generateReport(driver, test, Status.PASS, "Navigated to registration page.");
		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL,
					"Navigation to registration page failed: " + e.getMessage());
		}
	}

	public String newAccountCreation(String gender, String firstName, String lastName, String password) {
		String email = RandomStringUtils.randomAlphabetic(8).toLowerCase() + "@gmail.com";

		try {
			if (gender.equalsIgnoreCase("male")) {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.genderMale)).click();
				Reporter.generateReport(driver, test, Status.PASS, "Selected gender: Male");
			} else {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.genderFemale)).click();
				Reporter.generateReport(driver, test, Status.PASS, "Selected gender: Female");
			}
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.firstName)).sendKeys(firstName);
			Reporter.generateReport(driver, test, Status.PASS, "Entered First Name: " + firstName);

			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lastName)).sendKeys(lastName);
			Reporter.generateReport(driver, test, Status.PASS, "Entered Last Name: " + lastName);

			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.registerEmail)).sendKeys(email);
			Reporter.generateReport(driver, test, Status.PASS, "Entered Email: " + email);

			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.registerPassword)).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.confirmPassword)).sendKeys(password);
			Reporter.generateReport(driver, test, Status.PASS, "Entered and confirmed password");

			wait.until(ExpectedConditions.elementToBeClickable(Locators.registerButton)).click();
			Reporter.generateReport(driver, test, Status.PASS, "Clicked Register button");

			String successMessage = wait
					.until(ExpectedConditions.visibilityOfElementLocated(Locators.registrationSuccessMessage))
					.getText();
			System.out.println("Registration Message: " + successMessage);

			if (successMessage.equalsIgnoreCase("Your registration completed")) {
				Reporter.generateReport(driver, test, Status.PASS, "User registered successfully with email: " + email);
				return email;
			} else {
				Reporter.generateReport(driver, test, Status.FAIL,
						"Unexpected registration message: " + successMessage);
			}

		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL, "Error during registration: " + e.getMessage());
		}

		return null;

	}

	public String getSuccessMessage() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.registrationSuccessMessage))
					.getText();
		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL, "Unable to fetch success message: " + e.getMessage());
			return null;
		}
	}
	public void failedAccountCreation(String gender, String firstName, String lastName, String email, String password, String confirmPassword) {
		try {
			if (gender.equalsIgnoreCase("male")) {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.genderMale)).click();
				Reporter.generateReport(driver, test, Status.PASS, "Selected gender: Male");
			} else {
				wait.until(ExpectedConditions.elementToBeClickable(Locators.genderFemale)).click();
				Reporter.generateReport(driver, test, Status.PASS, "Selected gender: Female");
			}

			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.firstName)).sendKeys(firstName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.lastName)).sendKeys(lastName);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.registerEmail)).sendKeys(email);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.registerPassword)).sendKeys(password);
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.confirmPassword)).sendKeys(confirmPassword);
			wait.until(ExpectedConditions.elementToBeClickable(Locators.registerButton)).click();

			// Wait for and handle error message
			if (driver.findElements(Locators.registerErrorMessage).size() > 0) {
				String errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.registerErrorMessage)).getText();
				Reporter.generateReport(driver, test, Status.FAIL, "Account creation failed: " + errorMsg);
				Assert.fail("Account creation failed with error: " + errorMsg);
			} else {
				Reporter.generateReport(driver, test, Status.FAIL, "Error message not displayed - possible unexpected behavior.");
				Assert.fail("Account creation failed but error message not found.");
			}

		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL, "Exception in registration failure flow: " + e.getMessage());
			Assert.fail("Test failed due to exception: " + e.getMessage());
		}
	}




	public String getFailureMessage() {
		try {
			return wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.registerErrorMessage)).getText();
		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL, "Failed to fetch registration error: " + e.getMessage());
			return null;
		}
	}

}

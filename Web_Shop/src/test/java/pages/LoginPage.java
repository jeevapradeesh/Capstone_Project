package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.Reporter;

public class LoginPage {
	WebDriver driver;
	ExtentTest test;
	WebDriverWait wait;

	public LoginPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

	public void validateLogin(String email, String password) {
		  wait.until(ExpectedConditions.elementToBeClickable(Locators.loginLink)).click();
		    wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.emailField)).sendKeys(email);
		    wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.passwordField)).sendKeys(password);
		    wait.until(ExpectedConditions.elementToBeClickable(Locators.loginButton)).click();

		if (driver.findElements(Locators.logoutLink).size() > 0) {
			Reporter.generateReport(driver, test, Status.PASS, "Login Successful");
		} else {
			Reporter.generateReport(driver, test, Status.FAIL, "Login Failed");
		}
	}
	  public void navigateToLoginPage() {
	        wait.until(ExpectedConditions.elementToBeClickable(Locators.loginLink)).click();
	    }
	  public boolean isLogoutVisible() {
		    try {
		        boolean isvisible = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.logoutLink)).isDisplayed();
		        if (isvisible) {
		            Reporter.generateReport(driver, test, Status.PASS, "'Log out' link is visible.");
		        } else {
		            Reporter.generateReport(driver, test, Status.FAIL, "'Log out' link is not visible.");
		        }
		        return isvisible;
		    } catch (Exception e) {
		        Reporter.generateReport(driver, test, Status.FAIL, "Exception while checking 'Log out': " + e.getMessage());
		        return false;
		    }
		}
	  public boolean isLoginErrorDisplayed(String expectedMessage) {
		    try {
		        String actualMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.loginError)).getText();
		        boolean isErrorMessage = actualMessage.contains(expectedMessage);
		        
		        if (isErrorMessage) {
		            Reporter.generateReport(driver, test, Status.PASS, "Error message matched: " + actualMessage);
		        } else {
		            Reporter.generateReport(driver, test, Status.FAIL, "Expected error: '" + expectedMessage + "', but found: '" + actualMessage + "'");
		        }

		        return isErrorMessage;
		    } catch (Exception e) {
		        Reporter.generateReport(driver, test, Status.FAIL, "Error message not found: " + e.getMessage());
		        return false;
		    }
		}


}

package pages;

import java.time.Duration;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.Base;
import utils.Reporter;

public class CheckOutPage {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public CheckOutPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		this.test = test;
	}

	public void completeCheckoutSteps() {
		wait.until(ExpectedConditions.elementToBeClickable(Locators.billingContinue)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.shippingContinue)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.shippingMethodContinue)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.paymentMethodContinue)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.paymentInfoContinue)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.confirmOrderButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.completeCheckout)).click();
	}

	public String verifyConfirmationOrder() {
		try {
			WebElement confirmationElement = wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.confirmationMessage));
			String confirmationMsg = confirmationElement.getText();

			if (confirmationMsg.contains("Your order has been successfully processed!")) {
				Reporter.generateReport(driver, test, Status.PASS, "Order confirmed successfully: " + confirmationMsg);
			} else {
				Reporter.generateReport(driver, test, Status.FAIL, "Confirmation message mismatch: " + confirmationMsg);
			}

			return confirmationMsg;

		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL,
					"Failed to verify order confirmation: " + e.getMessage());
			throw e;
		}
	}

}

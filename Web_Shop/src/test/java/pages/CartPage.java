package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.Base;
import utils.Reporter;

public class CartPage {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public CartPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

	public void verifyProductInCart() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.cartLink)).click();

			boolean isProductVisible = driver.findElements(Locators.productInCart).size() > 0;
			if (isProductVisible) {
				Reporter.generateReport(driver, test, Status.PASS, "Product is visible in the cart");
			} else {
				Reporter.generateReport(driver, test, Status.FAIL, "Product is NOT visible in the cart");
			}
		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL, "Error verifying cart contents: " + e.getMessage());
		}
	}

	public void proceedToCheckout() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.termsCheckbox)).click();
			wait.until(ExpectedConditions.elementToBeClickable(Locators.checkoutButton)).click();

			Reporter.generateReport(driver, test, Status.PASS, "Proceeded to checkout");
		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL, "Error during checkout: " + e.getMessage());
		}
	}

	public void goToCartAndCheckout() {
		wait.until(ExpectedConditions.elementToBeClickable(Locators.cartLink)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.termsCheckbox)).click();
		wait.until(ExpectedConditions.elementToBeClickable(Locators.checkoutButton)).click();
	}
}

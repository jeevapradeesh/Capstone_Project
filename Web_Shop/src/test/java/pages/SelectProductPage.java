package pages;

import java.time.Duration;

import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;
import utils.Base;
import utils.Reporter;

public class SelectProductPage {
	WebDriver driver;
	WebDriverWait wait;
	ExtentTest test;

	public SelectProductPage(WebDriver driver, ExtentTest test) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		this.test = test;
	}

	public void searchAndSelectProduct(String productName) {
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(Locators.searchBox)).sendKeys(productName);

			wait.until(ExpectedConditions.elementToBeClickable(Locators.searchButton)).click();

			wait.until(ExpectedConditions.elementToBeClickable(By.linkText(productName))).click();
			Reporter.generateReport(driver, test, Status.PASS, "Product found and selected");
		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL, "Failed to search/select product: " + e.getMessage());
		}
	}

	public void addSearchedProductToCart() {
		try {
			wait.until(ExpectedConditions.elementToBeClickable(Locators.addToCartButton)).click();
			
			String message = driver.findElement(Locators.cartSuccessMessage).getText();
			if (message.contains("The product has been added")) {
				Reporter.generateReport(driver, test, Status.PASS, "Product added to cart successfully");
			} else {
				Reporter.generateReport(driver, test, Status.FAIL, "Add to cart message not found");
			}

		} catch (Exception e) {
			Reporter.generateReport(driver, test, Status.FAIL,
					"Failed to add searched product to cart: " + e.getMessage());
		}
	}

	public boolean verifyProductNotFound(String product) {
	    try {
	        wait.until(ExpectedConditions.elementToBeClickable(Locators.searchBox)).clear();
	        driver.findElement(Locators.searchBox).sendKeys(product);
	        wait.until(ExpectedConditions.elementToBeClickable(Locators.searchButton)).click();

	        boolean notFound = driver.findElements(By.linkText(product)).isEmpty();

	        if (notFound) {
	            Reporter.generateReport(driver, test, Status.FAIL, "Product '" + product + "' not found as expected.");
	        } else {
	            Reporter.generateReport(driver, test, Status.FAIL, "Product '" + product + "' was unexpectedly found.");
	        }

	        return false;
	    } catch (Exception e) {
	        Reporter.generateReport(driver, test, Status.FAIL, "Error occurred while verifying product: " + e.getMessage());
	        return false;
	    }
	}




}

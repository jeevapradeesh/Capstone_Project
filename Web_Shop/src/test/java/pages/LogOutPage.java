package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Reporter;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import objectrepository.Locators;

public class LogOutPage {
WebDriver driver;
WebDriverWait wait;
ExtentTest test;
public LogOutPage(WebDriver driver, ExtentTest test) {
    this.driver = driver;
    this.wait = new WebDriverWait(driver, Duration.ofSeconds(18));
    this.test = test;
}

public void logoutUser() {
    try {
        if (driver.findElements(Locators.logoutLink).size() > 0) {
            wait.until(ExpectedConditions.elementToBeClickable(Locators.logoutLink)).click();
            Reporter.generateReport(driver, test, Status.PASS, "User successfully logged out.");
        } else {
            Reporter.generateReport(driver, test, Status.FAIL, "Logout link not found — user might not be logged in.");
        }
    } catch (Exception e) {
        Reporter.generateReport(driver, test, Status.FAIL, "Logout failed: " + e.getMessage());
    }
}
}

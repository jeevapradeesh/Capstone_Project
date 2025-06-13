package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import pages.CartPage;
import pages.CheckOutPage;
import pages.SelectProductPage;
import pages.LoginPage;
import pages.AccountCreation;
//import pages.SelectProductPage;
import utils.PropertyReader;
import utils.Base;

public class ProductTest extends Base{
	ExtentReports extReports;
	ExtentTest test;

	@BeforeClass
	public void setUpReports() {
		ExtentSparkReporter spark = new ExtentSparkReporter("reports\\ExtentReport.html");
		extReports = new ExtentReports();
		extReports.attachReporter(spark);
	}

	@BeforeMethod
	public void setUp() {
		launchBrowser();
	}

	@Test
	public void testEcommerceFlow() {
		test = extReports.createTest("DemoWebShop Full Order Flow");
		
		AccountCreation accountPage = new AccountCreation(driver, test);
		accountPage.navigateToRegistrationPage();

		LoginPage loginPage = new LoginPage(driver, test);
		loginPage.validateLogin("jeeva344323@gmail.com", "Qwert@123");

		SelectProductPage homePage = new SelectProductPage(driver, test);
		homePage.addSearchedProductToCart();

		CartPage cartPage = new CartPage(driver, test);
		cartPage.goToCartAndCheckout();

		CheckOutPage checkOutPage = new CheckOutPage(driver,test);
		checkOutPage.completeCheckoutSteps();
	}

	@AfterMethod
	public void tearDown() {
		sleep(3000);
		driver.quit();
	}

	@AfterClass
	public void flushReports() {
		extReports.flush();
	}
 

}

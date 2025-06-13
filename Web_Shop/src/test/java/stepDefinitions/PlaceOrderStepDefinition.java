package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import pages.CheckOutPage;
import pages.LogOutPage;
import pages.SelectProductPage;

public class PlaceOrderStepDefinition {
	WebDriver driver = Hooks.driver;
	
	ExtentTest test = Hooks.test;
	SelectProductPage selectProductPage;
	CartPage cartPage;
	CheckOutPage checkoutPage;
	LogOutPage logoutPage;

	@When("the user select the product {string}")
	public void the_user_select_the_product(String productName) {
		selectProductPage = new SelectProductPage(driver, test);
		selectProductPage.searchAndSelectProduct(productName);
	}

	@When("the user add the product to the cart and click on add to cart button")
	public void the_user_add_the_product_to_the_cart_and_click_on_add_to_cart_button() {
		selectProductPage.addSearchedProductToCart();

	}

	@Then("the product is visible in the cart")
	public void the_product_is_visible_in_the_cart() {
		cartPage = new CartPage(driver, test);
		cartPage.verifyProductInCart();
	}

	@Then("the user proceeds the checkout")
	public void the_user_proceeds_the_checkout() {
		cartPage.proceedToCheckout();
	}

	@Then("the user completes the billing process")
	public void the_user_completes_the_billing_process() {
		checkoutPage = new CheckOutPage(driver,test);
		checkoutPage.completeCheckoutSteps();
		
	}
	@Then("user verifies the order confirmation")
	public void user_verifies_the_order_confirmation() {
//		String confirmationMsg = checkoutPage.verifyConfirmationOrder();
//		Assert.assertTrue(confirmationMsg.contains("Your order has been successfully processed!"));
	}
	@When("the user click on logout")
	public void the_user_click_on_logout() {
		logoutPage = new LogOutPage(driver, test);
		logoutPage.logoutUser();
	}

	@Then("the user validate text message")
	public void the_user_validate_text_message() {
		String actUrl = driver.getCurrentUrl();
		Assert.assertEquals("https://demowebshop.tricentis.com/",actUrl);
	}
	
	@Then("the product {string} should not be found")
	public void the_product_should_not_be_found(String product) {
		selectProductPage = new SelectProductPage(driver, test);
		boolean output=selectProductPage.verifyProductNotFound(product);
		Assert.assertFalse(output,"Test failed: Product '" + product + "' was unexpectedly found in search results.");
	}
	
}

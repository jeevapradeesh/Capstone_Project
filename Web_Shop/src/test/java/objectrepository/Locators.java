package objectrepository;

import org.openqa.selenium.By;

public class Locators {
	// Account Creation
	public static final By registerLink = By.className("ico-register");
	public static final By genderMale = By.id("gender-male");
	public static final By genderFemale = By.id("gender-female");
	public static final By firstName = By.id("FirstName");
	public static final By lastName = By.id("LastName");
	public static final By registerEmail = By.id("Email");
	public static final By registerPassword = By.id("Password");
	public static final By confirmPassword = By.id("ConfirmPassword");
	public static final By registerButton = By.id("register-button");
	public static final By registrationSuccessMessage = By.className("result");
	public static By registerErrorMessage = By.cssSelector("div.validation-summary-errors li");


	// Login Page
	public static By loginLink = By.className("ico-login");
	public static By emailField = By.id("Email");
	public static By passwordField = By.id("Password");
	public static By loginButton = By.xpath("//input[@value='Log in']");
	public static By logoutLink = By.className("ico-logout");
	public static By loginError = By.cssSelector(".message-error.validation-summary-errors li");


	// Select Product Page
	public static By searchBox = By.id("small-searchterms");
	public static By cartSuccessMessage = By.cssSelector(".bar-notification.success");
	public static By addToCartButton = By.xpath("//input[@value='Add to cart']");
	public static By searchButton = By.xpath("//input[@value='Search']");
	public static By firstBook = By.xpath("(//input[@value='Add to cart'])[1]");
	public static By cartLink = By.className("ico-cart");
	public static final By productInCart = By.xpath("//table[@class='cart']//a[text()='Fiction']");

	// CartPage
	public static By termsCheckbox = By.id("termsofservice");
	public static By checkoutButton = By.id("checkout");

	// Checkout Page
	public static By billingContinue = By.xpath("//input[@onclick='Billing.save()']");
	public static By shippingContinue = By.xpath("//input[@onclick='Shipping.save()']");
	public static By shippingMethodContinue = By.xpath("//input[@onclick='ShippingMethod.save()']");
	public static By paymentMethodContinue = By.xpath("//input[@onclick='PaymentMethod.save()']");
	public static By paymentInfoContinue = By.xpath("//input[@onclick='PaymentInfo.save()']");
	public static By confirmOrderButton = By.xpath("//input[@onclick='ConfirmOrder.save()']");
	public static By  completeCheckout = By.xpath("//input[@value='Continue']");
	public static By confirmationMessage = By.xpath("//div[@class='section order-completed']//strong[contains(text(),'successfully processed')]");

}

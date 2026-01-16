package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import factory.DriverManager;
import pageObjects.RegisterPage;
import utils.LoggerFactory;

public class RegisterPageTest extends DriverManager{
	private WebDriver driver;
	private RegisterPage registerPage;
	public RegisterPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		initialization();
		registerPage = new RegisterPage(driver);
	}

	@Test(priority = 1)
	public void userClicksTheRegisterLinkButtonOnTheHomePage() {
		driver.navigate().to("https://dsportalapp.herokuapp.com/register");
	}

	@Test(priority = 2, dependsOnMethods = {"userClicksTheRegisterLinkButtonOnTheHomePage"})
	public void userClicksLoginLinkInRegisterPage() {
		registerPage.loginLink();

		LoggerFactory.getLogger().info("User clicks Login link");
	}

	@Test(priority = 3, dependsOnMethods = { "userClicksTheRegisterLinkButtonOnTheHomePage" })
	public void user_should_be_redirected_to_login_page() {
		Assert.assertTrue(registerPage.getloginPageUrl());
		LoggerFactory.getLogger().info("User at the signIn page");
	}

}

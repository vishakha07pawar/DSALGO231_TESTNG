package testCases;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverManager;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import utils.DSAlgoDataProvider;

public class SignInTestCases extends BaseTest {
	protected WebDriver driver;
	private HomePage homePage; 
	private SignInPage signinpage; 
	 
	@BeforeMethod(alwaysRun = true)
	public void initPages() throws IOException {
		driver = DriverManager.getDriver();
	    homePage = new HomePage(driver);
	    signinpage = new SignInPage(driver);
	}
	
	@Test(dataProvider = "validLoginDataProvider", dataProviderClass = DSAlgoDataProvider.class)
	public void shouldValidateValidLoginData(Map<String, String> testData) throws IOException {
		driver.get(ConfigReader.getLoginUrl());
		String username = testData.get("username");
		String password = testData.get("password");
		homePage = signinpage.login(username, password);
		String actualMessage = homePage.getUserLoggedInMessage();
		Assert.assertEquals(actualMessage, "You are logged in");
	}

	@Test(dataProvider = "invalidLoginDataProvider", dataProviderClass = DSAlgoDataProvider.class)
	public void shouldValidateInvalidLoginData(Map<String, String> testData) throws IOException {	
		driver.get(ConfigReader.getLoginUrl());
		String username = testData.get("username");
		String password = testData.get("password");
		String expectedMessage = testData.get("expectedmessage");
		String validation = testData.get("validation");
		String actMsg = signinpage.verifyLogin(username, password, validation);
		if (testData.get("username") == null || testData.get("username").trim().isEmpty()) {
			signinpage.getBrowserValidationMessage();
		}
		else if(testData.get("password") == null || testData.get("password").trim().isEmpty()) {
			signinpage.getBrowserValidationMessage();
		}
		Assert.assertEquals(actMsg, expectedMessage);
	}

	@Test()
	public void user_clicks_register_button_in_the_login_page() {
		driver.get(ConfigReader.getLoginUrl());
		signinpage.clickRegister();
		Assert.assertTrue(signinpage.isRegistrationPageDisplayed());
	}

}

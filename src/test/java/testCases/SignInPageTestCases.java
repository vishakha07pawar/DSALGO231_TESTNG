package testCases;

import java.io.IOException;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverManager;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import utils.SignInDataProvider;

public class SignInPageTestCases extends BaseTest {
	protected WebDriver driver;
	private HomePage homePage; 
	private SignInPage signinpage; 
	 
	@BeforeMethod(alwaysRun = true)
	public void initPages() throws IOException {
		driver = DriverManager.getDriver();
	    homePage = new HomePage(driver);
	    dsAlgoPortal=new DsAlgoPortalPage(driver);
	    signinpage = new SignInPage(driver);
	    driver.get(ConfigReader.getLoginUrl());
	}
	
	@Test(dataProvider = "validLoginDataProvider", dataProviderClass = SignInDataProvider.class)
	public void shouldValidateValidLoginData(Map<String, String> testData) throws IOException {
		String username = testData.get("username");
		String password = testData.get("password");
		homePage = signinpage.login(username, password);
		String actualMessage = homePage.getUserLoggedInMessage();
		Assert.assertEquals(actualMessage, "You are logged in");
	}

	@Test(dataProvider = "invalidLoginDataProvider", dataProviderClass = SignInDataProvider.class)
	public void shouldValidateInvalidLoginData(Map<String, String> testData) throws IOException {	
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
		signinpage.clickRegister();
		Assert.assertTrue(signinpage.isRegistrationPageDisplayed());
	}

}

package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import utils.ConfigReader;
import utils.LoggerFactory;

public class RegisterPageTestCases extends BaseTest {

	private RegisterPage registerPage;
	private HomePage homePage;

	@BeforeMethod
	public void RegisterPageTest() {
		LoggerFactory.getLogger().info("Start RegisterPageTest");
		driver.get(ConfigReader.getAppUrl());
		dsAlgoPortal = new DsAlgoPortalPage(driver);
		homePage = dsAlgoPortal.clickDsPortalGetStarted();
		homePage.clickRegisterLink();
		registerPage = new RegisterPage(driver);

	}

	@Test(priority = 1)
	public void verifyHomePageRegisterLink() throws InterruptedException {
		LoggerFactory.getLogger().info("verifyHomePageRegisterLink");
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));

	}

	@Test(priority = 2)
	public void verifyRegisterPageLoginLink() throws InterruptedException {
		LoggerFactory.getLogger().info("verifyRegisterPageLoginLink");
		registerPage.clickLoginLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));

	}

	@Test(priority = 3, dataProvider = "registerWithValidData",dataProviderClass = utils.TestDataProviders.class)
	public void VerifyValidRegisteration(String userName, String passWord, String passwordConfirmation) {
		LoggerFactory.getLogger().info("VerifyValidRegisteration for user {}", userName);
		String UniqueUsername = userName + System.currentTimeMillis();
		registerPage.enterUserName(UniqueUsername);
		registerPage.enterpassWord(passWord);
		registerPage.enterPasswordConfirmation(passwordConfirmation);
		registerPage.clickRegisterBtn();
		Assert.assertTrue(registerPage.getRegisterMsg().contains("New Account Created"));
		homePage.clickSignOut();

	}

	@Test(priority = 4, dataProvider = "VerifyRegisterationWithInvalid", dataProviderClass = utils.TestDataProviders.class)
	public void InvalidRegisteration(String userName, String passWord, String passwordConfirmation, String fieldName,
			String expectedErrorMessage) {
		LoggerFactory.getLogger().info("InvalidRegisteration for user {}", userName);
		registerPage.enterUserName(userName);
		registerPage.enterpassWord(passWord);
		registerPage.enterPasswordConfirmation(passwordConfirmation);
		registerPage.clickRegisterBtn();
		String actualErrorMsg;
		WebElement field = registerPage.getFieldLocation(fieldName);
		if (fieldName.equalsIgnoreCase("login")) {

			actualErrorMsg = field.getText();
		} else {
			actualErrorMsg = (String) ((JavascriptExecutor) driver)
					.executeScript("return arguments[0].validationMessage;", field);
		}
		Assert.assertEquals(actualErrorMsg.trim(), expectedErrorMessage.trim());
		LoggerFactory.getLogger().info("The error message appears below the fieldName");

	}

}

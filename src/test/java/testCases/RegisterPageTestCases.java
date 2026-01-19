package testCases;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverManager;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.RegisterPage;
import pageObjects.SignInPage;
import utils.ExcelReader;
import utils.LoggerFactory;

public class RegisterPageTestCases extends BaseTest {
	private WebDriver driver;
	private RegisterPage registerPage;
	private DsAlgoPortalPage dsAlgoPortal;
	private HomePage homePage;
	private SignInPage signInPage;
	ExcelReader excelreader;

	@BeforeMethod
	public void RegisterPageTest() {

		driver = DriverManager.getDriver();
		dsAlgoPortal = new DsAlgoPortalPage(driver);
		signInPage = new SignInPage(driver);
		registerPage = new RegisterPage(driver);
		homePage = new HomePage(driver);
		dsAlgoPortal.clickDsPortalGetStarted();
		homePage.clickRegisterLink();

	}

	@Test(priority = 1)
	public void verifyHomePageRegisterLink() throws InterruptedException {
		
		Assert.assertTrue(driver.getCurrentUrl().contains("register"));
		
		
	}

	@Test(priority = 2)
	public void verifyRegisterPageLoginLink() throws InterruptedException {
		
		registerPage.clickLoginLink();
		Assert.assertTrue(driver.getCurrentUrl().contains("login"));
		
	}
	
    @Test(dataProvider = "registerWithValidData", dataProviderClass = ExcelReader.class)
	public void VerifyValidRegisteration(String userName, String passWord, String passwordConfirmation) {
    	String UniqueUsername = userName + System.currentTimeMillis();
    	registerPage.enterUserName(UniqueUsername);
		registerPage.enterpassWord(passWord);
		registerPage.enterPasswordConfirmation(passwordConfirmation);
		registerPage.clickRegisterBtn();
		Assert.assertTrue(registerPage.getRegisterMsg().contains("New Account Created"));
		
	}
    @Test(dataProvider = "VerifyRegisterationWithInvalid", dataProviderClass = ExcelReader.class)
    public void InvalidRegisteration(String userName, String passWord, String passwordConfirmation,String fieldName,String expectedErrorMessage) {
    	
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

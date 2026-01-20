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
	private HomePage homePage;
	private SignInPage signinpage;
	
	@BeforeMethod()
	public void initPages() throws IOException {
		driver.get(ConfigReader.getAppUrl());		
	    homePage = dsAlgoPortal.clickDsPortalGetStarted();
	    signinpage = homePage.clickSignInLink();
	}
	
	@Test(priority = 1, dataProvider = "validLoginDataProvider", dataProviderClass = utils.TestDataProviders.class)
	public void shouldValidateValidLoginData(String userName, String password, String expectedMessage) throws IOException {		
		homePage = signinpage.login(userName, password);
		String actualMessage = homePage.getUserLoggedInMessage();
		Assert.assertEquals(actualMessage, expectedMessage);
		homePage.clickSignOut();
	}
	@Test(priority = 2, dataProvider = "invalidLoginDataProvider", dataProviderClass = utils.TestDataProviders.class)
	public void shouldValidateInvalidLoginData(String userName, String password, String expectedMessage, String validation) throws IOException {
		String actMsg = signinpage.verifyLogin(userName, password, validation);
		Assert.assertEquals(actMsg, expectedMessage);
	}
	@Test(priority = 3)
	public void userNavigatesToRegistrationPage() {
		signinpage.clickRegister();
		Assert.assertTrue(signinpage.isRegistrationPageDisplayed());
	}
}








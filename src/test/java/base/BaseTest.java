package base;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import factory.DriverManager;
import io.qameta.allure.Allure;
import pageObjects.*;
import utils.*;

import static utils.LoggerFactory.*;

import java.io.ByteArrayInputStream;

public class BaseTest {
    protected WebDriver driver;
    protected static String appURL = null;
    protected DsAlgoPortalPage dsAlgoPortal;
    protected HomePage homePage;
    protected SignInPage signInPage;
    protected static String username = null;
    protected static String password = null;

    @BeforeSuite
    public void Initialize() {
        LoggerFactory.getLogger().info("calling DataReader to read text data from excel data source");
        ExcelDataReader.ReadTestData("/testData/" + "TestData.xlsx");
        ExcelDataReader.getValidCredentials();
        username = ExcelDataReader.getValidUserName();
        password = ExcelDataReader.getValidPassword();
        ConfigReader configReader = new ConfigReader();
        configReader.loadProperties();
        appURL = ConfigReader.getAppUrl();
    }

    @BeforeClass
    @Parameters({"browserType"})
    public void before(@Optional String browser) {
        LoggerFactory.getLogger().info("browser type from testNG configuration - {}", browser);
        ConfigReader.setBrowserType(browser);
        DriverManager.initBrowser(ConfigReader.getBrowserType(),appURL);
        driver = DriverManager.getDriver();
    }

    @AfterClass
    public void tearDown(ITestResult result) {
    	takeScreenShot(result);
        DriverManager.quitDriver();
        getLogger().info("DONE tearDown()..");
    }

    public void signIntoHomePage() {
        driver.get(appURL);
        dsAlgoPortal = new DsAlgoPortalPage(driver);
        homePage = dsAlgoPortal.clickDsPortalGetStarted();
        signInPage = homePage.clickSignInLink();
        homePage = signInPage.login(username, password);
    }
    public void takeScreenShot(ITestResult result) {
		if (!result.isSuccess()) {
			TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
			byte[] screenShot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
			Allure.addAttachment(result.getName(), new ByteArrayInputStream(screenShot));
		}
	}
}



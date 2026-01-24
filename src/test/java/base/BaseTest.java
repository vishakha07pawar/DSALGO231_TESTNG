package base;

import static utils.LoggerFactory.getLogger;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import factory.DriverManager;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import utils.ExcelDataReader;
import utils.LoggerFactory;

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
    public void tearDown() {
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
    
}



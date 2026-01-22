package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import factory.DriverManager;
import pageObjects.*;
import utils.*;

import static utils.LoggerFactory.*;

public class BaseTest {
    protected WebDriver driver;
    protected String appURL = null;
    protected DsAlgoPortalPage dsAlgoPortal;
    protected HomePage homePage;
    protected SignInPage signInPage;
    protected String username = null;
    protected String password = null;

    @BeforeSuite
    public void InitializeDataReader() {
        LoggerFactory.getLogger().info("calling DataReader to read text data from excel data source");
        ExcelDataReader.ReadTestData("/testData/" + "TestData.xlsx");
        ExcelDataReader.getValidCredentials();
        username = ExcelDataReader.getValidUserName();
        password = ExcelDataReader.getValidPassword();
    }

    @BeforeClass
    @Parameters({"browserType"})
    public void before(@Optional String browser) {
        LoggerFactory.getLogger().info("browser type from testNG configuration - {}", browser);
        ConfigReader.setBrowserType(browser);
        ConfigReader configReader = new ConfigReader();
        configReader.loadProperties();
        DriverManager.initBrowser(ConfigReader.getBrowserType());
        driver = DriverManager.getDriver();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        getLogger().info("DONE tearDown()..");
    }

    public void signIntoHomePage() {
        driver.get(ConfigReader.getAppUrl());
        dsAlgoPortal = new DsAlgoPortalPage(driver);
        homePage = dsAlgoPortal.clickDsPortalGetStarted();
        signInPage = homePage.clickSignInLink();
        homePage = signInPage.login(username, password);
    }
}



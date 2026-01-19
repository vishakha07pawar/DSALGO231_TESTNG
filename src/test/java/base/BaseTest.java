package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import factory.DriverManager;
import pageObjects.DsAlgoPortalPage;
import utils.*;

import static utils.LoggerFactory.*;

public class BaseTest {

    protected WebDriver driver;
    protected String appURL = null;
    protected DsAlgoPortalPage dsAlgoPortal;

    @BeforeClass
    @Parameters({"browserType"})
    public void before(@Optional String browser) {
        ConfigReader.setBrowserType(browser);
        LoggerFactory.getLogger().info("***  before() ***");
        String className = this.getClass().getName();
        ConfigReader configReader = new ConfigReader();
        configReader.loadProperties();
        DriverManager.initBrowser(ConfigReader.getBrowserType());
        driver = DriverManager.getDriver();
        appURL = ConfigReader.getAppUrl();
        driver.get(appURL);
        dsAlgoPortal = new DsAlgoPortalPage(driver);
    }

    @BeforeSuite
    public void InitializeDataReader() {
        LoggerFactory.getLogger().info("calling DataReader to read text data from excel data source");
        DataReader reader = new DataReader("/testData/" + "TestData.xlsx");
        ValidCredentialDataReader.getValidCredentialsFromExcelData();
    }

    @AfterClass
    public void tearDown() {
        getLogger().info("at tearDown()");
        getLogger().info("at tearDown() {}", DriverManager.getDriver());
        DriverManager.quitDriver();
        getLogger().info("DONE tearDown()..");
    }
}



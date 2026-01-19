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
        LoggerFactory.getLogger().info("browser type form testng configuration - {}",browser);
        ConfigReader.setBrowserType(browser);
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
        ExcelDataReader.ReadTestData("/testData/" + "TestData.xlsx");
        ExcelDataReader.getValidCredentials();
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        getLogger().info("DONE tearDown()..");
    }
}



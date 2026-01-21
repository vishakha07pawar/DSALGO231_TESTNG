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

    @BeforeSuite
    public void InitializeDataReader() {
        LoggerFactory.getLogger().info("calling DataReader to read text data from excel data source");
        ExcelDataReader.ReadTestData("/testData/" + "TestData.xlsx");
        ExcelDataReader.getValidCredentials();
    }

    @BeforeClass
    @Parameters({"browserType"})
    public void before(@Optional String browser) {
        ConfigReader.setBrowserType(browser);
        ConfigReader configReader = new ConfigReader();
        configReader.loadProperties();
        DriverManager.initBrowser(ConfigReader.getBrowserType());
        driver = DriverManager.getDriver();
        dsAlgoPortal = new DsAlgoPortalPage(driver);
    }

    @AfterClass
    public void tearDown() {
        DriverManager.quitDriver();
        getLogger().info("DONE tearDown()..");
    }
}



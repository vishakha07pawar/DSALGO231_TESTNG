package base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import factory.DriverManager;
import utils.LoggerFactory;
import utils.ConfigReader;

import static utils.LoggerFactory.*;

public class BaseTest {

    private ConfigReader configReader;
    private String browser;

    @BeforeClass
    public void beforeScenario() throws IOException {
        configReader = new ConfigReader();
        configReader.loadProperties();
        browser = ConfigReader.getBrowserType();
        DriverManager.initBrowser(browser);
       //System.out.println("Before class  : "+browser);
        System.out.printf("***Before class driver %s\n",DriverManager.getDriver());
        getLogger().info("***Before class driver {}",DriverManager.getDriver());
    }

    @AfterClass
    public void tearDown() {
        System.out.println("at teardown");
        System.out.printf("@@@@@After class driver %s\n",DriverManager.getDriver());
        getLogger().info("***Before class driver {}",DriverManager.getDriver());
        if (DriverManager.getDriver() != null) {

            DriverManager.getDriver().quit();
        }
        getLogger().info("DONE tearDown()..");
        System.out.println("after teardown");
    }
}



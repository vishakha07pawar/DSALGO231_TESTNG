package base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import factory.DriverManager;
import factory.LoggerFactory;
import utils.ConfigReader;

public class BaseTest {
	private Properties prop;
	private ConfigReader configReader;
	private String browser;
	private WebDriver driver;

	//@Before("@DsAlgoPortal or @HomePage or @Register or @SignIn or @HomePageSignIn")
	@BeforeMethod()
	public void setUp() throws IOException {
		configReader = new ConfigReader();
		prop = configReader.loadProperties();

		if (ConfigReader.getBrowserType() != null) {
			browser = ConfigReader.getBrowserType();
		} else {
			browser = prop.getProperty("browser");
		}

		driver = DriverManager.initBrowser(browser);
	}

	//@Before("@DataStructure")
	/*
	 * @BeforeMethod public void preStep() throws IOException, InterruptedException
	 * { configReader = new ConfigReader(); prop = configReader.loadProperties();
	 * 
	 * if (ConfigReader.getBrowserType() != null) { browser =
	 * ConfigReader.getBrowserType(); } else { browser =
	 * prop.getProperty("browser"); }
	 * 
	 * driver = DriverManager.initBrowser(browser);
	 * 
	 * String appURL = ConfigReader.getAppUrl(); driver.get(appURL);
	 * 
	 * DsAlgoPortalPage dsAlgoPortal = new DsAlgoPortalPage(driver); HomePage
	 * homePage = dsAlgoPortal.clickDsPortalGetStarted(); SignInPage signInPage =
	 * homePage.clickSignInLink(); String username = "dsalgo231"; String password =
	 * "automation2025#"; homePage = signInPage.login(username, password); }
	 */

	/*
	 * @Before("@TryEditor") public void tryEditorpPreStep() { configReader = new
	 * ConfigReader(); prop = configReader.loadProperties();
	 * 
	 * if (ConfigReader.getBrowserType() != null) { browser =
	 * ConfigReader.getBrowserType(); } else { browser =
	 * prop.getProperty("browser"); }
	 * 
	 * driver = DriverManager.initBrowser(browser); }
	 */

	@AfterMethod
	public void tearDown() {
		if (DriverManager.getDriver() != null) {
			DriverManager.getDriver().quit();
		}
		LoggerFactory.getLogger().info("DONE tearDown()..");
	}

}

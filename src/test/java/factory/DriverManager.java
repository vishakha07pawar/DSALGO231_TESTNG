package factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import utils.LoggerFactory;

public class DriverManager {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static WebDriver initBrowser(String browser) {

        BrowserOptions browserOptions = new BrowserOptions();
        String browserType = browser.trim().toLowerCase();

        switch (browserType) {
            case "chrome":
                driver.set(new ChromeDriver(browserOptions.chromeOption()));
                break;
            case "edge":
                driver.set(new EdgeDriver(browserOptions.edgeOption()));
                break;
            case "firefox":
                driver.set(new FirefoxDriver(browserOptions.firefoxOption()));
                break;
            default:
                LoggerFactory.getLogger().error("Unexpected value for browser: {}", browserType);
                throw new IllegalStateException("Unexpected value for browserType: " + browserType);
        }

        if (!browserType.equalsIgnoreCase("firefox"))
            getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

        getDriver().manage().window().maximize();
        getDriver().manage().deleteAllCookies();

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}

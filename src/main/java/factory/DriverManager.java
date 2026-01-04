package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import utils.BrowserOptions;


public class DriverManager {

    private static final BrowserOptions browserOptions = new BrowserOptions();
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    private static ChromeOptions co = browserOptions.chromeOption();
    private static EdgeOptions ed = browserOptions.edgeOption();
    private static FirefoxOptions fo = browserOptions.firefoxOption();

    public static WebDriver initBrowser(String browser) {
        LoggerFactory.getLogger().info("In initBrowser(), browser value - {}", browser);
        if (browser.trim().equalsIgnoreCase("chrome")) {
            driver.set(new ChromeDriver(co));
        } else if (browser.trim().equalsIgnoreCase("edge")) {
            driver.set(new EdgeDriver(ed));
        } else if (browser.trim().equalsIgnoreCase("firefox")) {
            driver.set(new FirefoxDriver(fo));
        }

        return getDriver();
    }

    public static WebDriver getDriver() {
        return driver.get();
    }
}
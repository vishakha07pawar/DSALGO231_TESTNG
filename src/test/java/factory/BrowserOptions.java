package factory;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {

    public ChromeOptions chromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();

        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.addArguments("--disk-cache-size=0");
        chromeOptions.setPageLoadTimeout(Duration.ofSeconds(40));
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setScriptTimeout(Duration.ofSeconds(40));
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--incognito");

        return chromeOptions;
    }

    public EdgeOptions edgeOption() {
        EdgeOptions options = new EdgeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--disk-cache-size=0");
        options.setPageLoadTimeout(Duration.ofSeconds(40));
        options.setAcceptInsecureCerts(true);
        options.setScriptTimeout(Duration.ofSeconds(40));
        options.addArguments("start-maximized");
        options.addArguments("--incognito");

        return options;
    }

    public FirefoxOptions firefoxOption() {
        FirefoxOptions options = new FirefoxOptions();

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--disk-cache-size=0");
        options.setPageLoadTimeout(Duration.ofSeconds(40));
        options.setAcceptInsecureCerts(true);
        options.setScriptTimeout(Duration.ofSeconds(40));
        options.addArguments("start-maximized");
        options.addArguments("--incognito");

        return options;
    }
}

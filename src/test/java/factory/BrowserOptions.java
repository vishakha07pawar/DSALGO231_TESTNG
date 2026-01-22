package factory;

import java.time.Duration;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserOptions {

    public ChromeOptions chromeOption() {
        ChromeOptions options = new ChromeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.addArguments("--disk-cache-size=0");
        options.setPageLoadTimeout(Duration.ofSeconds(40));
        options.setAcceptInsecureCerts(true);
        options.setScriptTimeout(Duration.ofSeconds(40));
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");

        return options;
    }

    public EdgeOptions edgeOption() {
        EdgeOptions options = new EdgeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setPageLoadTimeout(Duration.ofSeconds(60));
        options.setAcceptInsecureCerts(true);
        options.setScriptTimeout(Duration.ofSeconds(60));
        options.addArguments("--incognito");
        options.addArguments("--headless=new");
        options.addArguments("--disable-gpu");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-allow-origins=*");

        return options;
    }

    public FirefoxOptions firefoxOption() {
        FirefoxOptions options = new FirefoxOptions();

        if (System.getenv("JENKINS_URL") != null) {
            options.addArguments("--headless");
        }
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        options.setAcceptInsecureCerts(true);
        return options;
    }
}

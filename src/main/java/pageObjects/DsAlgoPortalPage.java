package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DsAlgoPortalPage {

    private WebDriver driver;

    private By lblDsPortalHeading = By.xpath("//h1[normalize-space()='Preparing for the Interviews']");
    private By lblDsPortalParagraph = By.xpath("//p[normalize-space()='You are at the right place']");
    private By btnGetStartedDsPortal = By.xpath("//button[normalize-space()='Get Started']");
    private WebDriverWait wait;

    public DsAlgoPortalPage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isDsPortalHeadingVisible() {
        return driver.findElement(lblDsPortalHeading).isDisplayed();
    }

    public boolean isDsPortalParagraphVisible() {
        return driver.findElement(lblDsPortalParagraph).isDisplayed();
    }

    public boolean isDsPortalGetStartedVisible() {
        return driver.findElement(btnGetStartedDsPortal).isDisplayed();
    }

    public HomePage clickDsPortalGetStarted() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(btnGetStartedDsPortal)).click();
        return new HomePage(driver);
    }
}

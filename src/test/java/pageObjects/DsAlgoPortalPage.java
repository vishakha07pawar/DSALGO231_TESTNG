package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DsAlgoPortalPage {

    private WebDriver driver;

    private By lblDsPortalHeading = By.xpath("//div[@class='content']//h1");
    private By lblDsPortalParagraph = By.xpath("//div[@class='content']//p");
    private By btnGetStartedDsPortal = By.xpath("//button[@class='btn']");
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

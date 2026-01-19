package pageObjects;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.LoggerFactory;

import java.time.Duration;

public class TryEditorPage {

    private WebDriver driver;

    private By btnRun = By.xpath("//button[normalize-space()='Run']");
    private By txtTryEditor = By.xpath("//pre[@role='presentation']//span[@role='presentation']//span");
    private By txtOutput = By.xpath("//pre[@id='output']");

    WebDriverWait wait;

    public TryEditorPage(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public boolean isRunButtonVisible() {
        return driver.findElement(btnRun).isDisplayed();
    }

    public void Run() {
        driver.findElement(btnRun).click();
    }

    public String getPrintMessage() {
        try {
            return driver.findElement(txtOutput).getText();
        } catch (Exception e) {
            LoggerFactory.getLogger().error(e.getMessage());
            return "";
        }
    }

    public record Result(boolean isAlertWindowVisible, String alertWindowMessage) {
    }

    public Result getAlertWindowVisibilityAndAlertText() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
                LoggerFactory.getLogger().info("Alert windows is not visible");
                return new Result(false, "");
            } else {
                Alert alert = driver.switchTo().alert();
                String alertMessage = alert.getText();
                alert.accept();
                LoggerFactory.getLogger().info("Alert was present and accepted");
                return new Result(true, alertMessage);
            }
        } catch (Exception ex) {
            LoggerFactory.getLogger().error(ex.getStackTrace());
            return new Result(false, null);
        }
    }

    public boolean isAlertWindowVisible() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
                LoggerFactory.getLogger().info("Alert windows is not visible");
                return false;
            } else {
                Alert alert = driver.switchTo().alert();
                alert.accept();
                LoggerFactory.getLogger().info("Alert was present and accepted");
                return true;
            }
        } catch (Exception ex) {
            LoggerFactory.getLogger().error(ex.getStackTrace());
            return false;
        }
    }

    public String getAlertWindowMessage() {
        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
                LoggerFactory.getLogger().info("Alert windows is not present");
                return "";
            } else {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                alert.accept();
                LoggerFactory.getLogger().info("Alert is present and accepted");
                return alertText;
            }
        } catch (Exception ex) {
            LoggerFactory.getLogger().error(ex.getStackTrace());
            return null;
        }
    }

    public void enterDataIntoEditor(String inputData) {
        WebElement txtDsCode = driver.findElement(txtTryEditor);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions act = new Actions(driver);
        act.moveToElement(txtDsCode).click();
        js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue('');");
        js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", inputData);
    }

    public String getTryEditorPageURL() {
        return driver.getCurrentUrl();
    }
}

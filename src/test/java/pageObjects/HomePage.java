package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    private WebDriver driver;

    private By lnkNumpyNinja = By.xpath("//a[normalize-space()='NumpyNinja']");
    private By lnkRegister = By.xpath("//a[normalize-space()='Register']");
    private By lnkSignIn = By.xpath("//a[normalize-space()='Sign in']");
    private By drpDataStructures = By.xpath("//a[normalize-space()='Data Structures']");
    private By drpDataStructureOptions = By.xpath("//div[@class='dropdown-menu show']//a");
    private By msgError = By.xpath("//div[@role='alert']");
    private By pnlDataStructureItems = By.xpath("//h5[@class='card-title']");
    private By msgUserLoggedIn = By.xpath("//*[contains(text(), 'You are logged in')]");
    private By lblSignedInUser = By.xpath("//div[@class='navbar-nav']//ul//a[2]");
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isNumpyNinjaHeaderVisible() {
        return driver.findElement(lnkNumpyNinja).isDisplayed();
    }

    public boolean isRegisterLinkVisible() {
        return driver.findElement(lnkRegister).isDisplayed();
    }

    public boolean isSignInLinkVisible() {
        return driver.findElement(lnkSignIn).isDisplayed();
    }

    public boolean isDataStructuresDropDownVisible() {
        return driver.findElement(drpDataStructures).isDisplayed();
    }

    public void clickDataStructureDropDown() {
        driver.findElement(drpDataStructures).click();
    }

    public List<String> getPanelDataStructuresItems() {
        List<WebElement> dataStructureItems = driver.findElements(pnlDataStructureItems);
        List<String> dataStructureItemsNames = new ArrayList<>();

        for (WebElement dataStructureItem : dataStructureItems) {
            String item = dataStructureItem.getText();
            dataStructureItemsNames.add(item);
        }

        return dataStructureItemsNames;
    }

    public List<String> getDataStructureDropDownItems() {
        List<WebElement> dataStructureDropDownItems = driver.findElements(drpDataStructureOptions);
        List<String> itemsList = new ArrayList<>();

        for (WebElement dataStructureDropDownItem : dataStructureDropDownItems) {
            String item = dataStructureDropDownItem.getText();
            itemsList.add(item);
        }

        return itemsList;
    }

    public void selectDataStructureItemFromDropdown(String dsType) {
        driver.findElement(drpDataStructures).click();
        List<WebElement> dataStructureOptions = driver.findElements(drpDataStructureOptions);

        for (WebElement dataStructureOption : dataStructureOptions) {
            String option = dataStructureOption.getText();
            if (option.equals(dsType)) {
                dataStructureOption.click();
                return;
            }
        }
    }

    public void clickGetStartedButton(String dsType) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By btnGetStarted = By.xpath("//h5[text()='" + dsType + "']/following-sibling::a");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnGetStarted));
        element.click();
    }

    public Object clickGetStartedButtonOfGivenDsType(String dsPage) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        By btnGetStarted = By.xpath("//h5[text()='" + dsPage + "']/following-sibling::a");
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(btnGetStarted));
        element.click();

        Object pageObject = null;

        String dataStructurePageName = dsPage.trim().toLowerCase();

		/*
		 * switch (dataStructurePageName) { case "data structures-introduction":
		 * pageObject = new DataStructurePage(driver); break; case "array": pageObject =
		 * new ArrayPage(driver); break; case "linked list": pageObject = new
		 * LinkedListPage(driver); break; case "stack": pageObject = new
		 * StackPage(driver); break; case "queue": pageObject = new QueuePage(driver);
		 * break; case "tree": pageObject = new TreePage(driver); break; case "graph":
		 * pageObject = new GraphPage(driver); break; default:
		 * LoggerFactory.getLogger().
		 * error("Given Data Structure page name does not exist"); break; }
		 */

        return pageObject;
    }

    public String getErrorMessage() {
        return driver.findElement(msgError).getText();
    }

    public RegisterPage clickRegisterLink() {
        driver.findElement(lnkRegister).click();
        return new RegisterPage(driver);
    }

    public SignInPage clickSignInLink() {
        driver.findElement(lnkSignIn).click();
        return new SignInPage(driver);
    }

    public String getUserLoggedInMessage() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(msgUserLoggedIn)).getText();
    }

    public boolean isUserNameVisibleAfterSignIn(String userName) {

        if (userName == null || userName.isEmpty()) {
            return false;
        }

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String userNameCapitalizeFirstLetter = userName.trim().substring(0, 1).toUpperCase() + userName.trim().substring(1);
        By lblSignedInUserName = By.xpath("//a[normalize-space()='" + userNameCapitalizeFirstLetter + "']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lblSignedInUserName)).isDisplayed();
    }

    public String getCurrentSignedInUserName() {
        return driver.findElement(lblSignedInUser).getText();
    }

    public String getHomePageURL() {
        return driver.getCurrentUrl();
    }
}

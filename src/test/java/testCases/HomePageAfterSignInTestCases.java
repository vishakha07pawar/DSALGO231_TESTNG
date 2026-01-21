package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import utils.ExcelDataReader;
import utils.LoggerFactory;

public class HomePageAfterSignInTestCases extends BaseTest {
    private HomePage homePage;
    private SignInPage signInPage;
    private String username = null;
    private String password = null;

    @BeforeMethod
    public void baseHomePageAfterSignIn() {
        driver.get(ConfigReader.getAppUrl());
        dsAlgoPortal = new DsAlgoPortalPage(driver);
        homePage = dsAlgoPortal.clickDsPortalGetStarted();
        signInPage = homePage.clickSignInLink();
        username = ExcelDataReader.getValidUserName();
        password = ExcelDataReader.getValidPassword();
        homePage = signInPage.login(username, password);
    }

    @Test(priority = 1)
    public void verifySignedIn() {
        Assert.assertTrue(homePage.isUserNameVisibleAfterSignIn(username));
        homePage.clickSignOut();
        LoggerFactory.getLogger().info("Signed in, user name \"{}\" is displayed on home page", username);
    }

    @Test(priority = 2, dataProvider = "dropdownNavigateDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyToNavigateFromDropDown(String dropdownItem, String dsPage) {
        homePage.selectDataStructureItemFromDropdown(dropdownItem.trim());
        String currentPageURL = driver.getCurrentUrl();
        Assert.assertTrue(currentPageURL.contains(dsPage.toLowerCase().trim()));
        homePage.clickSignOut();
        LoggerFactory.getLogger().info("Navigated to {} from DropDown {}", dsPage, dropdownItem);
    }

    @Test(priority = 3, dataProvider = "panelNavigateDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyToNavigateFromPanel(String panelItem, String dsPage) {
        homePage.clickGetStartedButton(panelItem.trim());
        String currentPageURL = driver.getCurrentUrl();
        Assert.assertTrue(currentPageURL.contains(dsPage.toLowerCase().trim()));
        homePage.clickSignOut();
        LoggerFactory.getLogger().info("Navigated to {} from the Panel {}", dsPage, panelItem);
    }

    @Test(priority = 4)
    public void verifyLogOutMessage() {
        homePage.clickSignOut();
        String loggedOutMessage = "Logged out successfully";
        Assert.assertEquals(homePage.getLoggedOutMsg(), loggedOutMessage);
        LoggerFactory.getLogger().info("Logged Out With A Message {}", loggedOutMessage);
    }
}
package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import utils.ExcelDataReader;
import utils.LoggerFactory;
import java.util.List;

public class HomePageTestCases extends BaseTest {
    private HomePage homePage;
    private SignInPage signInPage;
    private List<String> actualDataStructureDropDownItemNames;
    String username = null;
    String password = null;

    @BeforeMethod
    @Parameters({"browserType"})
    public void baseHomePage(@Optional String browser) {

        LoggerFactory.getLogger().info("browserType value from testNG file {}", browser);
        ConfigReader.setBrowserType(browser);
        appURL = ConfigReader.getAppUrl();
        driver.get(appURL);
        username = ExcelDataReader.getValidUserName();
        password = ExcelDataReader.getValidPassword();
        LoggerFactory.getLogger().info("***  HomePageTestCases ***");
        homePage = dsAlgoPortal.clickDsPortalGetStarted();
        actualDataStructureDropDownItemNames = null;
    }

    @Test(priority = 1)
    public void isNumpyNinjaHeadingVisible() {
        Assert.assertTrue(homePage.isNumpyNinjaHeaderVisible());
        LoggerFactory.getLogger().info("NumpyNinja heading is visible");
    }

    @Test(priority = 2)
    public void isRegisterLinkVisible() {
        Assert.assertTrue(homePage.isRegisterLinkVisible());
        LoggerFactory.getLogger().info("Register link is visible");
    }

    @Test(priority = 3)
    public void isSignInLinkVisible() {
        Assert.assertTrue(homePage.isSignInLinkVisible());
        LoggerFactory.getLogger().info("Sign in link is visible");
    }

    @Test(priority = 4)
    public void isDatastructuresDropdownVisible() {
        Assert.assertTrue(homePage.isDataStructuresDropDownVisible());
        LoggerFactory.getLogger().info("Data Structures drop down is visible");
    }


    @Test(priority = 5, dataProvider = "panelNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void isGetStartedButtonsForPanelItemsVisible(String ExpectedPanelName) {
        List<String> actualPanelDataStructuresNames = homePage.getPanelDataStructuresItems();
        Assert.assertTrue(actualPanelDataStructuresNames.contains(ExpectedPanelName));
        LoggerFactory.getLogger().info("user_should_be_able_to_see_get_started_buttons_for_the_following_panel_items {}", ExpectedPanelName);
    }


    @Test(priority = 6, dataProvider = "dropdownNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void isDropdownOptionsVisible(String expectedDropDownItemName) {
        if (actualDataStructureDropDownItemNames == null)
            actualDataStructureDropDownItemNames = homePage.getDataStructureDropDownItems();
        Assert.assertTrue(actualDataStructureDropDownItemNames.contains(expectedDropDownItemName));
        LoggerFactory.getLogger().info("user_should_able_to_see_the_following_dropdown_options {}", expectedDropDownItemName);

    }

   @Test(priority = 7,dataProvider = "dropdownNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void isWarningMessageVisibleForDropdownItem(String dropDownItem) {
       homePage.selectDataStructureItemFromDropdown(dropDownItem);
       String actualErrorMessage = homePage.getErrorMessage();
        String expectedErrorMessage = "You are not logged in";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        LoggerFactory.getLogger().info("user_should_able_to_see_a_warning_message for {}", dropDownItem);
    }

    @Test(priority = 8, dataProvider = "panelNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void isWarningMessageVisibleForPanelItems(String panelName) {
        homePage.clickGetStartedButton(panelName);
        String actualErrorMessage = homePage.getErrorMessage();
        String expectedErrorMessage = "You are not logged in";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        LoggerFactory.getLogger().info("user_clicks_get_started_button_of_from_panel for {}", panelName);
    }

    @Test(priority = 9)
    public void verifyNavigateToRegisterPage() {
        homePage.clickRegisterLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("register"));
    }

    @Test(priority = 10)
    public void verifyNavigateToSignInPage() {
        signInPage =  homePage.clickSignInLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("login"));
    }

    @Test(priority = 11)
    public void verifyUserAtHomePageAfterSignIn() {
        driver.get(ConfigReader.getAppUrl());
        dsAlgoPortal = new DsAlgoPortalPage(driver);
        homePage = dsAlgoPortal.clickDsPortalGetStarted();
        SignInPage signInPage = homePage.clickSignInLink();
        homePage = signInPage.login(username, password);
        Assert.assertTrue(homePage.isUserNameVisibleAfterSignIn(username));
        LoggerFactory.getLogger().info("Signed user \"{}\" is displayed on home page", username);
    }

    @Test(priority = 12, dataProvider = "dropdownNavigateDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyToNavigateFromDropDown(String dropdownItem, String dsPage) {
        homePage.selectDataStructureItemFromDropdown(dropdownItem.trim());
        String currentPageURL = driver.getCurrentUrl();
        Assert.assertTrue(currentPageURL.contains(dsPage.toLowerCase().trim()));
        LoggerFactory.getLogger().info("userShouldAbleToNavigateTo {} From DropDown {}", dsPage, dropdownItem);
    }

    @Test(priority = 13, dataProvider = "panelNavigateDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyToNavigateFromPanel(String panelItem, String dsPage) {
        homePage.clickSignOut();
        SignInPage signInPage = homePage.clickSignInLink();
        homePage = signInPage.login(username, password);
        homePage.clickGetStartedButton(panelItem.trim());
        String currentPageURL = driver.getCurrentUrl();
        Assert.assertTrue(currentPageURL.contains(dsPage.toLowerCase().trim()));
        LoggerFactory.getLogger().info("userShouldAbleToNavigateTo {} From Panel {}", dsPage, panelItem);
    }

    @Test(priority = 14)
    public void verifyLogOutMessage() {
        homePage.clickSignOut();
        String loggedOutMessage = "Logged out successfully";
        Assert.assertEquals(homePage.getLoggedOutMsg(), loggedOutMessage);
        LoggerFactory.getLogger().info("user Logged Out With A Message {}", loggedOutMessage);
    }
}

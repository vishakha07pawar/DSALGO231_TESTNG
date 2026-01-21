package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePage;
import utils.ConfigReader;
import utils.ExcelDataReader;
import utils.LoggerFactory;

import java.util.List;

public class HomePageTestCases extends BaseTest {
    private HomePage homePage;
    private List<String> actualDataStructureDropDownItemNames;
    String username = null;
    String password = null;

    @BeforeMethod
    public void baseHomePage() {
        driver.get(ConfigReader.getAppUrl());
        username = ExcelDataReader.getValidUserName();
        password = ExcelDataReader.getValidPassword();
        homePage = dsAlgoPortal.clickDsPortalGetStarted();
        actualDataStructureDropDownItemNames = null;
    }

    @Test(priority = 1)
    public void verifyNumpyNinjaHeadingVisible() {
        Assert.assertTrue(homePage.isNumpyNinjaHeaderVisible());
        LoggerFactory.getLogger().info("NumpyNinja heading is visible");
    }

    @Test(priority = 2)
    public void verifyRegisterLinkVisible() {
        Assert.assertTrue(homePage.isRegisterLinkVisible());
        LoggerFactory.getLogger().info("Register link is visible");
    }

    @Test(priority = 3)
    public void verifySignInLinkVisible() {
        Assert.assertTrue(homePage.isSignInLinkVisible());
        LoggerFactory.getLogger().info("Sign in link is visible");
    }

    @Test(priority = 4)
    public void verifyDatastructuresDropdownVisible() {
        Assert.assertTrue(homePage.isDataStructuresDropDownVisible());
        LoggerFactory.getLogger().info("Data Structures drop down is visible");
    }

    @Test(priority = 5, dataProvider = "panelNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyGetStartedButtonsForPanelItemsVisible(String ExpectedPanelName) {
        List<String> actualPanelDataStructuresNames = homePage.getPanelDataStructuresItems();
        Assert.assertTrue(actualPanelDataStructuresNames.contains(ExpectedPanelName));
        LoggerFactory.getLogger().info("Get stared button for the panel visible : {}", ExpectedPanelName);
    }

    @Test(priority = 6, dataProvider = "dropdownNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyDropdownOptionsVisible(String expectedDropDownItemName) {
        if (actualDataStructureDropDownItemNames == null)
            actualDataStructureDropDownItemNames = homePage.getDataStructureDropDownItems();
        Assert.assertTrue(actualDataStructureDropDownItemNames.contains(expectedDropDownItemName));
        LoggerFactory.getLogger().info("Drop down option visible : {}", expectedDropDownItemName);
    }

    @Test(priority = 7, dataProvider = "dropdownNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyWarningMessageVisibleForDropdownItem(String dropDownItem) {
        homePage.selectDataStructureItemFromDropdown(dropDownItem);
        String actualErrorMessage = homePage.getErrorMessage();
        String expectedErrorMessage = "You are not logged in";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        LoggerFactory.getLogger().info("Warning message visible for the dropdown : {}", dropDownItem);
    }

    @Test(priority = 8, dataProvider = "panelNamesDP", dataProviderClass = utils.TestDataProviders.class)
    public void verifyWarningMessageVisibleForPanelItems(String panelName) {
        homePage.clickGetStartedButton(panelName);
        String actualErrorMessage = homePage.getErrorMessage();
        String expectedErrorMessage = "You are not logged in";
        Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
        LoggerFactory.getLogger().info("Warning message visible for the panel : {}", panelName);
    }

    @Test(priority = 9)
    public void verifyNavigateToRegisterPage() {
        homePage.clickRegisterLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("register"));
        LoggerFactory.getLogger().info("Navigated to Register Page");
    }

    @Test(priority = 10)
    public void verifyNavigateToSignInPage() {
        homePage.clickSignInLink();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("login"));
        LoggerFactory.getLogger().info("Navigated to SignIn Page");
    }
}

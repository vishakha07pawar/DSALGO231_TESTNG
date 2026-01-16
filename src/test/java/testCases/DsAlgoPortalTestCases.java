package testCases;

import base.BaseTest;
import factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import utils.ConfigReader;
import utils.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

public class DsAlgoPortalTestCases extends BaseTest {

    private DsAlgoPortalPage dsAlgoPortal;
    private HomePage homePage;
    private String appURL = null;
    private WebDriver driver;
    private Properties prop;
    private ConfigReader configReader;
    private String browser;

     @Test(priority = 1, groups = "DsAlgoPortal")
    public void user_opens_the_browser() throws IOException {
        driver = DriverManager.getDriver();
    }

    @Test(priority = 2,groups = "DsAlgoPortal",dependsOnMethods = {"user_opens_the_browser"})
    public void user_enters_the_correct_ds_algo_portal_url() {
        appURL = ConfigReader.getAppUrl();
       driver.get(appURL);
        dsAlgoPortal = new DsAlgoPortalPage(driver);

    }

    @Test(priority = 3,groups = "DsAlgoPortal", dependsOnMethods = {"user_enters_the_correct_ds_algo_portal_url"})
    public void user_should_able_to_land_on_ds_algo_portal() {
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(appURL, currentURL);
        LoggerFactory.getLogger().info("User landed on DsAlgo portal page");
     }

    @Test(priority = 4,groups = "DsAlgoPortal", dependsOnMethods = {"user_enters_the_correct_ds_algo_portal_url"})
    public void user_should_able_to_see_get_started_button_on_ds_algo_portal_page() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalGetStartedVisible());
        LoggerFactory.getLogger().info("User can see Get Started button on DS Algo Portal page");
    }

    @Test(priority = 5,groups = "DsAlgoPortal",dependsOnMethods = {"user_enters_the_correct_ds_algo_portal_url"})
    public void user_should_able_to_see_preparing_for_the_interviews_heading() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalHeadingVisible());
        LoggerFactory.getLogger().info("User can see Preparing for the Interviews heading on DS Algo Portal page");
    }

    @Test(priority = 6,groups = "DsAlgoPortal",dependsOnMethods = {"user_enters_the_correct_ds_algo_portal_url"})
    public void user_should_able_to_see_you_are_at_the_right_place_paragraph() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalParagraphVisible());
        LoggerFactory.getLogger().info("User can see You are at the right place paragraph on DS Algo Portal page");
    }

    @Test(priority = 7,groups = "DsAlgoPortal",dependsOnMethods = {"user_enters_the_correct_ds_algo_portal_url"})
    public void user_clicks_the_get_started_button_on_ds_algo_portal_page() {
        homePage = dsAlgoPortal.clickDsPortalGetStarted();
    }

    @Test(priority = 8,groups = "DsAlgoPortal",dependsOnMethods = {"user_enters_the_correct_ds_algo_portal_url"})
    public void user_navigates_to_home_page() {
        String currentUrl = homePage.getHomePageURL();
        Assert.assertEquals(currentUrl, appURL + "home");
        LoggerFactory.getLogger().info("User navigates to Home page");
    }
}

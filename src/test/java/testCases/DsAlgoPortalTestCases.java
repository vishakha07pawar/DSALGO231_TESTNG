package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.LoggerFactory;

public class DsAlgoPortalTestCases extends BaseTest {

    @Test(priority = 1)
    public void verifyLandingOnDsAlgoPortal() {
        LoggerFactory.getLogger().info("***  user_should_able_to_land_on_ds_algo_portal ***");
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(appURL, currentURL);
        LoggerFactory.getLogger().info("User landed on DsAlgo portal page");
    }

    @Test(priority = 2)
    public void isGetStartedButtonOnDsPortalPageVisible() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalGetStartedVisible());
        LoggerFactory.getLogger().info("User can see Get Started button on DS Algo Portal page");
    }

    @Test(priority = 3)
    public void isPreparingForInterviewsHeadingOnDsPortalVisible() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalHeadingVisible());
        LoggerFactory.getLogger().info("User can see Preparing for the Interviews heading on DS Algo Portal page");
    }

    @Test(priority = 4)
    public void isRightPlaceParagraphOnDsPortalVisible() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalParagraphVisible());
        LoggerFactory.getLogger().info("User can see You are at the right place paragraph on DS Algo Portal page");
    }

    @Test(priority = 5)
    public void verifyNavigateToHomePage() {
        dsAlgoPortal.clickDsPortalGetStarted();
        LoggerFactory.getLogger().info("started .... User navigates to Home page");
        Assert.assertEquals(driver.getCurrentUrl(), appURL + "home");
        LoggerFactory.getLogger().info("User navigates to Home page");
    }
}

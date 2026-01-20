package testCases;

import base.BaseTest;
import pageObjects.DsAlgoPortalPage;

import org.testng.Assert;
import org.testng.annotations.*;
import utils.ConfigReader;
import utils.LoggerFactory;

public class DsAlgoPortalTestCases extends BaseTest {
	
	private DsAlgoPortalPage dsAlgoPortal;
	private String appURL = null;

    @BeforeMethod
    public void baseDsPortal() {
    	appURL = ConfigReader.getAppUrl();
        driver.get(appURL);
        dsAlgoPortal = new DsAlgoPortalPage(driver);
    }

    @Test(priority = 1)
    public void verifyLandingOnDsAlgoPortal() {
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(appURL, currentURL);
        LoggerFactory.getLogger().info("User landed on DsAlgo portal page");
    }

    @Test(priority = 2)
    public void verifyGetStartedButtonOnDsPortalPageVisible() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalGetStartedVisible());
        LoggerFactory.getLogger().info("Get Started button on DS Algo Portal page is visible");
    }

    @Test(priority = 3)
    public void verifyPreparingForInterviewsHeadingOnDsPortalVisible() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalHeadingVisible());
        LoggerFactory.getLogger().info("Preparing for the Interviews heading on DS Algo Portal page is visible");
    }

    @Test(priority = 4)
    public void verifyRightPlaceParagraphOnDsPortalVisible() {
        Assert.assertTrue(dsAlgoPortal.isDsPortalParagraphVisible());
        LoggerFactory.getLogger().info("You are at the right place paragraph on DS Algo Portal page is visible");
    }

    @Test(priority = 5)
    public void verifyNavigateToHomePage() {
        dsAlgoPortal.clickDsPortalGetStarted();
        Assert.assertEquals(driver.getCurrentUrl(), appURL + "home");
        LoggerFactory.getLogger().info("Navigated to Home page");
    }
}

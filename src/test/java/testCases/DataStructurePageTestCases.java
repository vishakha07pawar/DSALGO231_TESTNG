package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.DataStructurePage;
import pageObjects.HomePage;
import utils.LoggerFactory;
import utils.TestDataProviders;

public class DataStructurePageTestCases extends BaseTest {

    private DataStructurePage dataStructurePage;

    @BeforeMethod
    public void DataStructurePageBeforeMethod() {
        signIntoHomePage();
        homePage.clickGetStartedButton("Data Structures-Introduction");
        dataStructurePage = new DataStructurePage(driver);
    }

    @AfterMethod
    public void queueAfterMethod() {
        homePage.clickSignOut();
    }

    @Test(priority = 1)
    public void verifyDatastructureHeaderIsVisible() {

        Assert.assertTrue(dataStructurePage.HeaderTitleVisible());
        LoggerFactory.getLogger().info("User see the Data Structure-Introduction Header");

    }

    @Test(priority = 2)
    public void VerifyTopicCoveredHeaderForDatastructurePage() {
        Assert.assertTrue(dataStructurePage.TopicCoveredTitleForLinksVisisble());
        LoggerFactory.getLogger().info("User see the Topic Covered Header for Links");
    }

    @Test(priority = 3, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
    public void DataStructurePageTopicLinkVisible(String expectedTopicLink) {
        Assert.assertTrue(dataStructurePage.getTopics(expectedTopicLink));
        LoggerFactory.getLogger().info("User Can see Topics Link in Ds-introduction page");
    }

    @Test(priority = 4, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
    public void userClicksTopicLinkOnDatastructurepage(String expectedDSTopicLink) {
        dataStructurePage.clickTopicLink(expectedDSTopicLink);
        LoggerFactory.getLogger().info("Clicked On Topic :" + expectedDSTopicLink);
        String actualHeader = dataStructurePage.getHeaderForLinks();
        Assert.assertTrue(actualHeader.contains(expectedDSTopicLink));
        LoggerFactory.getLogger().info("User can see Header for there Respective link");
    }

    @Test(priority = 5, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
    public void verifyTryHereBtnVisibleForDSTopics(String expectedTopicLink) throws InterruptedException {
        dataStructurePage.clickTopicLink(expectedTopicLink);
        Assert.assertTrue(dataStructurePage.tryHereBtn());
        LoggerFactory.getLogger().info("User clicks the DsTopics to verify Try Here button");
    }

    @Test(priority = 6, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
    public void verifyIsPracticeQuestionsLinkOnDsTopicsVisible(String expectedTopicLink) {
        dataStructurePage.clickTopicLink(expectedTopicLink);
        Assert.assertTrue(dataStructurePage.isPracticeQuestionsLinkOnDsTopicsVisible());
        LoggerFactory.getLogger().info("User clicks DsTopics link should see Practice Questions link");
    }

    @Test(priority = 7, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
    public void VerifyTopicLinks(String expectedLink) {
        LoggerFactory.getLogger().info("User clicks the DsTopics in DataStructurePage");
        dataStructurePage.clickTopicLink(expectedLink);
        String expectedUrl = expectedLink.toLowerCase().replace(" ", "-");
        Assert.assertTrue(dataStructurePage.getPageURL().toLowerCase().contains(expectedUrl));
        LoggerFactory.getLogger().info("Redirected To there respective page");
    }

    @Test(priority = 8, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
    public void verifyPracticeQuestionLinkUrl(String expectedLink) {
        LoggerFactory.getLogger().info("User clicks the DsTopics in DataStructurePage");
        dataStructurePage.clickTopicLink(expectedLink);
        dataStructurePage.clickPracticeQuestionsLinkOnDs();
        Assert.assertTrue(dataStructurePage.getPracticePageURL().contains("practice"));
    }

    @Test(priority = 9, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
    public void verifyTryHereBtnForRespectivePage(String expectedLink) {
        LoggerFactory.getLogger().info("User clicks the DsTopics in DataStructurePage");
        dataStructurePage.clickTopicLink(expectedLink);
        dataStructurePage.tryHereBtnForLinks();
        Assert.assertTrue(dataStructurePage.getPageURL().contains("tryEditor"));
        driver.get(appURL + "home");
        homePage = new HomePage(driver);
        LoggerFactory.getLogger().info("User clicks the Try Here button For the respective page");
    }
}
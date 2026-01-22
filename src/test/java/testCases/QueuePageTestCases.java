package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.QueuePage;
import utils.ConfigReader;
import utils.LoggerFactory;

public class QueuePageTestCases extends BaseTest {
    private QueuePage queuePage;

    @BeforeMethod
    public void queueBeforeMethod() {
        signIntoHomePage();
        homePage.clickGetStartedButton("Queue");
        queuePage = new QueuePage(driver);
    }

    @AfterMethod
    public void queueAfterMethod() {
        homePage.clickSignOut();
    }

    @Test(priority = 1)
    public void verifyHeaderForQueuePageVisible() {
        Assert.assertTrue(queuePage.isQueueHeaderVisible());
        LoggerFactory.getLogger().info("Header for Queue page visible");
    }

    @Test(priority = 2)
    public void verifyTopicsCoveredHeaderForQueuePageVisible() {
        Assert.assertTrue(queuePage.isTopicsCoveredHeaderForQueueVisible());
        LoggerFactory.getLogger().info("Topics Covered header for Queue page visible");
    }

    @Test(priority = 3, dataProvider = "queuePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyQueuePageLinksVisible(String queuePageLink) {
        Assert.assertTrue(queuePage.isQueueLinkVisible(queuePageLink));
        LoggerFactory.getLogger().info("Link of Queue page {} visible", queuePageLink);
    }

    @Test(priority = 4, dataProvider = "queuePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyHeaderOfQueuePageLinksVisible(String queuePageLink) {
        queuePage.clickQueueTopicLink(queuePageLink);
        String actualQueueLinkHeader = queuePage.getQueueLinkTopicHeader();
        Assert.assertEquals(actualQueueLinkHeader, queuePageLink);
        LoggerFactory.getLogger().info("Header of the Queue page link {} visible", queuePageLink);
    }

    @Test(priority = 5, dataProvider = "queuePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyTryHereButtonOnQueuePageLinkVisible(String queuePageLink) {
        queuePage.clickQueueTopicLink(queuePageLink);
        Assert.assertTrue(queuePage.isTryHereButtonOnQueueLinkPageVisible());
        LoggerFactory.getLogger().info("Try here button on the Queue page link {} visible", queuePageLink);
    }

    @Test(priority = 6, dataProvider = "queuePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyPracticeQuestionsOnTheQueueTopicPageVisible(String queuePageLink) {
        queuePage.clickQueueTopicLink(queuePageLink);
        Assert.assertTrue(queuePage.isPracticeQuestionsLinkOnQueueVisible());
        LoggerFactory.getLogger().info("Practice Questions on the Queue topic page Visible");
    }

    @Test(priority = 7, dataProvider = "queuePageTopicsNavigationLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyNavigationToQueueTopicsPages(String queuePageLink, String queueTopicPage) {
        queuePage.clickQueueTopicLink(queuePageLink);
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains(queueTopicPage));
        LoggerFactory.getLogger().info("Navigated to Queue Topics Page {}", queueTopicPage);
    }

    @Test(priority = 8, dataProvider = "queuePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyNavigationToPracticeQuestionsPageOfQueueTopics(String queuePageLink) {
        queuePage.clickQueueTopicLink(queuePageLink);
        queuePage.clickPracticeQuestionsOnQueue();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("queue/practice"));
        LoggerFactory.getLogger().info("Navigated to Practice Questions page of Queue topics from {}", queuePageLink);
    }

    @Test(priority = 9, dataProvider = "queuePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyNavigationToTryEditorPageOfQueueTopic(String queuePageLink) {
        queuePage.clickQueueTopicLink(queuePageLink);
        queuePage.clickTryHereInQueueLinkPage();
        String currentURL = driver.getCurrentUrl();
        LoggerFactory.getLogger().info("current url {}", driver.getCurrentUrl());
        Assert.assertTrue(currentURL.contains("tryEditor"));
        LoggerFactory.getLogger().info("current url {}", driver.getCurrentUrl());
        driver.get(ConfigReader.getAppUrl() + "home");
        homePage = new HomePage(driver);
        LoggerFactory.getLogger().info("Navigated to try Editor page from link {}", queuePageLink);
    }
}


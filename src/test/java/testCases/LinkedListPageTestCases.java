package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePage;
import pageObjects.LinkedListPage;
import utils.LoggerFactory;
import utils.TestDataProviders;

public class LinkedListPageTestCases extends BaseTest {
    private LinkedListPage linkedListPage;

    @BeforeMethod
    public void linkedListBeforeMethod() {
        signIntoHomePage();
        homePage.clickGetStartedButton("Linked List");
        linkedListPage = new LinkedListPage(driver);
    }

    @AfterMethod
    public void linkedListAfterMethod() {
        homePage.clickSignOut();
    }

    @Test(priority = 1)
    public void verifyLinkedListPageHeader() {
        Assert.assertTrue(linkedListPage.HeaderTitleVisisble());
        LoggerFactory.getLogger().info("User see the Linked List Header");
    }

    @Test(priority = 2)
    public void VerifyTopicCoveredHeaderForLinkedlistPage() {
        Assert.assertTrue(linkedListPage.isTopicCoveredVisible());
        LoggerFactory.getLogger().info("User can see the Topic Covered Header for Links");
    }

    @Test(priority = 3, dataProvider = "TopicsForLinkedListPage", dataProviderClass = TestDataProviders.class)
    public void LinkedListPageTopicLinkVisible(String expectedTopicLink) {
        Assert.assertTrue(linkedListPage.TopicsLinkelistVisible(expectedTopicLink));
        LoggerFactory.getLogger().info("User Can see Topics Link in LinkedList Page");
    }

    @Test(priority = 4, dataProvider = "TopicsForLinkedListPage", dataProviderClass = TestDataProviders.class)
    public void userClicksTopicLinkOnLinkedListPage(String expectedHeaderForLink) {
        linkedListPage.clickTopicLink(expectedHeaderForLink);
        String actualHeader = linkedListPage.getHeaderForLinks();
        Assert.assertTrue(actualHeader.contains(expectedHeaderForLink));
        LoggerFactory.getLogger().info("User can see Header for there Respective link");

    }

    @Test(priority = 5, dataProvider = "TopicsForLinkedListPage", dataProviderClass = TestDataProviders.class)
    public void verifyTryHereBtnVisibleForLinklistPageTopics(String expectedTryHereBtn) {
        linkedListPage.clickTopicLink(expectedTryHereBtn);
        LoggerFactory.getLogger().info("User clicks the Links to verify Try Here button");
        Assert.assertTrue(linkedListPage.tryHereBtn());
        LoggerFactory.getLogger().info("User should see Try Here button For the respective page");
    }

    @Test(priority = 6, dataProvider = "TopicsForLinkedListPage", dataProviderClass = TestDataProviders.class)
    public void verifyIsPracticeQuestionsLinkOnLinklistTopicsVisible(String expectedTopicLink) {
        linkedListPage.clickTopicLink(expectedTopicLink);
        Assert.assertTrue(linkedListPage.isPracticeQuestionsLinkOnLinkedlistTopicsVisible());
        LoggerFactory.getLogger().info("User clicks LinkedListTopics link should see Practice Questions link");
    }

    @Test(priority = 7, dataProvider = "UrlForLinkedListPageLinks", dataProviderClass = TestDataProviders.class)
    public void VerifyTopicLinks(String LinkedListPageLink, String LinkedListPageTopic) {
        LoggerFactory.getLogger().info("User clicks the LinkedList Topics Links in LinkedListPage");
        linkedListPage.clickTopicLink(LinkedListPageLink);
        String actualUrl = driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains(LinkedListPageTopic));
        LoggerFactory.getLogger().info("Redirected To there respective page");
    }

    @Test(priority = 8, dataProvider = "TopicsForLinkedListPage", dataProviderClass = TestDataProviders.class)
    public void verifyPracticeQuestionLinkUrl(String expectedLink) {
        LoggerFactory.getLogger().info("User clicks the LinkedList Topics Links in LinkedListPage");
        linkedListPage.clickTopicLink(expectedLink);
        linkedListPage.clickPracticeQuestionsLinkOnLinklist();
        Assert.assertTrue(linkedListPage.getPracticePageURL().contains("practice"));
    }

    @Test(priority = 9, dataProvider = "TopicsForLinkedListPage", dataProviderClass = TestDataProviders.class)
    public void verifyTryHereBtnForRespectivePage(String expectedLink) {
        LoggerFactory.getLogger().info("User clicks the LinkedListTopics in LinkedListPage");
        linkedListPage.clickTopicLink(expectedLink);
        linkedListPage.tryHereBtnForLinks();
        Assert.assertTrue(driver.getCurrentUrl().contains("tryEditor"));
        driver.get(appURL + "home");
        homePage = new HomePage(driver);
        LoggerFactory.getLogger().info("User clicks the Try Here button For the respective page");
    }
}
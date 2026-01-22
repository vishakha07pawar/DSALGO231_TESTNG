package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.HomePage;
import pageObjects.TreePage;
import utils.ConfigReader;
import utils.LoggerFactory;

public class TreePageTestCases extends BaseTest {
    private TreePage treePage;

    @BeforeMethod
    public void treeBeforeMethod() {
        signIntoHomePage();
        homePage.clickGetStartedButton("Tree");
        treePage = new TreePage(driver);
    }

    @AfterMethod
    public void treeAfterMethod() {
        homePage.clickSignOut();
    }

    @Test(priority = 1)
    public void verifyTreeHeaderForTreePageVisible() {
        Assert.assertTrue(treePage.isTreeHeaderVisible());
        LoggerFactory.getLogger().info("Tree header for Tree page visible");
    }

    @Test(priority = 2)
    public void verifyTopicsCoveredHeaderForTreePageVisible() {
        Assert.assertTrue(treePage.isTopicsCoveredHeaderTreeVisible());
        LoggerFactory.getLogger().info("Topics covered header for Tree page visible");
    }

    @Test(priority = 3, dataProvider = "treePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyLinksOfTreePageVisible(String treePageLink) {
        Assert.assertTrue(treePage.isTreeLinkVisible(treePageLink));
        LoggerFactory.getLogger().info("Link of Tree page {} visible", treePageLink);
    }

    @Test(priority = 4, dataProvider = "treePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyHeaderOfTheTreePageLinkVisible(String treeLinkHeader) {
        treePage.clickTreeTopicLink(treeLinkHeader);
        String actualTreeLinkHeader = treePage.getTreeLinkHeader();
        Assert.assertEquals(actualTreeLinkHeader, treeLinkHeader);
        LoggerFactory.getLogger().info("Header for Tree page link {} visible", treeLinkHeader);
    }

    @Test(priority = 5, dataProvider = "treePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyTryHereButtonOnTheTreePageLinkVisible(String treePageLink) {
        treePage.clickTreeTopicLink(treePageLink);
        Assert.assertTrue(treePage.isTryHereButtonOnTreeLinkPageVisible());
        LoggerFactory.getLogger().info("Try here button on the Tree page link {} Visible", treePageLink);
    }

    @Test(priority = 6, dataProvider = "treePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyPracticeQuestionsOnTheTreeTopicPageVisible(String treePageLink) {
        treePage.clickTreeTopicLink(treePageLink);
        Assert.assertTrue(treePage.isPracticeQuestionsLinkOnTreeVisible());
        LoggerFactory.getLogger().info("Practice questions for Tree topic page {} Visible", treePageLink);
    }

    @Test(priority = 7, dataProvider = "treePageTopicsNavigationLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyNavigationToTreeTopicPages(String treeTopicPage, String redirectedPage) {
        treePage.clickTreeTopicLink(treeTopicPage);
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains(redirectedPage));
        LoggerFactory.getLogger().info("Navigated to Tree topic page {}", treeTopicPage);
    }

    @Test(priority = 8, dataProvider = "treePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyNavigationToPracticeQuestionsPageOfTreeTopics(String treePageLink) {
        treePage.clickTreeTopicLink(treePageLink);
        treePage.clickPracticeQuestionsOnTree();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("tree/practice"));
        LoggerFactory.getLogger().info("Navigated to Practice Questions page of Tree topic {}", treePageLink);
    }

    @Test(priority = 9, dataProvider = "treePageLinks", dataProviderClass = utils.TestDataProviders.class)
    public void verifyNavigationToTryEditorPageOfTreeTopic(String treePageLink) {
        treePage.clickTreeTopicLink(treePageLink);
        treePage.clickTryHereInTreeLinkPage();
        String currentURL = driver.getCurrentUrl();
        LoggerFactory.getLogger().info("current url {}", driver.getCurrentUrl());
        Assert.assertTrue(currentURL.contains("tryEditor"));
        LoggerFactory.getLogger().info("current url {}", driver.getCurrentUrl());
        driver.get(ConfigReader.getAppUrl() + "home");
        homePage = new HomePage(driver);
        LoggerFactory.getLogger().info("Navigated to try Editor page from link {}", treePageLink);
    }

}

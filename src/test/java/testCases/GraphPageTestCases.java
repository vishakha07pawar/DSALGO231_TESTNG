package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.GraphPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import utils.ExcelDataReader;
import utils.LoggerFactory;
import utils.TestDataProviders;

public class GraphPageTestCases extends BaseTest {

	private GraphPage graphPage;
	HomePage homePage;
	SignInPage signInPage;
	String username = null;
	String password = null;

	@BeforeMethod
	public void baseQueue() {
		driver.get(ConfigReader.getAppUrl());
		username = ExcelDataReader.getValidUserName();
		password = ExcelDataReader.getValidPassword();
		homePage = dsAlgoPortal.clickDsPortalGetStarted();
		signInPage = homePage.clickSignInLink();
		homePage = signInPage.login(username, password);
		homePage.clickGetStartedButton("Graph");
		graphPage = new GraphPage(driver);
	}

	@Test(priority = 1)
	public void verifyGraphHeaderIsVisible() {

		Assert.assertTrue(graphPage.HeaderTitleVisible());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User see the Graph Header");
	}

	@Test(priority = 2)
	public void VerifyTopicCoveredHeaderForGraphPage() {

		Assert.assertTrue(graphPage.TopicHeaderVisible());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User see the Topic Covered Header for Graph");
	}

	@Test(priority = 3, dataProvider = "TopicsForGraphPage", dataProviderClass = TestDataProviders.class)
	public void GraphPageTopicLinkVisible(String expectedTopics) {

		Assert.assertTrue(graphPage.TopicsGraphVisible().contains(expectedTopics));
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User Can see Topics Covered For Graph");
	}

	@Test(priority = 4, dataProvider = "TopicsForGraphPage", dataProviderClass = TestDataProviders.class)
	public void userClicksTopicLinkOnGraphPage(String expectedgraphTopicLink) {
		graphPage.clickTopicLink(expectedgraphTopicLink);
		LoggerFactory.getLogger().info("Clicked On Topic :" + expectedgraphTopicLink);
		String actualHeader = graphPage.getHeaderForLinks();
		Assert.assertTrue(actualHeader.contains(expectedgraphTopicLink));
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User can see Header for there Respective link");

	}

	@Test(priority = 5, dataProvider = "TopicsForGraphPage", dataProviderClass = TestDataProviders.class)
	public void verifyTryHereBtnVisibleForGraphPageTopics(String expectedTryHereBtn) {
		graphPage.clickTopicLink(expectedTryHereBtn);
		LoggerFactory.getLogger().info("User clicks the Links to verify Try Here button");
		Assert.assertTrue(graphPage.tryHereBtn());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User should see Try Here button For the respective page");
	}

	@Test(priority = 6, dataProvider = "TopicsForGraphPage", dataProviderClass = TestDataProviders.class)
	public void verifyIsPracticeQuestionsLinkOnLinklistTopicsVisible(String expectedTopicLink) {
		graphPage.clickTopicLink(expectedTopicLink);
		Assert.assertTrue(graphPage.isPracticeQuestionsLinkOnGraphTopicsVisible());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User clicks GraphTopics link should see Practice Questions link");
	}

	@Test(priority = 7, dataProvider = "UrlForGraphPage", dataProviderClass = TestDataProviders.class)
	public void VerifyTopicLinks(String graphPageLink, String graphPageTopic) {
		LoggerFactory.getLogger().info("User clicks the Graph Topics Links in graphPage");
		graphPage.clickTopicLink(graphPageLink);
		String actualUrl = driver.getCurrentUrl();
		Assert.assertTrue(actualUrl.contains(graphPageTopic));
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("Redirected To there respective page");
	}

	@Test(priority = 8, dataProvider = "TopicsForGraphPage", dataProviderClass = TestDataProviders.class)
	public void verifyPracticeQuestionLinkUrl(String expectedLink) {
		LoggerFactory.getLogger().info("User clicks the Graph Topics Links in graphPage");
		graphPage.clickTopicLink(expectedLink);
		graphPage.clickPracticeQuestionsLinkOnGraph();
		Assert.assertTrue(graphPage.getPracticePageURL().contains("practice"));
		homePage.clickSignOut();

	}

	@Test(priority = 9, dataProvider = "TopicsForGraphPage", dataProviderClass = TestDataProviders.class)
	public void verifyTryHereBtnForRespectivePage(String expectedLink) {
		LoggerFactory.getLogger().info("User clicks the GraphTopics in graphPage");
		graphPage.clickTopicLink(expectedLink);
		graphPage.tryHereBtnForLinks();
		Assert.assertTrue(driver.getCurrentUrl().contains("tryEditor"));
		driver.get(ConfigReader.getAppUrl() + "home");
		homePage = new HomePage(driver);
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User clicks the Try Here button For the respective page");

	}
}


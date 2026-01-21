package testCases;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.DataStructurePage;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import utils.ExcelDataReader;
import utils.LoggerFactory;
import utils.TestDataProviders;

public class DataStructurePageTestCases extends BaseTest {
	public HomePage homePage;
	private DataStructurePage dataStructurePage;
	SignInPage signInPage;
	String username = null;
	String password = null;

	@BeforeMethod
	public void DataStructurePageTest() {

		driver.get(ConfigReader.getAppUrl());
		dsAlgoPortal = new DsAlgoPortalPage(driver);
		username = ExcelDataReader.getValidUserName();
		password = ExcelDataReader.getValidPassword();
		homePage = dsAlgoPortal.clickDsPortalGetStarted();
		SignInPage signInPage = homePage.clickSignInLink();
		homePage = signInPage.login(username, password);
		LoggerFactory.getLogger().info("User clicks the Getting Started button in Home page");
		homePage.clickGetStartedButton("Data Structures-Introduction");
		dataStructurePage = new DataStructurePage(driver);

	}

	@Test(priority = 1)
	public void verifyDatastructureHeaderIsVisible() {

		Assert.assertTrue(dataStructurePage.HeaderTitleVisible());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User see the Data Structure-Introduction Header");

	}

	@Test(priority = 2)
	public void VerifyTopicCoveredHeaderForDatastructurePage() {

		Assert.assertTrue(dataStructurePage.TopicCoveredTitleForLinksVisisble());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User see the Topic Covered Header for Links");
	}

	@Test(priority = 3, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
	public void DataStructurePageTopicLinkVisible(String expectedTopicLink) {
		Assert.assertTrue(dataStructurePage.getTopics(expectedTopicLink));
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User Can see Topics Link in Ds-introduction page");
	}

	@Test(priority = 4, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
	public void userClicksTopicLinkOnDatastructurepage(String expectedDSTopicLink) {
		dataStructurePage.clickTopicLink(expectedDSTopicLink);
		LoggerFactory.getLogger().info("Clicked On Topic :" + expectedDSTopicLink);
		String actualHeader = dataStructurePage.getHeaderForLinks();
		Assert.assertTrue(actualHeader.contains(expectedDSTopicLink));
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User can see Header for there Respective link");

	}

	@Test(priority = 5, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
	public void verifyTryHereBtnVisibleForDSTopics(String expectedTopicLink) throws InterruptedException {
		dataStructurePage.clickTopicLink(expectedTopicLink);
		Assert.assertTrue(dataStructurePage.tryHereBtn());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User clicks the DsTopics to verify Try Here button");
	}

	@Test(priority = 6, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
	public void verifyIsPracticeQuestionsLinkOnDsTopicsVisible(String expectedTopicLink) {
		dataStructurePage.clickTopicLink(expectedTopicLink);
		Assert.assertTrue(dataStructurePage.isPracticeQuestionsLinkOnDsTopicsVisible());
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User clicks DsTopics link should see Practice Questions link");
	}

	@Test(priority = 7, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
	public void VerifyTopicLinks(String expectedLink) {
		LoggerFactory.getLogger().info("User clicks the DsTopics in DataStructurePage");
		dataStructurePage.clickTopicLink(expectedLink);
		String expectedUrl = expectedLink.toLowerCase().replace(" ", "-");
		Assert.assertTrue(dataStructurePage.getPageURL().toLowerCase().contains(expectedUrl));
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("Redirected To there respective page");
	}

	@Test(priority = 8, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
	public void verifyPracticeQuestionLinkUrl(String expectedLink) {
		LoggerFactory.getLogger().info("User clicks the DsTopics in DataStructurePage");
		dataStructurePage.clickTopicLink(expectedLink);
		dataStructurePage.clickPracticeQuestionsLinkOnDs();
		Assert.assertTrue(dataStructurePage.getPracticePageURL().contains("practice"));
		homePage.clickSignOut();
	}

	@Test(priority = 9, dataProvider = "TopicsForDataStructureIntroductionPage", dataProviderClass = TestDataProviders.class)
	public void verifyTryHereBtnForRespectivePage(String expectedLink) {
		LoggerFactory.getLogger().info("User clicks the DsTopics in DataStructurePage");
		dataStructurePage.clickTopicLink(expectedLink);
		dataStructurePage.tryHereBtnForLinks();
		Assert.assertTrue(dataStructurePage.getPageURL().contains("tryEditor"));
		driver.get(ConfigReader.getAppUrl() + "home");
		homePage = new HomePage(driver);
		homePage.clickSignOut();
		LoggerFactory.getLogger().info("User clicks the Try Here button For the respective page");
	}

}


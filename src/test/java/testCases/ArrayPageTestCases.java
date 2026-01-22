package testCases;

import org.testng.Assert;
import base.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import pageObjects.ArrayPage;
import pageObjects.HomePage;
import utils.ConfigReader;
import utils.LoggerFactory;

import org.testng.annotations.Test;


public class ArrayPageTestCases extends BaseTest {
	private ArrayPage arrayPage;

	@BeforeMethod()
	public void arrayBeforeMethod() {
		signIntoHomePage();
		homePage.clickGetStartedButtonOfGivenDsType("Array");
		arrayPage = new ArrayPage(driver);
	}

	@AfterMethod()
	public void arrayAfterMethod() {
		homePage.clickSignOut();
	}

	@Test(priority = 1)
	public void verifyArrayHeaderForArrayPageIsVisible() {
		Assert.assertTrue(arrayPage.isArrayHeaderVisible());
	}

	@Test(priority = 2)
	public void verifyTopicscoveredHeaderForArrayPageIsVisible() {
		Assert.assertTrue(arrayPage.isTopicsCoveredHeaderForArrayVisible());
	}

	@Test(priority = 3, dataProvider = "arrayTopics", dataProviderClass = utils.TestDataProviders.class)
	public void verifyArrayTopicsLinkOnArrayPageVisible(String topic, String urlPart) {
		Assert.assertTrue(arrayPage.isArrayLinkVisible(topic));
	}

	@Test(priority = 4, dataProvider = "arrayHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyHeaderOfArrayTopicsVisible(String expectedArrayLinkHeader) {
		arrayPage.clickArrayTopicLink(expectedArrayLinkHeader);
		String actualArrayLinkHeader = arrayPage.getArrayLinksTopicHeader();
		Assert.assertEquals(actualArrayLinkHeader, expectedArrayLinkHeader);
	}

	@Test(priority = 5, dataProvider = "arrayTopics", dataProviderClass = utils.TestDataProviders.class)
	public void verifyNavigationtoStackTopicsPages(String topicName, String expectedUrlPart) {
		arrayPage.clickArrayTopicLink(topicName);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains(expectedUrlPart));
		Assert.assertTrue(arrayPage.isTryHereButtonVisible(), "Try Here button not visible for topic: " + topicName);
	}

	@Test(priority = 6, dataProvider = "arrayHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyTryHereButtonOnStackPageLinkVisible(String ArrayPageLink) {
		arrayPage.clickArrayTopicLink(ArrayPageLink);
		Assert.assertTrue(arrayPage.isTryHereButtonVisible());
		LoggerFactory.getLogger().info("Try here button on the Queue page link {} visible", ArrayPageLink);
	}

	@Test(priority = 7, dataProvider = "arrayHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyNavigationToTryEditorPageOfArrayTopics(String topic) {
		arrayPage.clickTryHereForTopic(topic);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("tryEditor"));
		driver.get(ConfigReader.getAppUrl() + "home");
		homePage = new HomePage(driver);
	}

	@Test(priority = 8, dataProvider = "arrayHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyPracticeQuestionsLinkInTheArrayTopicsPageVisible(String topics) {
		arrayPage.clickArrayTopicLink(topics);
		Assert.assertTrue(arrayPage.isPracticeQuestionsLinkOnArrayPageVisible());
	}

	@Test(priority = 9, dataProvider = "arrayHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyPracticeQuestionsPageOfArrayTopics(String topicName) {
		arrayPage.clickArrayTopicLink(topicName);
		arrayPage.clickPracticeQuestionsTopicLink();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("array/practice"));
	}

	@Test(priority = 10, dataProvider = "validInvalidPythonCode", dataProviderClass = utils.TestDataProviders.class)
	public void VerifyAppropriateResultVisibleForGivenCode(String inputCode, String expectedResult,
			String expectedMessage) throws InterruptedException {
		arrayPage.navigateToPage(ConfigReader.getAppUrl() + "array/practice");
		arrayPage.clickAllPracticeQuestionsAndClearEditor(inputCode, expectedResult, expectedMessage);
	}
}

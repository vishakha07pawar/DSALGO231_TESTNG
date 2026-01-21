package testCases;

import org.testng.Assert;
import base.BaseTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import pageObjects.ArrayPage;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import utils.ConfigReader;
import org.testng.annotations.Test;
import utils.ExcelDataReader;
import java.io.IOException;
import java.util.List;

public class ArrayPageTestCases extends BaseTest {
	private HomePage homePage;
	private SignInPage signInPage;
	private ArrayPage arrayPage;
	String username = null;
	String password = null;

	@BeforeClass()
	public void initPages() throws IOException {
		driver.get(ConfigReader.getAppUrl());
		dsAlgoPortal = new DsAlgoPortalPage(driver);
		homePage = dsAlgoPortal.clickDsPortalGetStarted();
		signInPage = homePage.clickSignInLink();
		username = ExcelDataReader.getValidUserName();
		password = ExcelDataReader.getValidPassword();
		signInPage.login(username, password);
		homePage.clickGetStartedButtonOfGivenDsType("Array");
		arrayPage = new ArrayPage(driver);
	}

	@BeforeMethod
	public void baseArray() {
		arrayPage.navigateToPage(ConfigReader.getAppUrl() + "array");
	}

	@Test(priority = 1)
	public void userShouldSeeArrayheaderforarraypage() {
		Assert.assertTrue(arrayPage.isArrayHeaderVisible());
	}

	@Test(priority = 2)
	public void user_should_see_topics_covered_header_for_array_page() {
		Assert.assertTrue(arrayPage.isTopicsCoveredHeaderForArrayVisible());
	}

	@Test(priority = 3, dataProvider = "arrayTopics", dataProviderClass = utils.TestDataProviders.class)
	public void user_should_see_link_of_array_page(String topic, String header, String urlPart) {
		Assert.assertTrue(arrayPage.isArrayLinkVisible(topic));
	}

	@Test(priority = 4, dataProvider = "arrayTopics", dataProviderClass = utils.TestDataProviders.class)
	public void user_clicks_link_on_the_array_page(String topic, String expectedHeader, String urlPart) {
		arrayPage.clickArrayTopicLink(topic);
	}

	@Test(priority = 5, dataProvider = "arrayHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void user_should_see_header_of_the_respective_array_page(String expectedArrayLinkHeader) {
		arrayPage.clickArrayTopicLink(expectedArrayLinkHeader);
		String actualArrayLinkHeader = arrayPage.getArrayLinksTopicHeader();
		Assert.assertEquals(actualArrayLinkHeader, expectedArrayLinkHeader);
	}

	@Test(priority = 6, dataProvider = "arrayTopics", dataProviderClass = utils.TestDataProviders.class)
	public void user_should_be_directed_to_page_of_array_page(String topicName, String expectedHeader,
			String expectedUrlPart) {
		arrayPage.navigateToPage(ConfigReader.getAppUrl() + "array");
		arrayPage.clickArrayTopicLink(topicName);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains(expectedUrlPart));

		Assert.assertTrue(arrayPage.isTryHereButtonVisible(), "Try Here button not visible for topic: " + topicName);
	}

	@Test(priority = 7)
	public void userClicksTryHereButtonInTheRespectiveArrayPage() {
		arrayPage.navigateToPage(ConfigReader.getAppUrl() + "array");

		List<String> topics = arrayPage.getAllArrayTopicLinks();

		for (String topic : topics) {
			arrayPage.clickTryHereForTopic(topic);
			String currentURL = driver.getCurrentUrl();
			Assert.assertTrue(currentURL.contains("tryEditor"));
			// Navigate back to Array main page before next iteration
			arrayPage.navigateToPage(ConfigReader.getAppUrl() + "array");
		}
	}

	@Test(priority = 8)
	public void userClicksArrayTopicsLinkOnTheArrayPageForThePracticeQuestions() {
		arrayPage.clickArrayInPythonLink();
	}

	@Test(priority = 9)
	public void user_clicks_the_practice_questions_from_the_topics_in_the_array_page() {
		arrayPage.clickArrayInPythonLink();
		arrayPage.clickPracticeQuestionsTopicLink();
	}

	@Test(priority = 10)
	public void userNavigatesToTheArrayPracticePage() {
		appURL = ConfigReader.getAppUrl();
		driver.get(appURL + "array/practice");
	}

	@Test(priority = 11, dataProvider = "validInvalidPythonCode", dataProviderClass = utils.TestDataProviders.class)
	public void userWriteTheCodeAndSeeAppropriateResult(String inputCode, String expectedResult, String expectedMessage)
			throws InterruptedException {
		arrayPage.navigateToPage(ConfigReader.getAppUrl() + "array/practice");
		arrayPage.clickAllPracticeQuestionsAndClearEditor(inputCode, expectedResult, expectedMessage);
	}
}

package testCases;

import java.io.IOException;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import base.BaseTest;
import pageObjects.DsAlgoPortalPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import pageObjects.StackPage;
import utils.ConfigReader;
import utils.ExcelDataReader;

public class StackPageTestCases extends BaseTest {
	private HomePage homePage;
	private SignInPage signInPage;
	private StackPage stackPage;
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
		stackPage = new StackPage(driver);
	}

	@BeforeMethod
	public void baseStack() {
		stackPage.navigateToPage(ConfigReader.getAppUrl() + "stack");
	}

	@Test(priority = 1)
	public void user_should_see_stack_header_for_stack_page() {
		Assert.assertTrue(stackPage.isStackHeaderVisible());
	}

	@Test(priority = 2)
	public void user_should_see_topics_covered_header_for_stack_page() {
		Assert.assertTrue(stackPage.isTopicsCoveredHeaderForStackVisible());
	}

	@Test(priority = 3, dataProvider = "stackTopics", dataProviderClass = utils.TestDataProviders.class)
	public void user_should_see_link_of_stack_sage(String topic, String header, String urlPart) {
		Assert.assertTrue(stackPage.isStackLinkVisible(topic));
	}

	@Test(priority = 4, dataProvider = "stackTopics", dataProviderClass = utils.TestDataProviders.class)
	public void user_clicks_link_on_the_stack_page(String topic, String expectedHeader, String urlPart) {
		stackPage.clickStackTopicLink(topic);
	}

	@Test(priority = 5, dataProvider = "stackHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void user_should_see_header_of_the_respective_stack_page(String expectedStackLinkHeader) {
		stackPage.clickStackTopicLink(expectedStackLinkHeader);
		String actualStackLinkHeader = stackPage.getStackLinksTopicHeader();
		Assert.assertEquals(actualStackLinkHeader, expectedStackLinkHeader);
	}

	@Test(priority = 6, dataProvider = "stackTopics", dataProviderClass = utils.TestDataProviders.class)
	public void user_should_be_directed_to_page_of_stack_page(String topicName, String expectedHeader,
			String expectedUrlPart) {
		stackPage.navigateToPage(ConfigReader.getAppUrl() + "stack");
		stackPage.clickStackTopicLink(topicName);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains(expectedUrlPart));
	}

	@Test(priority = 7)
	public void userClicksTryHereButtonInTheRespectiveStackPage() {
		stackPage.navigateToPage(ConfigReader.getAppUrl() + "stack");

		List<String> topics = stackPage.getAllStackTopicLinks();

		for (String topic : topics) {
			stackPage.clickTryHereInStackLinkPage(topic);
			String currentURL = driver.getCurrentUrl();
			Assert.assertTrue(currentURL.contains("tryEditor"));
			// Navigate back to Array main page before next iteration
			stackPage.navigateToPage(ConfigReader.getAppUrl() + "stack");
		}
	}

	@Test(priority = 8)
	public void userClicksPracticeQuestionsLinkInTheRespectiveStackPage() {
		stackPage.clickOperationsInStackLink();
		stackPage.clickPracticeQuestionsOnStack();
	}

	@Test(priority = 9)
	public void userShouldBeRedirectedToPracticeQuestionsPageOfStackTopics() {
		appURL = ConfigReader.getAppUrl();
		driver.get(appURL + "stack/practice");
	}
}

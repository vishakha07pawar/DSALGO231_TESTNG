package testCases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.BaseTest;
import pageObjects.HomePage;
import pageObjects.StackPage;

public class StackPageTestCases extends BaseTest {
	private StackPage stackPage;

	@BeforeMethod
	public void stackBeforeMethod() {
		signIntoHomePage();
		homePage.clickGetStartedButton("Stack");
		stackPage = new StackPage(driver);
	}

	@AfterMethod()
	public void stackAfterMethod() {
		homePage.clickSignOut();
	}

	@Test(priority = 1)
	public void verifyStackHeaderForStackPageIsVisible() {
		Assert.assertTrue(stackPage.isStackHeaderVisible());
	}

	@Test(priority = 2)
	public void verifyTopicscoveredHeaderForStackPageIsVisible() {
		Assert.assertTrue(stackPage.isTopicsCoveredHeaderForStackVisible());
	}

	@Test(priority = 3, dataProvider = "stackTopics", dataProviderClass = utils.TestDataProviders.class)
	public void verifyStackTopicsLinkOnStackPageVisible(String topic, String urlPart) {
		Assert.assertTrue(stackPage.isStackLinkVisible(topic));

	}

	@Test(priority = 4, dataProvider = "stackHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyHeaderOfStackTopicsVisible(String expectedStackLinkHeader) {
		stackPage.clickStackTopicLink(expectedStackLinkHeader);
		String actualStackLinkHeader = stackPage.getStackLinksTopicHeader();
		Assert.assertEquals(actualStackLinkHeader, expectedStackLinkHeader);
	}

	@Test(priority = 5, dataProvider = "stackTopics", dataProviderClass = utils.TestDataProviders.class)
	public void verifyNavigationtoStackTopicsPages(String topicName, String expectedUrlPart) {
		stackPage.clickStackTopicLink(topicName);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains(expectedUrlPart));
	}

	@Test(priority = 6, dataProvider = "stackHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyTryHereButtonOnStackPageLinkVisible(String StackPageLink) {
		stackPage.clickStackTopicLink(StackPageLink);
		Assert.assertTrue(stackPage.isTryHereButtonOnStackLinkPageVisible());
	}

	@Test(priority = 7, dataProvider = "stackHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyNavigationToTryEditorPageOfStackTopics(String topic) {
		stackPage.clickTryHereInStackLinkPage(topic);
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("tryEditor"));
		driver.get(appURL + "home");
		homePage = new HomePage(driver);
	}

	@Test(priority = 8, dataProvider = "stackHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyPracticeQuestionsLinkInTheStackTopicsPageVisible(String topics) {
		stackPage.clickStackTopicLink(topics);
		Assert.assertTrue(stackPage.isPracticeQuestionsLinkOnStackPageVisible());
	}

	@Test(priority = 9, dataProvider = "stackHeaders", dataProviderClass = utils.TestDataProviders.class)
	public void verifyPracticeQuestionsPageOfStackTopics(String topicName) {
		stackPage.clickStackTopicLink(topicName);
		stackPage.clickPracticeQuestionsOnStack();
		String currentURL = driver.getCurrentUrl();
		Assert.assertTrue(currentURL.contains("stack/practice"));
	}
}

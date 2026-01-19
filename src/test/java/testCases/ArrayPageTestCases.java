package testCases;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import factory.DriverManager;
import pageObjects.ArrayPage;
import pageObjects.HomePage;
import pageObjects.SignInPage;
import pageObjects.TryEditorPage;
import utils.ConfigReader;
import utils.Constants;
import utils.SignInDataProvider;
import utils.ValidCredentialDataReader;

public class ArrayPageTestCases extends BaseTest{
	private HomePage homePage;
	private SignInPage signInPage;
	private ArrayPage arrayPage;
	private TryEditorPage tryEditorPage;
	
	@BeforeClass(alwaysRun = true)
	public void initPages() throws IOException {
		driver = DriverManager.getDriver();
		homePage = new HomePage(driver);
		homePage = dsAlgoPortal.clickDsPortalGetStarted();
		homePage.clickSignInLink();
		signInPage=new SignInPage(driver);
		signInPage.login(ValidCredentialDataReader.getValidUserName(),ValidCredentialDataReader.getValidPassword());
		arrayPage = new ArrayPage(driver);		
		arrayPage.navigateToPage(ConfigReader.getArrayUrl());
		//tryEditorPage=new TryEditorPage(driver);
	}
	
	@BeforeMethod(alwaysRun=true)
	public void initMethods()
	{
		arrayPage.navigateToPage(ConfigReader.getArrayUrl());
	}
	
	 @DataProvider(name = "arrayTopics")
	    public Object[][] arrayTopicsData() {
	        return new Object[][] {
	                { "Arrays in Python", "Arrays in Python", "arrays-in-python" },
	                { "Arrays Using List", "Arrays Using List", "arrays-using-list" },
	                { "Basic Operations in Lists", "Basic Operations in Lists", "basic-operations-in-lists" }
	        };
	    }
	 @DataProvider(name = "arrayTopicsurlcheck")
	 public Object[][] arrayTopics() {
	     return new Object[][]{
	         {"arrays-in-python"},
	         {"arrays-using-list"},
	         {"basic-operations-in-lists"}
	     };
	 }

	    @DataProvider(name = "practiceQuestions")
	    public Object[][] practiceQuestionsData() {
	        return new Object[][] {
	                { "Search the array" },
	                { "Max Consecutive Ones" },
	                { "Find Numbers with Even Number of Digits" },
	                { "Squares of a Sorted Array" }
	        };
	    }
	    @DataProvider(name = "arrayHeaders")
	    public Object[][] arrayHeaders() {
	        return new Object[][]{
	            {"Arrays in Python"},
	            {"Arrays Using List"},
	            {"Basic Operations in Lists"}
	        };
	    }
	    
	@Test(priority = 2)
	    public void user_should_see_array_header_for_array_page() {
		 	arrayPage.navigateToPage(ConfigReader.getArrayUrl());
	        Assert.assertTrue(arrayPage.isArrayHeaderVisible());
	    }

	@Test(priority = 3)
	    public void user_should_see_topics_covered_header_for_array_page() {
	        Assert.assertTrue(arrayPage.isTopicsCoveredHeaderForArrayVisible());
	    }

	@Test(priority = 4, dataProvider = "arrayTopics")
	    public void user_should_see_link_of_array_page(String topic, String header, String urlPart) {
	        Assert.assertTrue(arrayPage.isArrayLinkVisible(topic));
	    }
	    

	@Test(priority = 5, dataProvider = "arrayTopics")
	    public void user_clicks_link_on_the_array_page(String topic,String expectedHeader,String urlPart) {
	        arrayPage.clickArrayTopicLink(topic);
	    }

	@Test(priority = 6,dataProvider = "arrayHeaders")
	    public void user_should_see_header_of_the_respective_array_page(String expectedArrayLinkHeader) {
		arrayPage.clickArrayTopicLink(expectedArrayLinkHeader);
	        String actualArrayLinkHeader = arrayPage.getArrayLinksTopicHeader();
	        Assert.assertEquals(actualArrayLinkHeader, expectedArrayLinkHeader);
	    }

	@Test(priority = 7, dataProvider = "arrayTopics")
	    public void user_should_be_directed_to_page_of_array_page(String topicName,String expectedHeader,
	            String expectedUrlPart) {
		arrayPage.navigateToPage(ConfigReader.getArrayUrl());
		arrayPage.clickArrayTopicLink(topicName);
	        String currentURL = driver.getCurrentUrl();
	        Assert.assertTrue(currentURL.contains(expectedUrlPart));
	        
	        Assert.assertTrue(arrayPage.isTryHereButtonVisible(),
	                "Try Here button not visible for topic: " + topicName);
	    }

	@Test(priority = 8)
	    public void userClicksTryHereButtonInTheRespectiveArrayPage() {
		 arrayPage.navigateToPage(ConfigReader.getArrayUrl());

		    List<String> topics = arrayPage.getAllArrayTopicLinks();

		    for (String topic : topics) {
		        arrayPage.clickTryHereForTopic(topic);
		        String currentURL = driver.getCurrentUrl();
		        Assert.assertTrue(currentURL.contains("tryEditor"));

		        // Navigate back to Array main page before next iteration
		        arrayPage.navigateToPage(ConfigReader.getArrayUrl());
		    }
	    }

	@Test(priority = 10)
	    public void userClicksArrayTopicsLinkOnTheArrayPageForThePracticeQuestions() {
	        arrayPage.clickArrayInPythonLink();
	    }

	@Test(priority = 11)
	    public void user_clicks_the_practice_questions_from_the_topics_in_the_array_page() {
			arrayPage.clickArrayInPythonLink();
	        arrayPage.clickPracticeQuestionsTopicLink();
	    }

	@Test(priority = 12,dataProvider = "practiceQuestions")
	    public void userShouldSeeLinkOnPracticeQuestionsPage(String topicName) {
		arrayPage.clickArrayInPythonLink();
	    arrayPage.clickPracticeQuestionsTopicLink();
	        Assert.assertTrue(arrayPage.isPracticeQuestionLinkDisplayed(topicName),
	                "Practice question link not visible: " + topicName);
	    }

	@Test(priority = 13)
	    public void userClicksPracticeQuestionsLinkInTheArraysInPythonPage() {
	        arrayPage.clickArraysInPythonLink();
	    }

	@Test(priority = 14)
	    public void userShouldBeRedirectedToPracticeQuestionsPageOfArrayTopics() {
	        arrayPage.clickPracticeQuestionsFromArraysInPython();
	        String currentURL = driver.getCurrentUrl();
	        Assert.assertTrue(currentURL.contains("array/practice"));
	    }

	@Test(priority = 15)
	    public void userNavigatesToTheArrayPracticePage() {
	    		appURL = ConfigReader.getAppUrl();
	        driver.get(appURL + "array/practice");       
	    }
	
	    @Test(priority = 16)
	    public void userClicksSearchTheArrayLinkInThePracticePage() {
	    		arrayPage.clickArraysInPythonLink();
	        arrayPage.clickPracticeQuestionsTopicLink();
	        arrayPage.clickSearchTheArray();
	        arrayPage.clearEditorText();
	    }
	    @Test(priority = 17)
	    public void userClicksMaxConsecutiveOnesLinkInThePracticePage() {
	    		arrayPage.clickArraysInPythonLink();
	        arrayPage.clickPracticeQuestionsTopicLink();
	        arrayPage.clickMaxConsecutiveOnes();
	        arrayPage.clearEditorText();
	    }

	    @Test(priority = 18)
	    public void userClicksFindNumbersWithEvenNumberOfDigitsLinkInThePracticePage() {
	    		arrayPage.clickArraysInPythonLink();
	        arrayPage.clickPracticeQuestionsTopicLink();
	        arrayPage.clickFindNumberswithEvenNumberofDigits();
	        arrayPage.clearEditorText();
	    }

	    @Test(priority = 19)
	    public void userClicksSquaresOfASortedArrayLinkInThePracticePage() {
	    		arrayPage.clickArraysInPythonLink();
	        arrayPage.clickPracticeQuestionsTopicLink();
	        arrayPage.clickSquaresofaSortedArray();
	        arrayPage.clearEditorText();
	    }
/*
	    @Test(priority = 20, dataProvider = "tryEditorData",dataProviderClass = SignInDataProvider.class)
	    public void userRunsTheFollowingPythonCodeAndSeeTheResult(String scenarioName) {
	    		arrayPage.clickArraysInPythonLink();
	        arrayPage.clickPracticeQuestionsTopicLink();
	        arrayPage.clickSearchTheArray();
	        arrayPage.clearEditorText();
	        DataReader dataReader = new DataReader("/testData/" + TEST_DATA_FILE_NAME);
	        Map<String, String> data = dataReader.getDataByScenarioName(Constants.TRY_EDITOR_PAGE_DATA_SHEET_NAME,
	                scenarioName);

	        String expectedResult = data.get(Constants.TRY_EDITOR_PAGE_DATA_COL_RESULT);
	        String expectedMessage = data.get(Constants.TRY_EDITOR_PAGE_DATA_COL_MESSAGE);
	        String inputCode = data.get(Constants.TRY_EDITOR_PAGE_DATA_COL_CODE);
	        arrayPage.enterDataIntoEditor(inputCode);
	        arrayPage.clickRunButton();	
	        
	        if (expectedResult.equalsIgnoreCase("print")) {
	            Assert.assertEquals(tryEditorPage.getPrintMessage(), expectedMessage);
	        } else if (expectedResult.equalsIgnoreCase("alert")) {
	            TryEditorPage.Result result = tryEditorPage.getAlertWindowVisibilityAndAlertText();
	            Assert.assertTrue(result.isAlertWindowVisible());
	            Assert.assertEquals(result.alertWindowMessage(), expectedMessage);
	        } else if (expectedResult.isEmpty() || expectedResult.isBlank()) {
	            Assert.assertEquals(tryEditorPage.getPrintMessage(), expectedMessage);
	        }
	    }
	    */	       
}

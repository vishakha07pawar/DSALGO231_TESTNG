package testCases;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.*;
import pageObjects.TryEditorPage;
import utils.*;

public class TryEditorTestCases extends BaseTest {

    private TryEditorPage tryEditorPage;

    @BeforeMethod
    public void baseTryEditorPage() {
        driver.get(ConfigReader.getAppUrl() + "tryEditor");
        tryEditorPage = new TryEditorPage(driver);
    }

    @Test(priority = 1)
    public void userShouldSeeRunButtonInTheTryEditorPage() {
        Assert.assertTrue(tryEditorPage.isRunButtonVisible());
        LoggerFactory.getLogger().info("userShouldSeeRunButtonInTheTryEditorPage");
    }

    @Test(priority = 2, dataProvider = "TryEditorData", dataProviderClass = utils.TestDataProviders.class)
	public void userWriteTheCodeAndSeeAppropriateResult(String scenarioName, String inputCode, String expectedResult,
			String expectedMessage) {

        LoggerFactory.getLogger().info("Executing scenario {}", scenarioName);

        tryEditorPage.enterDataIntoEditor(inputCode);

        tryEditorPage.Run();

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
}


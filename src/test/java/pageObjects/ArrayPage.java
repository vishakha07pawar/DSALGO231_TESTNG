package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.testng.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utils.LoggerFactory;

public class ArrayPage {
	private WebDriver driver;
	private WebDriverWait wait;

	private By headerArray = By.xpath("//h4[normalize-space()='Array']");
	private By headerTopicsCoveredArray = By.xpath("//p[@class='bg-secondary text-white']");
	private By headerArrayLinkTopic = By.xpath("//div[@class='col-sm']//strong//p");
	private By lnkArrayLinks = By.xpath("//a[@class='list-group-item']");
	private By btnTryHereArrayLinkPage = By.xpath("//a[normalize-space()='Try here>>>']");
	private By arrayInPythonTopicLink = By.xpath("//a[normalize-space()='Arrays in Python']");
	private By practiceQuestionsTopicLink = By.xpath("//a[normalize-space()='Practice Questions']");
	private By practiceQuestionsLinks = By.xpath("//a[@class='list-group-item']");
	private By codeEditor = By.xpath("//div[@class='CodeMirror-scroll']");
	private By runBtn = By.xpath("//button[normalize-space()='Run']");
	private By txtOutput = By.xpath("//pre[@id='output']");

	public ArrayPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public boolean isArrayHeaderVisible() {
		WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(headerArray));
		return header.getText().contains("Array");
	}

	public boolean isTopicsCoveredHeaderForArrayVisible() {
		return driver.findElement(headerTopicsCoveredArray).isDisplayed();
	}

	public String getArrayLinksTopicHeader() {
		return driver.findElement(headerArrayLinkTopic).getText();
	}

	public boolean isTryHereButtonVisible() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(btnTryHereArrayLinkPage)).isDisplayed();
	}

	public boolean isArrayLinkVisible(String arrayTopicLink) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lnkArrayLinks));
		List<WebElement> arrayLinks = driver.findElements(lnkArrayLinks);

		for (WebElement a : arrayLinks) {
			if (a.getText().equals(arrayTopicLink))
				return true;
		}
		return false;
	}

	public void clickArrayTopicLink(String arrayTopicLink) {
		By linkPath = By.xpath("//a[text() = '" + arrayTopicLink + "']");
		driver.findElement(linkPath).click();
	}

	public void clickTryHereForTopic(String topic) {
		clickArrayTopicLink(topic);
		wait.until(ExpectedConditions.elementToBeClickable(btnTryHereArrayLinkPage)).click();
	}

	public void clickAllPracticeQuestionsAndClearEditor(String inputCode, String expectedResult, String expectedMessage)
			throws InterruptedException {
		List<WebElement> links = driver.findElements(practiceQuestionsLinks);

		for (int i = 0; i < links.size(); i++) {
			List<WebElement> currentLinks = driver.findElements(practiceQuestionsLinks);
			WebElement link = currentLinks.get(i);
			link.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(codeEditor));
			clearEditorText();
			enterDataIntoEditor(inputCode);
			clickRunButton();
			if (expectedResult.equalsIgnoreCase("print")) {
				Assert.assertEquals(getPrintMessage(), expectedMessage);
			} else if (expectedResult.equalsIgnoreCase("alert")) {
				Result result = getAlertWindowVisibilityAndAlertText();
				Assert.assertTrue(result.isAlertWindowVisible());
				Assert.assertEquals(result.alertWindowMessage(), expectedMessage);
			} else if (expectedResult.isEmpty() || expectedResult.isBlank()) {
				Assert.assertEquals(getPrintMessage(), expectedMessage);
			}
			// Navigate back to the practice questions page
			driver.navigate().back();
		}
	}

	public record Result(boolean isAlertWindowVisible, String alertWindowMessage) {
	}

	private Result getAlertWindowVisibilityAndAlertText() {
		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(30));

			if (wait.until(ExpectedConditions.alertIsPresent()) == null) {
				LoggerFactory.getLogger().info("Alert windows is not visible");
				return new Result(false, "");
			} else {
				Alert alert = driver.switchTo().alert();
				String alertMessage = alert.getText();
				alert.accept();
				LoggerFactory.getLogger().info("Alert was present and accepted");
				return new Result(true, alertMessage);
			}
		} catch (Exception ex) {
			LoggerFactory.getLogger().error(ex.getStackTrace());
			return new Result(false, null);
		}
	}

	public String getPrintMessage() {
		try {
			return driver.findElement(txtOutput).getText();
		} catch (Exception e) {
			LoggerFactory.getLogger().error(e.getMessage());
			return "";
		}
	}

	public List<String> getAllArrayTopicLinks() {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(lnkArrayLinks));
		List<WebElement> links = driver.findElements(lnkArrayLinks);

		List<String> linkTexts = new ArrayList<>();
		for (WebElement link : links) {
			linkTexts.add(link.getText().trim());
		}
		return linkTexts;
	}

	public void clickPracticeQuestionsTopicLink() {
		wait.until(ExpectedConditions.elementToBeClickable(practiceQuestionsTopicLink)).click();
	}

	public void clearEditorText() {
		WebElement editor = wait.until(ExpectedConditions.visibilityOfElementLocated(codeEditor));
		editor.click();

		Actions actions = new Actions(driver);
		actions.keyDown(Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).sendKeys(Keys.DELETE).perform();
	}

	public void clickRunButton() {
		driver.findElement(runBtn).click();
	}

	public void clickArrayInPythonLink() {
		wait.until(ExpectedConditions.elementToBeClickable(arrayInPythonTopicLink)).click();
	}

	public void clickArraysInPythonLink() {
		driver.findElement(arrayInPythonTopicLink).click();
	}

	public void enterDataIntoEditor(String inputData) {
		WebElement txtDsCode = driver.findElement(codeEditor);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		Actions act = new Actions(driver);
		act.moveToElement(txtDsCode).click();
		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue('');");
		js.executeScript("document.querySelector('.CodeMirror').CodeMirror.setValue(arguments[0]);", inputData);
	}

	public void navigateToPage(String arrayUrl) {
		if (arrayUrl != null && !arrayUrl.isEmpty()) {
			driver.get(arrayUrl);
		}
	}
}

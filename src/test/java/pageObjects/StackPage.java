package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StackPage {

	private WebDriver driver;
	private WebDriverWait wait;

	private By headerStack = By.xpath("//h4[normalize-space()='Stack']");
	private By headerTopicsCoveredStack = By.xpath("//p[@class='bg-secondary text-white']");
	private By headerStackLinkTopic = By.xpath("//div[@class='col-sm']//strong//p");
	private By lnkStackLinks = By.xpath("//a[@class='list-group-item']");
	private By btnTryHereStackLinkPage = By.xpath("//a[normalize-space()='Try here>>>']");
	private By lnkPracticeQuestionsStackTopics = By.xpath("//a[normalize-space()='Practice Questions']");

	public StackPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public boolean isStackHeaderVisible() {
		return driver.findElement(headerStack).isDisplayed();
	}

	public boolean isTopicsCoveredHeaderForStackVisible() {
		return driver.findElement(headerTopicsCoveredStack).isDisplayed();
	}

	public boolean isStackLinkVisible(String stackTopicLink) {
		List<WebElement> stackLinks = driver.findElements(lnkStackLinks);

		for (WebElement s : stackLinks) {
			if (s.getText().equals(stackTopicLink))
				return true;
		}
		return false;
	}

	public void clickStackTopicLink(String stackTopicLink) {
		By linkPath = By.xpath("//a[text() = '" + stackTopicLink + "']");
		driver.findElement(linkPath).click();
	}

	public void clickTryHereInStackLinkPage(String topic) {
		clickStackTopicLink(topic);
		wait.until(ExpectedConditions.elementToBeClickable(btnTryHereStackLinkPage)).click();
	}

	public String getStackLinksTopicHeader() {
		return driver.findElement(headerStackLinkTopic).getText();
	}

	public void clickPracticeQuestionsOnStack() {
		driver.findElement(lnkPracticeQuestionsStackTopics).click();
	}

	public boolean isPracticeQuestionsLinkOnStackPageVisible() {
		return driver.findElement(lnkPracticeQuestionsStackTopics).isDisplayed();
	}

	public boolean isTryHereButtonOnStackLinkPageVisible() {
		return driver.findElement(btnTryHereStackLinkPage).isDisplayed();
	}
}
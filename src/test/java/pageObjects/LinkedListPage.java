package pageObjects;


import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LinkedListPage {
	private WebDriver driver;
	private By headerLinkedList = By.xpath("//h4[normalize-space()='Linked List']");
	private By headerTopics = By.xpath("//p[@class='bg-secondary text-white']");
	private By topicsLink = By.xpath("//a[@class='list-group-item']");
	private By verifyTopicLinksHeader = By.xpath("//div[@class='col-sm']/strong/p");
	private By tryHereBtn = By.xpath("//a[text()='Try here>>>']");
	private By lnkPracticeQuesLinkedListTopics = By.xpath("//a[normalize-space()='Practice Questions']");
	
	public LinkedListPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public boolean HeaderTitleVisisble() {
		return driver.findElement(headerLinkedList).isDisplayed();
		
	}
	public boolean TopicCoveredTitleForLinks() {
		return driver.findElement(headerTopics).isDisplayed();
		
	}
	public boolean TopicsLinkelistVisible(String linkListTopicLink) {
		List<WebElement> topicsName = driver.findElements(topicsLink);
		
		for (WebElement link : topicsName) {
			link.getText().trim().equalsIgnoreCase(linkListTopicLink);
			return true;
		}
		return false;

	}
	
	
	public void clickTopicLink(String linkListTopicLink) {
		
		By linkPath = By.xpath("//a[text() ='" + linkListTopicLink + "']");
		
        driver.findElement(linkPath).click();
	}
	public boolean isTopicCoveredVisible() {
		return driver.findElement(headerTopics).isDisplayed();
	}
	public String getHeaderForLinks() {
		
		  String topicH = driver.findElement(verifyTopicLinksHeader).getText(); 
		  return topicH;
		 
		

	}
	public boolean tryHereBtn() {
		return driver.findElement(tryHereBtn).isDisplayed();
		
		
	}
	public void tryHereBtnForLinks() {
		 driver.findElement(tryHereBtn).click();
		
	}
	public String getLinkedListPageURL() {
	return driver.getCurrentUrl();
	}
	public boolean isPracticeQuestionsLinkOnLinkedlistTopicsVisible() {
		return driver.findElement(lnkPracticeQuesLinkedListTopics).isDisplayed();
	}

	public void clickPracticeQuestionsLinkOnLinklist() {
		driver.findElement(lnkPracticeQuesLinkedListTopics).click();
	}

	public String getPracticePageURL() {

		return driver.getCurrentUrl();
	}

}

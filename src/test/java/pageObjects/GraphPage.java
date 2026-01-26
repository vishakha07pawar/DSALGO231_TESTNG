package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GraphPage {
	private WebDriver driver;
	private By headerGraph = By.xpath("//h4[normalize-space()='Graph']");
	private By headerTopics = By.xpath("//p[@class='bg-secondary text-white']");
	private By topicsLink = By.xpath("//a[@class='list-group-item']");
	private By verifyLinksHeader = By.xpath("//div[@class='col-sm']//strong//p");
	private By tryHereBtn = By.xpath("//a[normalize-space()='Try here>>>']");
	private By lnkPracticeQuesGraphTopics = By.xpath("//a[normalize-space()='Practice Questions']");

	public GraphPage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean HeaderTitleVisible() {
		return driver.findElement(headerGraph).isDisplayed();

	}

	public boolean TopicHeaderVisible() {
		return driver.findElement(headerTopics).isDisplayed();

	}

	public List<String> TopicsGraphVisible() {
		List<WebElement> topicsName = driver.findElements(topicsLink);
		ArrayList<String> topic = new ArrayList<>();
		for (WebElement link : topicsName) {
			topic.add(link.getText().trim());
		}
		return topic;

	}

	public void clickTopicLink(String linksName) {
		List<WebElement> topicsName = driver.findElements(topicsLink);

		for (WebElement link : topicsName) {
			if (link.getText().trim().equalsIgnoreCase(linksName)) {
				link.click();
				return;
			}
		}
	}

	public String getHeaderForLinks() {
		String topicH = driver.findElement(verifyLinksHeader).getText();
		return topicH;

	}

	public boolean tryHereBtn() {
		return driver.findElement(tryHereBtn).isDisplayed();

	}

	public void tryHereBtnForLinks() {
		driver.findElement(tryHereBtn).click();

	}

	public boolean isPracticeQuestionsLinkOnGraphTopicsVisible() {
		return driver.findElement(lnkPracticeQuesGraphTopics).isDisplayed();
	}

	public void clickPracticeQuestionsLinkOnGraph() {
		driver.findElement(lnkPracticeQuesGraphTopics).click();
	}

	public String getPracticePageURL() {

		return driver.getCurrentUrl();
	}
}

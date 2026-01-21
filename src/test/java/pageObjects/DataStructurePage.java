package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DataStructurePage {
	private WebDriver driver;
	private By headerTopics = By.xpath("//h4[normalize-space()='Data Structures-Introduction']");
	private By topicsLink = By.xpath("//a[@class='list-group-item']");
	private By headerDS = By.xpath("//h4[@class='bg-secondary text-white']");
	private By tryHereBtn = By.xpath("//a[text()='Try here>>>']");
	private By verifyTopicLinksHeader = By.xpath("//div[@class='col-sm']//strong//p");
	private By lnkPracticeQuestionsDsTopics = By.xpath("//a[normalize-space()='Practice Questions']");

	public DataStructurePage(WebDriver driver) {
		this.driver = driver;
	}

	public boolean HeaderTitleVisible() {
		return driver.findElement(headerDS).isDisplayed();
	}

	public boolean TopicCoveredTitleForLinksVisisble() {
		return driver.findElement(headerTopics).isDisplayed();
	}

	public boolean getTopics(String linkDsTopicLink) {
		List<WebElement> topicsName = driver.findElements(topicsLink);
		for (WebElement link : topicsName) {
			link.getText().trim().equalsIgnoreCase(linkDsTopicLink);
			return true;
		}
		return false;
	}

	public void clickTopicLink(String linkDsTopicLink) {

		By linkPath = By.xpath("//a[text()='" + linkDsTopicLink + "']");
		driver.findElement(linkPath).click();
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

	public String getDataStructurePageURL() {
		return driver.getCurrentUrl();
	}

	public boolean isPracticeQuestionsLinkOnDsTopicsVisible() {
		return driver.findElement(lnkPracticeQuestionsDsTopics).isDisplayed();
	}

	public void clickPracticeQuestionsLinkOnDs() {
		driver.findElement(lnkPracticeQuestionsDsTopics).click();
	}

	public String getPracticePageURL() {
		return driver.getCurrentUrl();
	}
	public String getPageURL() {
		return driver.getCurrentUrl();
	}
}

package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class TreePage {

    private WebDriver driver;

    private By headerTree = By.xpath("//h4[normalize-space()='Tree']");
    private By headerTopicsCoveredTree = By.xpath("//p[@class='bg-secondary text-white']");
    private By lnkTreeLinks = By.xpath("//a[@class='list-group-item']");
    private By headerTreeLink = By.xpath("//div[@class='col-sm']//strong//p");
    private By btnTryHereTreeLinkPage = By.xpath("//a[normalize-space()='Try here>>>']");
    private By lnkPracticeQuestionsTreeTopics = By.xpath("//a[normalize-space()='Practice Questions']");

    public TreePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isTreeHeaderVisible() {
        return driver.findElement(headerTree).isDisplayed();
    }

    public boolean isTopicsCoveredHeaderTreeVisible() {
        return driver.findElement(headerTopicsCoveredTree).isDisplayed();
    }

    public boolean isTreeLinkVisible(String treeTopicLink) {
        List<WebElement> treeLinks = driver.findElements(lnkTreeLinks);
        for (WebElement t : treeLinks) {
            if (t.getText().equals(treeTopicLink))
                return true;
        }
        return false;
    }

    public void clickTreeTopicLink(String treeTopicLink) {
        By linkPath = By.xpath("//a[text() = '" + treeTopicLink + "']");
        driver.findElement(linkPath).click();
    }

    public String getTreeLinkHeader() {
        return driver.findElement(headerTreeLink).getText();
    }

    public boolean isTryHereButtonOnTreeLinkPageVisible() {
        return driver.findElement(btnTryHereTreeLinkPage).isDisplayed();
    }

    public void clickTryHereInTreeLinkPage() {
        driver.findElement(btnTryHereTreeLinkPage).click();
    }

    public boolean isPracticeQuestionsLinkOnTreeVisible() {
        return driver.findElement(lnkPracticeQuestionsTreeTopics).isDisplayed();
    }

    public void clickPracticeQuestionsOnTree() {
        driver.findElement(lnkPracticeQuestionsTreeTopics).click();
    }

    public String getTreePageURL() {
        return driver.getCurrentUrl();
    }
}

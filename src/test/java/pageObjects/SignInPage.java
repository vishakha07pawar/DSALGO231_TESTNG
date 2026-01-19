package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignInPage {
	private WebDriver driver;

	private By txtUsername = By.xpath("//input[@id='id_username']");
	private By txtPassword = By.xpath("//input[@id='id_password']");
	private By loginButton = By.xpath("//input[@value='Login']");
	private By registerLink = By.xpath("//a[contains(text(),'Register')]");
	private By errorMsg = By.xpath("//div[contains(@class,'alert')]");

	public SignInPage(WebDriver driver) {
		this.driver = driver;
	}

	public void enterUsername(String username) {
		if (username != null && !username.isEmpty()) {
			driver.findElement(txtUsername).clear();
			driver.findElement(txtUsername).sendKeys(username);
		}
	}

	public void enterPassword(String password) {
		if (password != null && !password.isEmpty()) {
			driver.findElement(txtPassword).clear();
			driver.findElement(txtPassword).sendKeys(password);
		}
	}

	public String getSignInPageURL() {
		return driver.getCurrentUrl();
	}

	public String getBrowserValidationMessage() {
		WebElement activeElement = driver.switchTo().activeElement();
		return activeElement.getAttribute("validationMessage");
	}

	public String getApplicationErrorMessage() {
		return driver.findElement(errorMsg).getText();
	}

	// -------------------- Page Chaining-----------------------------------------
	// VALID LOGIN → Navigates to HomePage
	public HomePage login(String username, String password) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(txtUsername)).sendKeys(username);
	    wait.until(ExpectedConditions.visibilityOfElementLocated(txtPassword)).sendKeys(password);
	    wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	    return new HomePage(driver);
	    /*
		driver.findElement(txtUsername).sendKeys(username);
		driver.findElement(txtPassword).sendKeys(password);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement a = driver.findElement(loginButton);
		js.executeScript("arguments[0].click();", a);
		return new HomePage(driver);
		*/
	}
	
	public void clickSignIn() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
	}
	public String verifyLogin(String Username, String Password,String Validation) {

		String actualOutput = "";
		enterUsername(Username);
		enterPassword(Password);
		clickSignIn();

		switch (Validation) {
		case "Empty username and empty password":
			actualOutput = getBrowserValidationMessage();
			break;
		case "Valid username and empty password":
			actualOutput = getBrowserValidationMessage();
			break;
		case "Empty username and valid password":
			actualOutput = getBrowserValidationMessage();
			break;
		case "Invalid username and valid password":
			actualOutput=getApplicationErrorMessage();
			break;
		case "Valid username and invalid password":
			actualOutput=getApplicationErrorMessage();
			break;
		}
		return actualOutput;
	}

	public RegisterPage clickRegister() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(registerLink));
	    wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
	    return new RegisterPage(driver);			
	}

	public boolean isRegistrationPageDisplayed() {
		return driver.getCurrentUrl().contains("register");
	}

	public void navigateToPage(String loginUrl) {
		if (loginUrl != null && !loginUrl.isEmpty()) {
	        driver.get(loginUrl);
	    }
		
	}

}

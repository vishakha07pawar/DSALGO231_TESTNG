package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BaseTest;

public class RegisterPage extends BaseTest{
	private WebDriver driver;
	private By userTxt = By.name("username");
	private By passwordTxt = By.name("password1");
	private By confirmPasswrdTxt = By.name("password2");
	private By registerBtn = By.xpath("//input[@type='submit']");
	private By loginLink = By.xpath("//a[normalize-space()='Login']");
	private By registerLink = By.xpath("//a[normalize-space()='Register']");
	private By successRegMsg = By.xpath("//div[@role='alert']");
	private By errorMsg = By.xpath("//div[@role='alert']");
	private By btnGetStartedDsPortal = By.xpath("//button[normalize-space()='Get Started']");

	public RegisterPage(WebDriver driver) {

		this.driver = driver;

	}

	public void enterUserName(String userName) {
		driver.findElement(userTxt).sendKeys(userName);
	}

	public void enterpassWord(String passWord) {
		driver.findElement(passwordTxt).sendKeys(passWord);
	}

	public void enterPasswordConfirmation(String passwordConfirmation) {
		driver.findElement(confirmPasswrdTxt).sendKeys(passwordConfirmation);
	}

	public void clickRegisterBtn() {
		driver.findElement(registerBtn).click();
	}

	public SignInPage clickLoginLink() {
		driver.findElement(loginLink).click();
		return new SignInPage(driver);
	}

	public String getRegisterMsg() {

		String UserRegisterMsg = driver.findElement(successRegMsg).getText();
		return UserRegisterMsg;
	}

	public void homeRegisterLink() {
		driver.findElement(registerLink).click();
	}

	public void clickDsPortalGetStarted() {
		driver.findElement(btnGetStartedDsPortal).click();
	}

	public WebElement getFieldLocation(String fieldName) {

		switch (fieldName.toLowerCase()) {
		case "username":
			return driver.findElement(userTxt);
		case "password":
			return driver.findElement(passwordTxt);
		case "password confirmation":
			return driver.findElement(confirmPasswrdTxt);
		case "login":
			return driver.findElement(errorMsg);
		default:
			throw new IllegalArgumentException("Invalid FieldName:" + fieldName);

		}

	}

	public boolean getloginPageUrl() {

		return driver.getCurrentUrl().contains("login");

	}


	public boolean getRegisterPageURL() {
		return driver.getCurrentUrl().contains("register");
	}
}

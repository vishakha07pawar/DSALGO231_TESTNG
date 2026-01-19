package testCases;

import org.openqa.selenium.WebDriver;

import factory.DriverManager;
import pageObjects.DataStructurePage;
import pageObjects.HomePage;

public class DataStructurePageTestCases {
	private HomePage homePage;
	private DataStructurePage dataStructurePage;
    private WebDriver driver;

	public void DataStructurePageTest() {

		driver = DriverManager.getDriver();
		
		homePage = new HomePage(driver);
		
	}
}


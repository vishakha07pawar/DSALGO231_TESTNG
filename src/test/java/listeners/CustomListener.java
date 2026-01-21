package listeners;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.chaintest.plugins.ChainTestListener;
import factory.DriverManager;

public class CustomListener implements ITestListener {
    public void onTestFailure(ITestResult result) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
        byte[] screenShot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        ChainTestListener.embed(screenShot, "image/png");
    }
}

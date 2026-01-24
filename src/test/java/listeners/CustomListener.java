package listeners;

import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.chaintest.plugins.ChainTestListener;
import factory.DriverManager;

import java.io.ByteArrayInputStream;

public class CustomListener implements ITestListener {
    public void onTestFailure(ITestResult result) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
        byte[] screenShot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        ChainTestListener.embed(screenShot, "image/png");
        Allure.addAttachment(result.getName(), new ByteArrayInputStream(screenShot));
    }
}

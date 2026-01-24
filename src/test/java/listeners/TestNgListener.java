package listeners;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import factory.DriverManager;
import io.qameta.allure.Allure;
import utils.LoggerFactory;

public class TestNgListener implements ITestListener {
	private ExtentSparkReporter sparkReporter;
	private ExtentReports extentReport;
	private ExtentTest extentTest;

	public void onStart(ITestContext context) {
		Path currentWorkingDir = Paths.get("");
		String projectPath = currentWorkingDir.toAbsolutePath().toString();
		sparkReporter = new ExtentSparkReporter(projectPath + "/Reports/extentReport.html");
		sparkReporter.config().setDocumentTitle("DsAlgoTestNg_ExtentReport");
		sparkReporter.config().setTheme(Theme.STANDARD);
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);
	}

	public void onTestSuccess(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.PASS, "Test case passed: " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.FAIL, "Test case failed: " + result.getName());
		extentTest.log(Status.FAIL, "Test case failed, cause: " + result.getThrowable());
		TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
		byte[] screenShot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
		ChainTestListener.embed(screenShot, "image/png");
		Allure.addAttachment(result.getName(), new ByteArrayInputStream(screenShot));
		try {
			extentTest.fail("Test case failed", MediaEntityBuilder
					.createScreenCaptureFromBase64String(Base64.getEncoder().encodeToString(screenShot)).build());
		} catch (IOException e) {
			LoggerFactory.getLogger().error("Unexpected error occurred when generating a failed report. {}",
					e.getStackTrace().toString());
		}
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.SKIP, "Test case skipped: " + result.getName());
	}

	public void onFinish(ITestContext context) {
		extentReport.flush();
	}
}

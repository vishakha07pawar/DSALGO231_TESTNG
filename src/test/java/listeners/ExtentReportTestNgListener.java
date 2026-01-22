package listeners;

import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import factory.DriverManager;

public class ExtentReportTestNgListener implements ITestListener {
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extentReport;
	public ExtentTest extentTest;

	public void onStart(ITestContext context) {
		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/extentReport.html");
		sparkReporter.config().setDocumentTitle("DsAlgoTestNg_ExtentReport");
		sparkReporter.config().setTheme(Theme.DARK);
		extentReport = new ExtentReports();
		extentReport.attachReporter(sparkReporter);

	}

	public void onTestSuccess(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.PASS, "Test case passed is:" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.FAIL, "Test case failed is:" + result.getName());
		extentTest.log(Status.FAIL, "Test case failed cause is:" + result.getThrowable());
		TakesScreenshot takesScreenshot = (TakesScreenshot) DriverManager.getDriver();
		String base64 = takesScreenshot.getScreenshotAs(OutputType.BASE64);
		try {
			extentTest.fail(
			    "Screenshot",
			    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build()
			);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}

	public void onTestSkipped(ITestResult result) {
		extentTest = extentReport.createTest(result.getName());
		extentTest.log(Status.SKIP, "Test case skipped is:" + result.getName());
		
	}
	public void onFinish(ITestContext context) {
	    extentReport.flush();
	}

}

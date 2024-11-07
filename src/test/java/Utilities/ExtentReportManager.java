package Utilities;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {

	public static ExtentSparkReporter sparkReporter; // for UI and Look & Feel
	public static ExtentReports extent; // Providing the common info
	public static ExtentTest test; // Updating the status of the test and methods

	public void onStart(ITestContext context) {
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.MM.ss");
		String date1 = df.format(date);
		String FileName = System.getProperty("user.dir")+"\\reports\\" + date1 + ".html";
		sparkReporter = new ExtentSparkReporter(FileName);

		sparkReporter.config().setDocumentTitle("Hackathon Extended Report");
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("Practo Hospital");

		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);

		extent.setSystemInfo("Computer Name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("Tester Name", System.getProperty("user.name"));
		extent.setSystemInfo("os", context.getCurrentXmlTest().getParameter("os"));
		extent.setSystemInfo("Browser name", context.getCurrentXmlTest().getParameter("browser"));
	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getName()); // Create new Entry to report
		test.log(Status.PASS, "Test case Passes is : " + result.getName()); // Update the Status
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test failed is : " + result.getName());
		test.log(Status.FAIL, "Test Case failed cause is : " + result.getThrowable());
		ScreenshotUtility s = new ScreenshotUtility();
		try {
			s.captureScreenshot(result.getName());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());
		test.log(Status.FAIL, "Test skipped is : " + result.getName());
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}

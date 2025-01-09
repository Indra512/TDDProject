package listener;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

public class MyTestListener implements ITestListener {

	public void onTestSuccess(ITestResult result) {
		ExtentTest test = (ExtentTest) result.getTestContext().getAttribute("Test");
		test.pass("Test passed--" + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		ExtentTest test = (ExtentTest) result.getTestContext().getAttribute("Test");
		test.fail(result.getThrowable().getMessage());
		test.fail("Test failed--" + result.getName());
	}

	public void onTestSkipped(ITestResult result) {
		ExtentTest test = (ExtentTest) result.getTestContext().getAttribute("Test");
		test.skip("Test skipped--" + result.getName());
	}
}

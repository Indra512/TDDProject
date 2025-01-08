package testBase;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import keywords.ApplicationKeyword;
import reports.ExtentManager;


public class TestBase {
	public ApplicationKeyword app;
	public ExtentReports report;
	public ExtentTest test;
	
	@BeforeTest
	public void beforeTest(ITestContext context) {
		// single object for single test
		// initialize and share for all test cases
		app = new ApplicationKeyword();
		report = ExtentManager.getExtentReport();
		test = report.createTest(context.getCurrentXmlTest().getName());
		context.setAttribute("App", app);
		context.setAttribute("Report", report);
		context.setAttribute("Test", test);
		app.setExtentTest(test);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		app = (ApplicationKeyword) context.getAttribute("App");
		report = (ExtentReports) context.getAttribute("Report");
		test = (ExtentTest) context.getAttribute("Test");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
	}
	
	@AfterTest
	public void afterTest() {
		if (app != null) {
			app.quit();
		}
		if (report!=null) {
			report.flush();
		}
	}
}

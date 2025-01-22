package testBase;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import keywords.ApplicationKeyword;
import reports.ExtentManager;
import runner.DataUtil;
import runner.ExcelReader;


public class TestBase {
	public ApplicationKeyword app;
	public ExtentReports report;
	public ExtentTest test;
	
	@BeforeTest
	public void beforeTest(ITestContext context) {
		// single object for single test
		// initialize and share for all test cases
		
		//Read JSON
//		String testDataFilePath = context.getCurrentXmlTest().getParameter("testDataFilePath");
//		String dataFlag = context.getCurrentXmlTest().getParameter("dataFlag");
//		int iterationNumber = Integer.parseInt(context.getCurrentXmlTest().getParameter("iterationNumber"));
//		JSONObject data = new DataUtil().getTestData(testDataFilePath, dataFlag, iterationNumber);
		
		// Read Excel
		String testDataFilePath = context.getCurrentXmlTest().getParameter("testDataFilePath");
		String sheetName = context.getCurrentXmlTest().getParameter("sheetName");
		String dataFlag = context.getCurrentXmlTest().getParameter("dataFlag");
		int iterationNumber = Integer.parseInt(context.getCurrentXmlTest().getParameter("iterationNumber"));
		JSONObject data = new ExcelReader().getTestData(testDataFilePath, sheetName, dataFlag, iterationNumber);
		
		app = new ApplicationKeyword();
		report = ExtentManager.getExtentReport();
		test = report.createTest(context.getCurrentXmlTest().getName());
		context.setAttribute("App", app);
		context.setAttribute("Report", report);
		context.setAttribute("Test", test);
		context.setAttribute("Data", data);
		app.setExtentTest(test);
		String runMode = (String) data.get("runmode");
		if (!runMode.equalsIgnoreCase("Yes")) {
			app.skip("Run mode in test data is not true");
			throw new SkipException("Run mode in test data is not true");
		}
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		app = (ApplicationKeyword) context.getAttribute("App");
		report = (ExtentReports) context.getAttribute("Report");
		test = (ExtentTest) context.getAttribute("Test");
		String criticalFailure = (String) context.getAttribute("IsCriticalFailure");
		if (criticalFailure != null && criticalFailure.equals("true")) {
			app.skip("Skipped because of previous test failed");
			throw new SkipException("Skipped because of previos test failed");
		}
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod(ITestContext context) {
		app = (ApplicationKeyword) context.getAttribute("App");
		app.reportAll();
	}
	
	@AfterTest
	public void afterTest(ITestContext context) {
		app = (ApplicationKeyword) context.getAttribute("App");
		report = (ExtentReports) context.getAttribute("Report");
		if (app != null) {
			app.quit();
		}
		if (report!=null) {
			report.flush();
		}
	}
}

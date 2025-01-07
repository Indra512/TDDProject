package testBase;

import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import keywords.ApplicationKeyword;


public class TestBase {
	public ApplicationKeyword app;
	
	@BeforeTest
	public void beforeTest(ITestContext context) {
		System.out.println("Before Test");
		// single object for single test
		// initialize and share for all test cases
		app = new ApplicationKeyword();
		context.setAttribute("app", app);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		System.out.println("Before Method");
		app = (ApplicationKeyword) context.getAttribute("app");
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethod() {
		System.out.println("After Method");
	}
	
	@AfterTest
	public void afterTest() {
		System.out.println("After Test");
	}

}

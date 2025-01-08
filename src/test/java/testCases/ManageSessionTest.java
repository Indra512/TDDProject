package testCases;

import org.testng.annotations.Test;

import testBase.TestBase;

public class ManageSessionTest extends TestBase {
	
	@Test
	public void login() {
		app.openBrowser("browser_name");
		app.openUrl("url");
		app.click("signIn_linkText");
        app.type("username_id", "sncckssbm9988@gmail.com");
        app.type("password_xpath", "kssbm9988");
	}
	
	@Test
	public void logout() {
		test.info("Logout");
	}

}

package testCases;

import org.testng.annotations.Test;

import testBase.TestBase;

public class ManageSessionTest extends TestBase {
	
	@Test
	public void login() throws InterruptedException {
		app.info("Login into app");
		app.openBrowser("browser_name");
		app.openUrl("url");
		app.click("signIn_linkText");
        app.type("username_id", "sncckssbm9988@gmail.com");
        app.type("password_xpath", "kssbm9988");
        app.enterCaptcha("captcha_css");
        app.click("submitButton_name");
        app.waitForWebPageToLoad();
        app.validateTitle("portfolio_page_title");
	}
	
	@Test
	public void logout() {
		app.info("Logout");
	}

}

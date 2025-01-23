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
        app.type("username_id", "ADD_YOUR_CREDENTAILS");
        app.type("password_xpath", "ADD_YOUR_CREDENTAILS");
//        Enter captcha manually
//        app.enterCaptcha("captcha_css");
        app.wait(10);
        app.click("submitButton_name");
        app.waitForWebPageToLoad();
        app.validateTitle("portfolio_page_title");
	}
	
	@Test
	public void logout() {
		app.info("Logout");
	}

}

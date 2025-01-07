package testCases;

import org.testng.annotations.Test;

import testBase.TestBase;

public class ManageSessionTest extends TestBase {
	
	@Test
	public void login() {
		System.out.println("Login");
		app.openBrowser("browser_name");
	}
	
	@Test
	public void logout() {
		System.out.println("Logout");
	}

}

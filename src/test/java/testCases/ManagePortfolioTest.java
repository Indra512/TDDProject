package testCases;

import org.testng.annotations.Test;

import testBase.TestBase;

public class ManagePortfolioTest extends TestBase {
	
	@Test
	public void createPortfolio() {
		System.out.println("Create Portfolio");
		app.openUrl("url");
	}

	@Test
	public void deletePortfolio() {
		System.out.println("Delete Portfolio");
	}
}

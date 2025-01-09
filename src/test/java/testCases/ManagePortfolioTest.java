package testCases;

import org.testng.annotations.Test;

import testBase.TestBase;

public class ManagePortfolioTest extends TestBase {
	
	@Test
	public void createPortfolio() {
		app.info("Create Portfolio");
	}

	@Test
	public void deletePortfolio() {
		app.info("Delete Portfolio");
	}
}

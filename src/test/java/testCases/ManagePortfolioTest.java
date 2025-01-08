package testCases;

import org.testng.annotations.Test;

import testBase.TestBase;

public class ManagePortfolioTest extends TestBase {
	
	@Test
	public void createPortfolio() {
		test.info("Create Portfolio");
	}

	@Test
	public void deletePortfolio() {
		test.info("Delete Portfolio");
	}
}

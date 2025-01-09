package testCases;

import org.testng.annotations.Test;

import testBase.TestBase;

public class ManagePortfolioTest extends TestBase {

	@Test
	public void createPortfolio() {
		String portfolioName = "MyPortfolio_1234";
		app.info("Creating Portfolio--" + portfolioName);
		app.click("createPortfolio_id");
		app.clear("create_textfield_id");
		app.type("create_textfield_id", portfolioName);
		app.click("createPortfolioButton_id");
		app.waitForWebPageToLoad();
		app.validateValueInPortfolioDropdpwn("portfolio_dropdown_id", portfolioName);
	}

	@Test
	public void deletePortfolio() {
		String portfolioName = "MyPortfolio_1234";
		app.info("Deleting Portfolio--" + portfolioName);
		app.selectByVisibleText("portfolio_dropdown_id", portfolioName);
		app.click("deletePortfolio_id");
		app.acceptAlert();
		app.waitForWebPageToLoad();
		app.validateValueNotInPortfolioDropdpwn("portfolio_dropdown_id", portfolioName);
	}
}

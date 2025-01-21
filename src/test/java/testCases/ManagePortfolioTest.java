package testCases;

import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import testBase.TestBase;

public class ManagePortfolioTest extends TestBase {

	@Test
	public void createPortfolio(ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("Data");
		String portfolioName = (String) data.get("portfolioname");
		app.info("Creating Portfolio--" + portfolioName);
		app.click("createPortfolio_id");
		app.clear("create_textfield_id");
		app.type("create_textfield_id", portfolioName);
		app.click("createPortfolioButton_id");
		app.waitForWebPageToLoad();
		app.validateValueInPortfolioDropdpwn("portfolio_dropdown_id", portfolioName);
	}

	@Test
	public void deletePortfolio(ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("Data");
		String portfolioName = (String) data.get("portfolioname");
		app.info("Deleting Portfolio--" + portfolioName);
		app.selectByVisibleText("portfolio_dropdown_id", portfolioName);
		app.waitForWebPageToLoad();
		app.click("deletePortfolio_id");
		app.acceptAlert();
		app.waitForWebPageToLoad();
		app.validateValueNotInPortfolioDropdpwn("portfolio_dropdown_id", portfolioName);
	}
	
	@Test
	public void selectPortfolio(ITestContext context) {
		JSONObject data = (JSONObject) context.getAttribute("Data");
		String portfolioName = (String) data.get("portfolioname");
		app.info("Selecting Portfolio--"+portfolioName);
		app.selectByVisibleText("portfolio_dropdown_id", portfolioName);
		app.waitForWebPageToLoad();
	}
}

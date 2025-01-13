package runner;

import java.util.ArrayList;
import java.util.List;

public class Runner {

	public static void main(String args[]) {
		TestNGRunner testNG = new TestNGRunner(1);
		testNG.createSuite("Manage Stocks Suite", false);
		testNG.addListener("listener.MyTestListener");
		testNG.addTest("Modify Stock : Sell Stock");
		testNG.adddTestParameter("action", "buyStock");

		// Add login test method
		List<String> includeMethods = new ArrayList<String>();
		includeMethods.add("login");
		testNG.addTestClass("testCases.ManageSessionTest", includeMethods);

		// Add select portfolio method
		includeMethods = new ArrayList<String>();
		includeMethods.add("selectPortfolio");
		testNG.addTestClass("testCases.ManagePortfolioTest", includeMethods);

		// Add modify stock methods
		includeMethods = new ArrayList<String>();
		includeMethods.add("verifyStockIsPresent");
		includeMethods.add("modifyStock");
		includeMethods.add("verifyStockIsPresent");
		includeMethods.add("verifyStockQuantity");
		includeMethods.add("verifyStockTransactionHistory");
		testNG.addTestClass("testCases.ManageStocksTest", includeMethods);

		testNG.run();
	}
}

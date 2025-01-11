package testCases;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testBase.TestBase;

public class ManageStocksTest extends TestBase {

	@Test
	public void addStock() {
		String stockName = "YES Bank";
		String selectionDate = "10-01-2023";
		String stockQuantity = "200";
		String stockPrice = "100";
		app.info("Adding stock in portfolio");
		app.click("addStock_button_id");
		app.type("addstockname_textfield_id", stockName);
		app.pressEnterKey("addstockname_textfield_id");
		app.click("stockPurchaseDate_id");
		app.selectDateFromCalender(selectionDate);
		app.type("addstockqty_textfield_id", stockQuantity);
		app.type("addstockprice_textfield_id", stockPrice);
		app.click("addStockButton_button_id");
		app.waitForWebPageToLoad();
		app.info("Stock added successfully");
	}

	@Test
	public void verifyStockIsPresent() {
		String stockName = "YES Bank";
		app.info("Verifying stock in portfolio");
		int rNum = app.getRowNumberWithCellData("stock_table_id", stockName);
		if (rNum == -1) {
			app.reportFailure(stockName + " stock is not present in the stock list", true);
		}
		app.info(stockName + " stock is present in the stock list");
	}

	@Parameters({ "action" })
	@Test
	public void verifyStockQuantity(String action, ITestContext context) {
		String stockName = "YES Bank";
		int modifiedQuantity = 50;
		int expectedModifiedQuantity = 0;

		app.info("Verifying stock quantity after action-- " + action);
		int beforeBuySellQuantity = (int) context.getAttribute("BeforeBuySellQuantity");

		int quantity = app.getStockQuantity("stock_table_id", stockName);

		if (action.equals("sellStock"))
			expectedModifiedQuantity = beforeBuySellQuantity - quantity;
		else if (action.equals("buyStock"))
			expectedModifiedQuantity = quantity - beforeBuySellQuantity;

		app.info("Before stock quantity-- " + beforeBuySellQuantity);
		app.info("After stock quantity-- " + quantity);

		if (expectedModifiedQuantity != modifiedQuantity) {
			app.reportFailure("Expected modified quantity is not matching", true);
		}
		app.info(stockName + " stock has " + quantity + " quantity");
	}

	@Test
	public void verifyStockTransactionHistory() {
		String stockName = "YES Bank";
		app.info("Verifying stock transaction history after modifying stock");
		app.openTransactionHistory("stock_table_id", stockName);
		String quantity = app.getText("no_of_shares_on_transaction_history_css");
		app.info("Latest changes in stock " + stockName + " is " + quantity);
	}

	@Parameters({"action"})
	@Test
	public void modifyStock(String action, ITestContext context) {
		String stockName = "YES Bank";
		String selectionDate = "10-01-2024";
		String stockQuantity = "50";
		String stockPrice = "150";

		int quantity = app.getStockQuantity("stock_table_id", stockName);
		app.info("Before modifying quantity--" + quantity + " of stock --" + stockName);
		context.setAttribute("BeforeBuySellQuantity", quantity);

		app.info("Buy/Sell quantity-- " + stockQuantity + " of stock-- " + stockName);
		app.goToBuySell("stock_table_id", stockName);
		if (action.equals("sellStock"))
			app.selectByVisibleText("equityaction_id", "Sell");
		else
			app.selectByVisibleText("equityaction_id", "Buy");
		app.click("buySellCalendar_id");
		app.selectDateFromCalender(selectionDate);
		app.type("buysellqty_id", stockQuantity);
		app.type("buysellprice_id", stockPrice);
		app.click("buySellStockButton_id");
		app.waitForWebPageToLoad();
		app.info("Stock modified successfully");
	}
}

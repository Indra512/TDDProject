package testCases;

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

	@Test
	public void verifyStockQuantity() {
		String stockName = "YES Bank";
		app.info("Verifying stock quantity after add stock");
		int quantity = app.getStockQuantity("stock_table_id", stockName);
		app.info(stockName + " stock has " + quantity + " quantity");
	}

	@Test
	public void verifyStockTransactionHistory() {
		String stockName = "YES Bank";
		app.info("Verifying stock transaction history after add stock");
		app.openTransactionHistory("stock_table_id", stockName);
		String quantity = app.getText("no_of_shares_on_transaction_history_css");
		app.info("Got " + stockName + " stock and available quantity--" + quantity);
	}

	@Test
	public void modifyStock() {
		app.info("Modify Stock");
	}
}

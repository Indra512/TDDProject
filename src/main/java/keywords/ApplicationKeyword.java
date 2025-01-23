package keywords;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;

public class ApplicationKeyword extends ValidationKeyword {

	public ApplicationKeyword() {
		try {
			properties = new Properties();
			FileInputStream fs = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/Project.properties");
			properties.load(fs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		softAssert = new SoftAssert();
	}

	public void selectDateFromCalender(String date) {
		info("Selecting date--" + date);
		try {
			SimpleDateFormat formateDateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date dateToSelect = formateDateFormat.parse(date);
			String day = new SimpleDateFormat("d").format(dateToSelect);
			String month = new SimpleDateFormat("MMMM").format(dateToSelect);
			String year = new SimpleDateFormat("yyyy").format(dateToSelect);

			String monthYearToBeSelected = month + " " + year;
			String monthYearDisplayed = getText("month_year_text_css");

			while (!monthYearToBeSelected.equals(monthYearDisplayed)) {
				click("dpBack_button_xpath");
				monthYearDisplayed = getText("month_year_text_css");
			}
			driver.findElement(By.xpath("//td[text()='" + day + "']")).click();
			wait(1);
		} catch (Exception e) {
			reportFailure(e.getLocalizedMessage(), true);
			e.printStackTrace();
		}
	}

	public int getRowNumberWithCellData(String locatorKey, String cellData) {
		WebElement table = getElement(locatorKey);
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for (int rNum = 0; rNum < rows.size(); rNum++) {
			WebElement row = rows.get(rNum);
			List<WebElement> cells = row.findElements(By.tagName("td"));
			for (WebElement cell : cells) {
				String cellText = cell.getText();
				if (!cellText.trim().equals("")) {
					if (cellText.trim().contains(cellData)) {
						return rNum;
					}
				}
			}
		}
		return -1; // data not found
	}

	public int getStockQuantity(String locatorKey, String stockName) {
		int rowNum = getRowNumberWithCellData(locatorKey, stockName);
		if (rowNum == -1) {
			reportFailure("Stock quantity is 0 as stock " + stockName + " stock is not present in the stock list",
					true);
			return 0;
		}
		String quantity = driver
				.findElement(
						By.cssSelector("table#stock > tbody > tr:nth-child(" + rowNum + ") > td:nth-child(4) > span"))
				.getText();
		return Integer.parseInt(quantity);
	}

	public void openTransactionHistory(String locatorKey, String stockName) {
		int rowNum = getRowNumberWithCellData(locatorKey, stockName);
		if (rowNum == -1) {
			reportFailure(stockName + " is not present in the stock list", true);
		}
		driver.findElement(By.cssSelector("table#stock > tbody > tr:nth-child(" + rowNum + ") > td:nth-child(1)"))
				.click();
		driver.findElement(By.cssSelector("table#stock > tbody > tr:nth-child(" + rowNum + ") input.equityTransaction"))
				.click();
		waitForWebPageToLoad();
	}
	
	public void goToBuySell(String locatorKey, String stockName) {
		int rowNum = getRowNumberWithCellData(locatorKey, stockName);
		if (rowNum == -1) {
			reportFailure(stockName + " is not present in the stock list", true);
		}
		driver.findElement(By.cssSelector("table#stock > tbody > tr:nth-child(" + rowNum + ") > td:nth-child(1)"))
				.click();
		driver.findElement(By.cssSelector("table#stock > tbody > tr:nth-child(" + rowNum + ") input.buySell"))
				.click();
		waitForWebPageToLoad();
	}
}

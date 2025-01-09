package keywords;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import reports.ExtentManager;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class GenericKeyword {

	public WebDriver driver = null;
	public Properties properties = null;
	public ExtentTest test = null;
	public SoftAssert softAssert = null;

	public void openBrowser(String browserNameKey) {
		String browserName = properties.getProperty(browserNameKey);
		info("Opening browser--" + browserName);
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions chromeOptions = new ChromeOptions();

			chromeOptions.addArguments("--start-maximized", "--disable-infobars");
			chromeOptions.addArguments("--disable-extensions");
			chromeOptions.addArguments("--incognito");
			chromeOptions.addArguments("--disable-notifications");
			chromeOptions.addArguments("ignore-certificate-error");

			driver = new ChromeDriver(chromeOptions);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			FirefoxOptions firefoxOptions = new FirefoxOptions();

			ProfilesIni profilesIni = new ProfilesIni();
			FirefoxProfile firefoxProfile = profilesIni.getProfile("TestUser");

			firefoxProfile.setPreference("dom.webnotifications.enabled", false);
			firefoxProfile.setAcceptUntrustedCertificates(true);
			firefoxProfile.setAssumeUntrustedCertificateIssuer(false);
			firefoxOptions.setProfile(firefoxProfile);

			driver = new FirefoxDriver(firefoxOptions);
		} else if (browserName.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (browserName.equalsIgnoreCase("safari")) {
			driver = new SafariDriver();
		} else {
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
	}

	public void setExtentTest(ExtentTest test) {
		this.test = test;
	}

	public void openUrl(String urlKey) {
		info("Opening web url--" + properties.getProperty(urlKey));
		driver.get(properties.getProperty(urlKey));
	}

	public void quit() {
		info("Driver quit");
		driver.quit();
	}

	public void click(String locatorKey) {
		info("Perform click on locator--" + properties.getProperty(locatorKey));
		getElement(locatorKey).click();
	}

	public void type(String locatorKey, String value) {
		info("Typing text--" + value + " in locator--" + properties.getProperty(locatorKey));
		getElement(locatorKey).sendKeys(value);
	}

	public void clear(String locatorKey) {
		info("Clear default text from " + properties.getProperty(locatorKey));
		getElement(locatorKey).clear();
	}

	public void enterCaptcha(String locatorKey) throws InterruptedException {
		System.out.println("Enter Captcha to fill in the text box: ");
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine();
		getElement(locatorKey).sendKeys(inputText);
		Thread.sleep(1000);
	}

	public void selectByVisibleText(String locatorKey, String value) {
		info("Selecting value--" + value);
		Select select = new Select(getElement(locatorKey));
		select.selectByVisibleText(value);

	}

	public void getText() {

	}

	public void navigate() {

	}

	public void acceptAlert() {
		info("Accepting the alert");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.alertIsPresent());
		driver.switchTo().alert().accept();
		info("Alert accepted successfully");
	}

	public void dismissAlert() {

	}

	public boolean isElementPresent(String locatorKey) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean isElementVisible(String locatorKey) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorKey)));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public WebElement getElement(String locatorKey) {
		if (!isElementPresent(locatorKey)) {
			reportFailure("Locator is not present--" + properties.getProperty(locatorKey));
		}

		if (!isElementVisible(locatorKey)) {
			reportFailure("Locator is not visible--" + properties.getProperty(locatorKey));
		}

		WebElement element = driver.findElement(getLocator(locatorKey));
		return element;
	}

	public By getLocator(String locatorKey) {
		By by = null;
		if (locatorKey.endsWith("_id")) {
			by = By.id(properties.getProperty(locatorKey));
		} else if (locatorKey.endsWith("_linkText")) {
			by = By.linkText(properties.getProperty(locatorKey));
		} else if (locatorKey.endsWith("_partialLinkText")) {
			by = By.partialLinkText(properties.getProperty(locatorKey));
		} else if (locatorKey.endsWith("_xpath")) {
			by = By.xpath(properties.getProperty(locatorKey));
		} else if (locatorKey.endsWith("_css")) {
			by = By.cssSelector(properties.getProperty(locatorKey));
		} else if (locatorKey.endsWith("_name")) {
			by = By.name(properties.getProperty(locatorKey));
		} else if (locatorKey.endsWith("_tagName")) {
			by = By.tagName(properties.getProperty(locatorKey));
		} else if (locatorKey.endsWith("_className")) {
			by = By.className(properties.getProperty(locatorKey));
		}
		return by;
	}

	public List<WebElement> getElements(String locatorKey) {
		List<WebElement> elements = driver.findElements(getLocator(locatorKey));
		return elements;
	}

	public void info(String msg) {
		test.info(msg);
	}

	public void fail(String msg) {
		test.fail(msg);
	}

	public void warning(String msg) {
		test.warning(msg);
	}

	public void skip(String msg) {
		test.skip(msg);
	}

	public void reportFailure(String msg, boolean isCriticalFailure) {
		fail(msg);
		takeScreenshot();
		softAssert.fail();
		if (isCriticalFailure) {
			Reporter.getCurrentTestResult().getTestContext().setAttribute("IsCriticalFailure", "true");
			reportAll();
		}
	}

	public void reportFailure(String msg) {
		reportFailure(msg, false);
	}

	public void reportAll() {
		softAssert.assertAll();
	}

	public void takeScreenshot() {
//		file name of screenshot
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		String formatedDate = simpleDateFormat.format(date);
//		take screenshot
		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(srcFile, new File(ExtentManager.screenshotPath + "/" + formatedDate + ".png"));
//			attach screenshot into Extent report
//			test.addScreenCaptureFromPath(ExtentManager.screenshotPath+"/"+formatedDate+".png", "Screenshot");
			test.fail(MarkupHelper.createLabel("Screenshot", ExtentColor.RED));
			test.fail(
					"<img src='" + ExtentManager.screenshotPath + "/" + formatedDate + ".png' style='width: 100%' />");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void waitForWebPageToLoad() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int i = 0;
		while (i != 10) {
			String state = (String) js.executeScript("return document.readyState;");
			System.out.println(state);
			if (state.equals("complete")) {
				break;
			}
			wait(2);
			i++;
		}
		i = 0;
		while (i != 10) {
			Long d = (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if (d.longValue() == 0) {
				break;
			}
			wait(2);
			i++;
		}
	}

	public void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

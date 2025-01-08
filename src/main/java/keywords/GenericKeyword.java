package keywords;

import org.openqa.selenium.By;
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
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;

import java.time.Duration;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class GenericKeyword {

	public WebDriver driver = null;
	public Properties properties = null;
	public ExtentTest test = null;

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

	public void enterCaptcha(String locatorKey) throws InterruptedException {
		System.out.println("Enter Captcha to fill in the text box: ");
		Scanner scanner = new Scanner(System.in);
		String inputText = scanner.nextLine();
		getElement(locatorKey).sendKeys(inputText);
		Thread.sleep(1000);
	}

	public void select() {

	}

	public void getText() {

	}

	public void navigate() {

	}

	public void acceptAlert() {

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
			test.info("Locator is not present--" + properties.getProperty(locatorKey));
		}

		if (!isElementVisible(locatorKey)) {
			test.info("Locator is not visible--" + properties.getProperty(locatorKey));
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
}

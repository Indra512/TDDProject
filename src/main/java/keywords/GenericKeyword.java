package keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.ProfilesIni;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;
import java.util.Scanner;

public class GenericKeyword {

    public WebDriver driver = null;

    public void openBrowser(String browserName) {
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
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
    }

    public void openUrl(String url) {
        driver.get(url);
    }

    public void quit() {
        driver.quit();
    }

    public void click(String locator) {
        driver.findElement(By.linkText(locator)).click();
    }

    public void clickButton(String locator) {
        driver.findElement(By.id(locator)).click();
    }

    public void type(String locator, String value) {
        driver.findElement(By.id(locator)).sendKeys(value);
    }

    public void enterCaptcha(String locator) {
        System.out.println("Enter Captcha to fill in the text box: ");
        Scanner scanner = new Scanner(System.in);
        String inputText = scanner.nextLine();
        driver.findElement(By.id(locator)).sendKeys(inputText);
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
}

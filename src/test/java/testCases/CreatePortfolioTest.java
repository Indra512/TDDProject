package testCases;

import keywords.ApplicationKeyword;
import keywords.GenericKeyword;
import org.testng.annotations.Test;

import java.util.Scanner;

public class CreatePortfolioTest extends ApplicationKeyword {

    /**
     * 1. Open browser
     * 2. Click on SignIn button/link
     * 3. Enter login details
     * 4. Click on Submit button
     * 5. Verify you are on Portfolio page after login
     * 6. Create Portfolio link
     * 7. Enter portfolio name
     * 8. Click on Create Portfolio link
     * @throws InterruptedException 
     */
    @Test
    public void createPortfolioTest() throws InterruptedException {
        ApplicationKeyword app = new ApplicationKeyword();
        app.openBrowser("browser_name");
        app.openUrl("url");
        app.click("signIn_linkText");
        app.type("username_id", "sncckssbm9988@gmail.com");
        app.type("password_xpath", "kssbm9988");
        app.enterCaptcha("captcha_css");
        app.click("submitButton_name");
        Thread.sleep(3000);
        app.validateTitle("portfolio_page_title");
        app.quit();
    }
}

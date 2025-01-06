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
     */
    @Test
    public void createPortfolioTest() {
        ApplicationKeyword app = new ApplicationKeyword();
        app.openBrowser("chrome");
        app.openUrl("https://money.rediff.com");
        app.click("Sign In");
        app.type("useremail", "sncckssbm9988@gmail.com");
        app.type("userpass", "kssbm9988");
        app.enterCaptcha("captcha");
        app.clickButton("loginsubmit");
        app.validateTitle("Rediff Moneywiz | My Portfolio(s)");
        app.quit();
    }
}

package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test(description = "Verify application landing page authentication workflow accepts valid users securely")
    public void testValidUserLogin() {
        LoginPage loginPage = new LoginPage(driver);
        
        String appUrl = "https://practice.expandtesting.com/login";
        log.info("Navigating to target web workspace: " + appUrl);
        driver.get(appUrl);
        
        log.info("Executing happy path login script sequence...");
        // Log in using the standard test account credentials
        loginPage.login("practice", "SuperSecretPassword!");
        
        log.info("Asserting session workspace updates state contextually...");
        Assert.assertTrue(loginPage.isDashboardVisible(), "Login validation failed! Profile dashboard menu did not render.");
        log.info("Login authentication milestone completed successfully!");
    }
}
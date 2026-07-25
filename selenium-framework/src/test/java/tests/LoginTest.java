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

    @Test(priority = 2, description = "verify authentication fails for wrong inputs")
    public void testInvalidUsernameLogin() {
    	LoginPage loginpage = new LoginPage(driver);
    	
    	// Navigate to the target URL first!
        log.info("Navigating to target application workspace URL...");
        driver.get("https://practice.expandtesting.com/login");
    	
    	log.info("Executiing negative path login script sequesnce with invalid credentials");
    	loginpage.login("invalid user" , "wrongpassword213");
    	
    	log.info("Asserting error banner text matches expected failure criteria");
    	String actualErrorMessage = loginpage.getErrorMessageText();
    	
    	Assert.assertTrue(actualErrorMessage.contains("Your username is invalid!"), "Expected error meesage is" + actualErrorMessage);
    	log.info("Negative login authentication milestone completed successfully!");
    	
    }
    @Test(priority = 2, description = "verify authentication fails for wrong inputs")
    public void testInvalidpasswordLogin() {
    	LoginPage loginpage = new LoginPage(driver);
    	
    	// Navigate to the target URL first!
        log.info("Navigating to target application workspace URL...");
        driver.get("https://practice.expandtesting.com/login");
    	
    	log.info("Executiing negative path login script sequesnce with invalid credentials");
    	loginpage.login("practice" , "wrongpassword213");
    	
    	log.info("Asserting error banner text matches expected failure criteria");
    	String actualErrorMessage = loginpage.getErrorMessageText();
    	
    	Assert.assertTrue(actualErrorMessage.contains("Your password is invalid!"), "Expected error meesage is" + actualErrorMessage);
    	log.info("Negative login authentication milestone completed successfully!");
    	
    }

    @Test(priority = 4, description = "verify authentication fails for wrong inputs")
    public void testemptyUserLogin() {
    	LoginPage loginpage = new LoginPage(driver);
    	
    	// Navigate to the target URL first!
        log.info("Navigating to target application workspace URL...");
        driver.get("https://practice.expandtesting.com/login");
    	
    	log.info("Executiing negative path login script sequesnce with invalid credentials");
    	loginpage.login(" " , " ");
    	
    	log.info("Asserting error banner text matches expected failure criteria");
    	String actualErrorMessage = loginpage.getErrorMessageText();
    	
    	Assert.assertTrue(actualErrorMessage.contains("Your username is invalid!"), "Expected error meesage is" + actualErrorMessage);
    	log.info("Negative login authentication milestone completed successfully!");
    	
    }
}
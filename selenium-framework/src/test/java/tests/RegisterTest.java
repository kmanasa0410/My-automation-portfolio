package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.RegisterPage;

public class RegisterTest extends BaseTest{
	
	@Test(priority = 1, description = "Verify new User registration with dynamic credentials")
	
	public void testSuccessfulUserRegistration() {
		RegisterPage registerPage = new RegisterPage(driver);
		
		//Dynamic test data genaration for new user registration validation
		
		String dynamicUsername = "user_" + System.currentTimeMillis();
		String password = "SecurePassword123!";
		
		log.info("Navigating to new User Registration page...");
		driver.get("https://practice.expandtesting.com/register");
		
		log.info("Submitting registration details for user:" + dynamicUsername);
		registerPage.registerUser(dynamicUsername, password);
		
		log.info("Asserting registration success response banner...");
		String alertText = registerPage.getAlertBannerText();
		Assert.assertTrue(
				alertText.contains("Successfully registered"),
		"Expected registration Success message, but received: " + alertText);
		
		log.info("User registration workflow completed successfully!");
	}
	
	

}

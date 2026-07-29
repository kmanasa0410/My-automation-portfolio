package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	//Locators for Registration page
	private By usernameInput = By.id("username");
	private By passwordInput = By.id("password");
	private By confirmPasswordInput = By.id("confirmPassword");
	private By registerButton = By.cssSelector("button[type = 'submit']");
	private By alertBanner = By.id("flash");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	public void enterUsername(String username) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput)).sendKeys(username);	
		
	}
	
	public void enterPassword(String password)
	{
		driver.findElement(passwordInput).sendKeys(password);
		
	}
	
	public void enterConfirmPassword(String password) {
		driver.findElement(confirmPasswordInput).sendKeys(password);
	}
	
	public void clickRegister() {
		driver.findElement(registerButton).click();
	}
	
	public void registerUser(String username, String password) {
		enterUsername(username);
		enterPassword(password);
		enterConfirmPassword(password);
		clickRegister();
		
	}
	
	public String getAlertBannerText() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(alertBanner)).getText();
	}
			
	

}

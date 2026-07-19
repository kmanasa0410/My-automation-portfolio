package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final Logger log = LogManager.getLogger(this.getClass());

    // 1. Private UI Selectors (Encapsulation)
    private final By Username = By.id("username");
    private final By passwordInput = By.id("password");
    private final By loginButton = By.xpath("//button[@id='submit-login']");
    private final By userProfileMenu = By.id("flash"); // Visible only when logged in

    // Constructor to pass the browser session downstream
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // 2. Structural Interactivity Methods
    public void enterUsername(String username) {
        log.info("Entering username parameter credentials.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(Username)).sendKeys(username);
    }

    public void enterPassword(String password) {
        log.info("Entering password security tokens securely.");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput)).sendKeys(password);
    }

    public void clickLogin() {
        log.info("Submitting authentication execution forms.");
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    // A clean wrapper method to handle the comprehensive action step
    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

    public boolean isDashboardVisible() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(userProfileMenu)).isDisplayed();
        } catch (Exception e) {
            log.error("Login not successful: ", e);
            return false;
        }
    }
}
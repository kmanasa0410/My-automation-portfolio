package tests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        log = LogManager.getLogger(this.getClass());
        log.info("Initializing the WebDriver session...");

        try {
            ChromeOptions options = new ChromeOptions();
           // options.addArguments("--headless=new"); 
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
            log.info("Chrome Browser session spawned successfully.");

            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.manage().window().maximize();

        } catch (Exception e) {
            log.fatal("Critical failure during WebDriver initialization: ", e);
            throw e;
        }
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            log.info("Closing down the WebDriver browser instance...");
            driver.quit();
        }
    }
}
package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ControlsPage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Locators for dropdowns page
    private By dropdownElement = By.id("dropdown");
    private By countryDropdownElement = By.id("country");

    // Locators for tables page
    private By tableRows = By.xpath("//table[@id='table1']/tbody/tr");

    public ControlsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectOptionByValue(String value) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dropdownElement));
            Select select = new Select(element);
            select.selectByValue(value);
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for dropdown element: " + dropdownElement);
            throw e;
        } catch (NoSuchElementException e) {
            System.err.println("Unable to locate option with value: " + value);
            throw e;
        } catch (Exception e) {
            System.err.println("Unexpected error selecting dropdown value '" + value + "': " + e.getMessage());
            throw e;
        }
    }

    public String getSelectedOptionText() {
        try {
            WebElement element = driver.findElement(dropdownElement);
            Select select = new Select(element);
            return select.getFirstSelectedOption().getText();
        } catch (NoSuchElementException e) {
            System.err.println("Failed to locate dropdown element to read selected option.");
            throw e;
        } catch (Exception e) {
            System.err.println("Error reading selected option text: " + e.getMessage());
            throw e;
        }
    }

    public void selectCountryByVisibleText(String countryName) {
        try {
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(countryDropdownElement));
            Select select = new Select(element);
            select.selectByVisibleText(countryName);
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for country dropdown element: " + countryDropdownElement);
            throw e;
        } catch (NoSuchElementException e) {
            System.err.println("Country option not found in dropdown: " + countryName);
            throw e;
        } catch (Exception e) {
            System.err.println("Failed to select country '" + countryName + "': " + e.getMessage());
            throw e;
        }
    }

    // --- Dynamic Web Table Methods ---

    /**
     * Iterates dynamically through table rows to find a target email and returns the associated user's last name.
     */
    public String getLastNameByEmail(String targetEmail) {
        try {
            List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));
            
            for (WebElement row : rows) {
                List<WebElement> cells = row.findElements(By.tagName("td"));
                if (!cells.isEmpty()) {
                    String lastName = cells.get(0).getText().trim();
                    String email = cells.get(2).getText().trim();

                    if (email.equalsIgnoreCase(targetEmail)) {
                        return lastName;
                    }
                }
            }
            throw new NoSuchElementException("Target email '" + targetEmail + "' was not found in the dynamic table.");
        } catch (TimeoutException e) {
            System.err.println("Timeout waiting for web table rows to render.");
            throw e;
        } catch (Exception e) {
            System.err.println("Error processing dynamic web table: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Returns total count of rows currently loaded in table 1.
     */
    public int getTableRowCount() {
        try {
            List<WebElement> rows = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(tableRows));
            return rows.size();
        } catch (Exception e) {
            System.err.println("Failed to fetch table row count: " + e.getMessage());
            return 0;
        }
    }
}
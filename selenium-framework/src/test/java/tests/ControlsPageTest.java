package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ControlsPage;

public class ControlsPageTest extends BaseTest {

    @Test(priority = 1, description = "Verify dropdown option selection with error handling")
    public void testDropdownSelection() {
        ControlsPage controlsPage = new ControlsPage(driver);

        try {
            log.info("Navigating to dropdown practice page...");
            driver.get("https://practice.expandtesting.com/dropdown");

            log.info("Selecting option '1' from simple dropdown...");
            controlsPage.selectOptionByValue("1");

            String selectedText = controlsPage.getSelectedOptionText();
            log.info("Selected option verified as: " + selectedText);
            Assert.assertEquals(selectedText, "Option 1", "Dropdown selection mismatch!");

            log.info("Selecting country 'India' from country dropdown...");
            controlsPage.selectCountryByVisibleText("India");

            log.info("Dropdown controls test executed successfully!");

        } catch (AssertionError e) {
            log.error("Assertion failure during dropdown validation: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Execution exception encountered in testDropdownSelection: " + e.getMessage());
            Assert.fail("Test failed unexpectedly due to exception: " + e.getMessage());
        }
    }

    @Test(priority = 2, description = "Verify dynamic web table row iteration and cell value extraction")
    public void testDynamicWebTable() {
        ControlsPage controlsPage = new ControlsPage(driver);

        try {
            log.info("Navigating to web tables practice page...");
            driver.get("https://practice.expandtesting.com/tables");

            int totalRows = controlsPage.getTableRowCount();
            log.info("Total dynamic table rows identified: " + totalRows);
            Assert.assertTrue(totalRows > 0, "Table should contain at least one row.");

            String targetEmail = "fbach@yahoo.com";
            log.info("Searching dynamic table for email: " + targetEmail);

            String actualLastName = controlsPage.getLastNameByEmail(targetEmail);
            log.info("Found matching record! Last Name: " + actualLastName);

            Assert.assertEquals(actualLastName, "Bach", "Dynamic table cell value mismatch!");
            log.info("Dynamic web table test executed successfully!");

        } catch (AssertionError e) {
            log.error("Assertion failure during web table validation: " + e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Execution exception encountered in testDynamicWebTable: " + e.getMessage());
            Assert.fail("Test failed unexpectedly due to exception: " + e.getMessage());
        }
    }
}
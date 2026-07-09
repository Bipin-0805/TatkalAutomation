package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.Properties;

/**
 * Utility class for common test operations
 */
public class TestUtils {
    
    private WebDriver driver;
    private WebDriverWait wait;
    private Properties config;
    private Properties OR;
    
    public TestUtils(WebDriver driver, Properties config, Properties OR) {
        this.driver = driver;
        this.config = config;
        this.OR = OR;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    /**
     * Wait and click on element
     */
    public void clickElement(String locator) {
        try {
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath(locator)));
            element.click();
            System.out.println("Clicked on element: " + locator);
        } catch (Exception e) {
            System.err.println("Failed to click element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Wait and enter text in element
     */
    public void enterText(String locator, String text) {
        try {
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(
                    By.xpath(locator)));
            element.clear();
            element.sendKeys(text);
            System.out.println("Entered text in element: " + locator);
        } catch (Exception e) {
            System.err.println("Failed to enter text in element: " + locator + " - " + e.getMessage());
            throw e;
        }
    }
    
    /**
     * Wait for element visibility
     */
    public boolean waitForElement(String locator, int seconds) {
        try {
            WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            customWait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath(locator)));
            System.out.println("Element visible: " + locator);
            return true;
        } catch (Exception e) {
            System.err.println("Element not visible within " + seconds + " seconds: " + locator);
            return false;
        }
    }
    
    /**
     * Get page title
     */
    public String getPageTitle() {
        return driver.getTitle();
    }
    
    /**
     * Get current URL
     */
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
    
    /**
     * Check if element exists
     */
    public boolean isElementPresent(String locator) {
        try {
            driver.findElement(By.xpath(locator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

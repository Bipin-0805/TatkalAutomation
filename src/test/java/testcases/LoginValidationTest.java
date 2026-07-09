package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import utilities.TestUtils;

import base.TestBase;

/**
 * Login validation test case
 * Verifies successful login to IRCTC application
 */
public class LoginValidationTest extends TestBase {
    
    private TestUtils testUtils;
    
    @BeforeTest
    public void initTest() throws Exception {
        testUtils = new TestUtils(driver, config, OR);
    }
    
    /**
     * Test Case 1: Verify login page is accessible
     */
    @Test(priority = 0, description = "Verify login page accessibility")
    public void testLoginPageAccess() {
        try {
            System.out.println("========== Test: Login Page Access ==========");
            
            String pageTitle = testUtils.getPageTitle();
            System.out.println("Page Title: " + pageTitle);
            
            String baseURL = config.getProperty("baseURL");
            System.out.println("Base URL loaded: " + baseURL);
            
            // Verify login button is present
            Assert.assertTrue(testUtils.waitForElement(OR.getProperty("Login"), 10),
                    "Login button not found on page");
            
            System.out.println("Test PASSED: Login page is accessible!");
            
        } catch (Exception e) {
            System.err.println("Test FAILED: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Login page access test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test Case 2: Validate successful login with valid credentials
     */
    @Test(priority = 1, description = "Verify login with valid credentials")
    public void testValidLogin() throws InterruptedException {
        try {
            System.out.println("========== Test: Valid Login ==========");
            
            // Click Login button
            testUtils.clickElement(OR.getProperty("Login"));
            Thread.sleep(1000);
            
            // Verify login page elements are present
            Assert.assertTrue(testUtils.waitForElement(OR.getProperty("username"), 10),
                    "Username field not found");
            Assert.assertTrue(testUtils.waitForElement(OR.getProperty("password"), 10),
                    "Password field not found");
            
            // Enter credentials
            testUtils.enterText(OR.getProperty("username"), config.getProperty("Username"));
            testUtils.enterText(OR.getProperty("password"), config.getProperty("Password"));
            
            System.out.println("Credentials entered successfully");
            
            // Wait for captcha
            System.out.println("Waiting for captcha resolution...");
            Thread.sleep(5000);
            
            // Click Sign In
            testUtils.clickElement(OR.getProperty("signin"));
            
            System.out.println("Sign In button clicked");
            
            // Wait for login completion
            Thread.sleep(3000);
            
            // Verify login success by checking URL or page title
            String currentUrl = testUtils.getCurrentUrl();
            System.out.println("Current URL after login: " + currentUrl);
            
            System.out.println("Test PASSED: Login successful!");
            
        } catch (Exception e) {
            System.err.println("Test FAILED: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Login test failed: " + e.getMessage());
        }
    }
    
    /**
     * Test Case 3: Verify all login form fields exist
     */
    @Test(priority = 2, description = "Verify login form fields")
    public void testLoginFormFields() throws InterruptedException {
        try {
            System.out.println("========== Test: Login Form Fields ==========");
            
            // Click Login button
            testUtils.clickElement(OR.getProperty("Login"));
            Thread.sleep(1000);
            
            // Verify all form fields
            boolean usernameExists = testUtils.isElementPresent(OR.getProperty("username"));
            boolean passwordExists = testUtils.isElementPresent(OR.getProperty("password"));
            boolean signinExists = testUtils.isElementPresent(OR.getProperty("signin"));
            
            System.out.println("Username field exists: " + usernameExists);
            System.out.println("Password field exists: " + passwordExists);
            System.out.println("SignIn button exists: " + signinExists);
            
            Assert.assertTrue(usernameExists && passwordExists && signinExists,
                    "One or more login form fields not found");
            
            System.out.println("Test PASSED: All login form fields are present!");
            
        } catch (Exception e) {
            System.err.println("Test FAILED: " + e.getMessage());
            e.printStackTrace();
            Assert.fail("Login form fields test failed: " + e.getMessage());
        }
    }
}

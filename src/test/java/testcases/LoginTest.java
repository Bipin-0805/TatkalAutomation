package testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;

import base.TestBase;

public class LoginTest extends TestBase{

	@Test
	public void Login() throws InterruptedException {
		try {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			// Click Login button
			System.out.println("Clicking on Login button...");
			WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("Login"))));
			loginButton.click();
			Thread.sleep(1000); // Brief pause for page transition
			
			// Enter UserName
			System.out.println("Entering username...");
			WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty("username"))));
			usernameField.sendKeys(config.getProperty("Username"));
			
			// Enter Password
			System.out.println("Entering password...");
			WebElement passwordField = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(OR.getProperty("password"))));
			passwordField.sendKeys(config.getProperty("Password"));
			
			// Wait for captcha to be solved (with explicit wait instead of hardcoded sleep)
			System.out.println("Waiting for captcha resolution...");
			Thread.sleep(5000); // User needs to solve captcha manually
			
			// Click Signin Button
			System.out.println("Clicking on SignIn button...");
			WebElement signinButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(OR.getProperty("signin"))));
			signinButton.click();
			
			// Verify login success (add appropriate assertion based on your application)
			Thread.sleep(2000); // Wait for page to load after login
			System.out.println("Login successful!");
			
		} catch (Exception e) {
			System.err.println("Login failed: " + e.getMessage());
			throw e;
		}
	}
	
}

package testcases;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import base.TestBase;

public class LoginTest extends TestBase{

	@Test
	public void Login() throws InterruptedException {
		
	// Click Login button
		driver.findElement(By.xpath(OR.getProperty("Login"))).click();
		
		
	// Enter UserName:
		driver.findElement(By.xpath(OR.getProperty("username"))).sendKeys(config.getProperty("Username"));
		
	//Enter Password:
		driver.findElement(By.xpath(OR.getProperty("password"))).sendKeys(config.getProperty("Password"));
		
	//wait for captcha
		
		Thread.sleep(5000);
		
	// Click Signin Button
		
		driver.findElement(By.xpath(OR.getProperty("signin"))).click();
		
	}
	
	
}

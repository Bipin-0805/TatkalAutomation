package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class TestBase {
	
	public static WebDriver driver;
	public static Properties config;
	public static Properties OR;
	public static FileInputStream fis;
	
	@BeforeTest
	public void Setup() throws IOException {
		
		if (driver==null) {
		config =new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		
		OR =new Properties();
		fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\properties\\OR.properties");
		OR.load(fis);
		
		if(config.getProperty("browser").equals("chrome"))
		{
			ChromeOptions options=new ChromeOptions();
			options.addArguments("disable-notifications");
			options.addArguments("start-maximized");
			driver= new ChromeDriver(options);
		}
		else if(config.getProperty("browser").equals("Firefox")) {
			driver= new FirefoxDriver();
		}
		
		
		
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(config.getProperty("implicit.wait"))));
		driver.get(config.getProperty("baseURL"));
		
		
		}
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	
	}
	

}

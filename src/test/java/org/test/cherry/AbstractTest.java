package org.test.cherry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class AbstractTest {
	
	HomePage homepage;
	WebDriver webDriver;
	
	@BeforeTest
	@Parameters({"url"})
	public void setup(String url) {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		
		webDriver = new ChromeDriver();
		
		homepage = new HomePage(webDriver);
		homepage.get(url);
	}
	
	@AfterTest
	public void afterTest() {
		homepage.tearDown();
	}
	
	public WebDriver getWebDriver() {
		return webDriver;
	}
	
}

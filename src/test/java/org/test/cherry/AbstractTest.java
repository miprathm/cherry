package org.test.cherry;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class AbstractTest {
	
	HomePage homepage;
	
	@BeforeTest
	@Parameters({"url"})
	public void setup(String url) {
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		
		homepage = new HomePage(new ChromeDriver());
		homepage.navigate(url);
	}
	
	@AfterTest
	public void afterTest() {
		homepage.tearDown();
	}
	
}

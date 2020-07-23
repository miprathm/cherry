package org.test.cherry;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver webDriver;
	
	public HomePage(WebDriver driver) {
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(text(),'Deposits')]")
	WebElement deposit;
	
	
	public void navigate(String url) {
		webDriver.navigate().to(url);
	}
	
	public String getTitle() {
		return webDriver.getTitle();
	}
	
	public void tearDown() {
		try {
			this.webDriver.quit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

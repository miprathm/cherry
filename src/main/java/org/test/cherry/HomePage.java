package org.test.cherry;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {
	
	WebDriver webDriver;
	
	public HomePage(WebDriver driver) {
		this.webDriver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[contains(text(),'Deposits')]")
	WebElement deposit;
	
	@FindBys({@FindBy(tagName="img")})
	List<WebElement> image_tags;
	
	@FindBy(css="mat-card.ieco-example-card:first-of-type")
	WebElement fixedDeposite;
	
	
	public void navigate(String url) {
		webDriver.navigate().to(url);
	}
	
	public void get(String url) {
		webDriver.get(url);
	}
	
	public String getTitle() {
		return webDriver.getTitle();
	}
	
	public void clickDepositeTab() {
		WebElement element = new WebDriverWait(webDriver, 20).until(ExpectedConditions.visibilityOf(deposit));
		element.click();
	}
	
	public void tearDown() {
		try {
			this.webDriver.quit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}

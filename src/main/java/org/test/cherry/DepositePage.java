package org.test.cherry;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DepositePage {
	
	WebDriver webDriver;
	
	DepositePage(WebDriver driver){
		this.webDriver = driver;
		PageFactory.initElements(webDriver, this);
	}
	
	@FindBy(xpath="//ieco-app-fixed-deposit[1]/div[1]/section[2]/div[1]/div[1]/div[1]/div[1]/div[3]/div[1]/span[1]/b[1]")
	WebElement fixDepositeAmtView;
	
	//@FindBy(css="")
	//WebElement fixDepositeAmtInput;
	
	
	public WebElement getElementOfAmountField() {
		WebElement element = new WebDriverWait(webDriver, 10).until(ExpectedConditions.visibilityOf(fixDepositeAmtView));		
		element.click();
		WebElement fixDepositeAmtInput = new WebDriverWait(webDriver, 10)
				.until(ExpectedConditions
						.visibilityOfElementLocated(
								By.xpath("//input[@name='investedAmt']")));
		
		return fixDepositeAmtInput;
	}
	
	
}

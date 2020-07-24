package org.test.cherry;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DepositeTest extends AbstractTest {

	int invalidImageCount;
	DepositePage depositePage;
	
	@Test
	public void checkBrokenImage() {
		// Assert.assertEquals(homepage.getTitle(),"Kotak-IECO");
		
		
		// Click on Deposite tab
		homepage.clickDepositeTab();
		
		// get all image link
		// check all broken links
		try {
			int invalidImageCount = 0;
			List<WebElement> imagesList = homepage.image_tags;
			System.out.println("Total no. of images are " + imagesList.size());
			for (WebElement imgElement : imagesList) {
				if (imgElement != null) {
					verifyimageActive(imgElement);
				}
			}
			System.out.println("Total no. of invalid images are " + invalidImageCount);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		Assert.assertEquals(invalidImageCount, 0);
		
	}
	
	@Test
	public void validateFixedDepositeAmount() {
		
		homepage.fixedDeposite.click();
		
		depositePage = new DepositePage(getWebDriver());
		
		depositePage.getElementOfAmountField().sendKeys("100");
		
	}
	
	
	public void verifyimageActive(WebElement imgElement) {
		try {
			HttpClient client = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(imgElement.getAttribute("src"));
			HttpResponse response = client.execute(request);
			// verifying response code he HttpStatus should be 200 if not,
			// increment as invalid images count
			if (response.getStatusLine().getStatusCode() != 200)
				invalidImageCount++;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}

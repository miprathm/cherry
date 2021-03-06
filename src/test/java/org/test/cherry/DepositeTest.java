package org.test.cherry;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utility.Utility;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DepositeTest extends AbstractTest {

	int invalidImageCount;
	DepositePage depositePage;

	@Test(priority = 1)
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

	@Test(priority = 2)
	public void validateFixedDepositeAmount() {

		homepage.fixedDeposite.click();

		depositePage = new DepositePage(getWebDriver());

		WebElement depositeAmtField = depositePage.getElementOfAmountField();
		depositeAmtField.clear();
		depositeAmtField.sendKeys("100");

		Actions actions = new Actions(getWebDriver());

		actions.doubleClick().click().build().perform();

		Assert.assertEquals(depositePage.getValidationMessage(), "Minimum: 10000");

		depositeAmtField.clear();

		depositeAmtField.sendKeys("200000");

		actions.doubleClick().build().perform();

		Assert.assertEquals(depositePage.getValidationMessage(), "Maximum: 100000");

	}

	@Test(priority = 3, dataProvider = "getData")
	public void validateInterest(String amt, String interest_plan, String interest_rate, String payout,String formula) {
		System.out.println(amt + " " + payout);
		
		getWebDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		Actions actions = new Actions(getWebDriver());
		WebElement depositeAmtField = depositePage.getElementOfAmountField();
		depositeAmtField.clear();
		depositeAmtField.sendKeys(amt);
		actions.click().build().perform();

		//System.out.println(depositePage.getMaturityAmt());
		double intPayout = Double.parseDouble(payout);
		
		Assert.assertEquals(Utility.getDoubleFromString(depositePage.getMaturityAmt()),intPayout);

		
	}

	@DataProvider
	public Object[][] getData() {
		return Utility.getAmountSampleData();

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

package org.test.cherry;

import org.testng.annotations.Test;

import junit.framework.Assert;

public class DepositeTest extends AbstractTest{
	
	@Test
	public void checkBrokenImage() {
		Assert.assertEquals(homepage.getTitle(),"Kotak-IECO");
	}
	
}

package com.c2info.RMS_NewRequestTCs;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC002_VerifyByAddingItemsInRequest extends TestBase{

public static final Logger log = Logger.getLogger(TC002_VerifyByAddingItemsInRequest.class.getName());
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Employee"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		log.info("Login Successfull");
	}
	
	@Test
	public void verifyAddingItems() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		homePage.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch(APP.getProperty("BranchCode"));
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequestPage.clickOnAddButton();
		Thread.sleep(2000);
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName3"));
		Thread.sleep(2000);
		newRequestPage.clickOnAddButton();
		Thread.sleep(2000);
		ArrayList<String> itemNames = newRequestPage.getItemNamesAddedToCart();
		Assert.assertTrue(itemNames.contains(APP.getProperty("ItemName2")));
		Assert.assertTrue(itemNames.contains(APP.getProperty("ItemName3")));
	}
}

package com.c2info.RMS_NewRequestTCs;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC004_VerifyQtyFieldAfterUpdate extends TestBase{

public static final Logger log = Logger.getLogger(TC004_VerifyQtyFieldAfterUpdate.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		log.info("Login Successfull");
	}
	
	@Test(priority=0)
	public void verifyQtyIncreaseAfterUpdate() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		homePage.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch(APP.getProperty("BranchCode"));
		newRequestPage.selectCostCenter("Information Technology");
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequestPage.enterQty("5");
		newRequestPage.clickOnAddButton();
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName3"));
		Thread.sleep(2000);
		newRequestPage.enterQty("4");
		newRequestPage.clickOnAddButton();
		HashMap<String,Integer> getItemNamesAndQty = newRequestPage.getItemNamesWithQtyInMyRequestPage();
		int expectedQty = getItemNamesAndQty.get(APP.getProperty("ItemName2"));
		int expectedQty1 = getItemNamesAndQty.get(APP.getProperty("ItemName3"));
		Thread.sleep(3000);
		newRequestPage.increaseQtyBy2ForAllItemsAfterAdding();
		HashMap<String,Integer> getItemNamesAndQtyAfterUpdate = newRequestPage.getItemNamesWithQtyInMyRequestPage();
		int actualQty = getItemNamesAndQtyAfterUpdate.get(APP.getProperty("ItemName2"));
		int actualQty1 = getItemNamesAndQtyAfterUpdate.get(APP.getProperty("ItemName3"));
		Assert.assertEquals(actualQty, expectedQty+2);
		Assert.assertEquals(actualQty1, expectedQty1+2);	
		
	}
	
	@Test(priority=1)
	public void verifyQtyDecreaseAfterUpdate() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		Thread.sleep(2000);
		newRequestPage.clickOnSubmitButton();
		Thread.sleep(2000);
		homePage.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch(APP.getProperty("BranchCode"));
		newRequestPage.selectCostCenter("Information Technology");
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequestPage.enterQty("5");
		newRequestPage.clickOnAddButton();
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName3"));
		Thread.sleep(2000);
		newRequestPage.enterQty("4");
		newRequestPage.clickOnAddButton();
		HashMap<String,Integer> getItemNamesAndQty = newRequestPage.getItemNamesWithQtyInMyRequestPage();
		int expectedQty = getItemNamesAndQty.get(APP.getProperty("ItemName2"));
		int expectedQty1 = getItemNamesAndQty.get(APP.getProperty("ItemName3"));
		Thread.sleep(3000);
		newRequestPage.decreaseQtyBy2ForAllItemsAfterAdding();
		HashMap<String,Integer> getItemNamesAndQtyAfterUpdate = newRequestPage.getItemNamesWithQtyInMyRequestPage();
		int actualQty = getItemNamesAndQtyAfterUpdate.get(APP.getProperty("ItemName2"));
		int actualQty1 = getItemNamesAndQtyAfterUpdate.get(APP.getProperty("ItemName3"));
		Assert.assertEquals(actualQty, expectedQty-2);
		Assert.assertEquals(actualQty1, expectedQty1-2);	
		
	}
}

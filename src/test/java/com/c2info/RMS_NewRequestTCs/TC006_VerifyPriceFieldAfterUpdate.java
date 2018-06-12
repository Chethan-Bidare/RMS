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

public class TC006_VerifyPriceFieldAfterUpdate extends TestBase{

public static final Logger log = Logger.getLogger(TC006_VerifyPriceFieldAfterUpdate.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		log.info("Login Successfull");
	}
	
	@Test
	public void verifyPriceFieldAfterUpdate() throws InterruptedException{
		HomePage hp= new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		hp.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch(APP.getProperty("BranchCode"));
		newRequestPage.selectCostCenter("Information Technology");
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequestPage.enterAmount("5000");
		newRequestPage.clickOnAddButton();
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName3"));
		Thread.sleep(2000);
		newRequestPage.enterAmount("8000");
		newRequestPage.clickOnAddButton();
		Thread.sleep(5000);
		newRequestPage.updateAmountField();
		HashMap<String,Double> getItemNamesAndQtyAfterUpdate = newRequestPage.getItemNamesWithPriceInMyRequestPage();
		double actualQty = getItemNamesAndQtyAfterUpdate.get(APP.getProperty("ItemName2"));
		double actualQty1 = getItemNamesAndQtyAfterUpdate.get(APP.getProperty("ItemName3"));
		Assert.assertEquals(actualQty, 10000.0);
		Assert.assertEquals(actualQty1,10000.0);
	}
	
}

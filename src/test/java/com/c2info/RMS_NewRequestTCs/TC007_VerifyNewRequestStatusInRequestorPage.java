package com.c2info.RMS_NewRequestTCs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC007_VerifyNewRequestStatusInRequestorPage extends TestBase{

	public static final Logger log = Logger.getLogger(TC007_VerifyNewRequestStatusInRequestorPage.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void verifyNewRequestStatus() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequest = new NewRequestPage();
		
		homePage.clickOnNewRequestButton();
		newRequest.selectRequestType("Item");
		newRequest.selectRefBranch(APP.getProperty("BranchCode"));
		newRequest.selectCostCenter(APP.getProperty("CostCenter"));
		newRequest.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequest.enterQty(APP.getProperty("ItemQty"));
		newRequest.enterAmount(APP.getProperty("ItemPrice"));
		newRequest.clickOnAddButton();
		Thread.sleep(2000);
		newRequest.clickOnSubmitButton();
		Thread.sleep(5000);
		ArrayList<Integer> prNum = homePage.getPRnoForRequiredSectionInGridView("My Request");
		Collections.sort(prNum);
		int createdRequst = prNum.get(prNum.size()-1);
		String createdReq = String.valueOf(createdRequst);
		String actualResult = homePage.getStatusBasedOnPRnumber(createdReq, "My Request");
		String expectedResult = "Pending";
		Assert.assertEquals(actualResult, expectedResult);
	}
}

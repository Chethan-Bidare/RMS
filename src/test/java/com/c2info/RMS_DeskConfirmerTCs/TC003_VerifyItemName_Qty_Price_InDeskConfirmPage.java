package com.c2info.RMS_DeskConfirmerTCs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.ApprovalPage;
import com.c2info.RMS_UIActions.DeskConfirmationPage;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC003_VerifyItemName_Qty_Price_InDeskConfirmPage extends TestBase{

	public static final Logger log = Logger.getLogger(TC003_VerifyItemName_Qty_Price_InDeskConfirmPage.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void VerifyItemName_Qty_Price_InApprovalPage() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequest = new NewRequestPage();
		ApprovalPage approvalPage = new ApprovalPage();
		DeskConfirmationPage deskConfirmationPage = new DeskConfirmationPage();
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
		homePage.doLogOut();
		homePage.doLogin(OR.getProperty("Approver"), "3213");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("APPROVAL LIST");
		Thread.sleep(2000);
		approvalPage.clickOnRequestApprovalBasedOnPRnumber("Single Request", createdReq);
		Thread.sleep(3000);
		approvalPage.clickOnApproveButton();
		Thread.sleep(5000);
		homePage.doLogOut();
		homePage.doLogin(OR.getProperty("DeskConfirmer"), OR.getProperty("otp"));
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("DESK CONFIRMATION LIST");
		Thread.sleep(2000);
		deskConfirmationPage.clickOnDeskApprovalRequestBasedOnPRnumber("Single Request", createdReq);
		HashMap<String, ArrayList<String>> itemData  = deskConfirmationPage.getItemNameAndData();
		System.out.println(itemData);
		ArrayList<String> values = new ArrayList<String>();
		for(ArrayList<String> val : itemData.values()){
			values.addAll(val);
		}
		System.out.println(values);
		Assert.assertTrue(itemData.containsKey(APP.getProperty("ItemName2")));
		
		Assert.assertTrue(values.contains(APP.getProperty("ItemQty")));
		Assert.assertTrue(values.contains(APP.getProperty("CostCenter")));
		Assert.assertTrue(values.contains(APP.getProperty("ItemPrice")));
	}
}

package com.c2info.RMS_CostCenterApproverTCs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.ApprovalPage;
import com.c2info.RMS_UIActions.CostCenterApprovalPage;
import com.c2info.RMS_UIActions.DeskConfirmationPage;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC002_VerifyItemDetailsInCostCenterPage extends TestBase{

	public static final Logger log = Logger.getLogger(TC002_VerifyItemDetailsInCostCenterPage.class.getName());
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void verifyItemDetails() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequest = new NewRequestPage();
		ApprovalPage approvalPage = new ApprovalPage();
		DeskConfirmationPage deskConfirmationPage = new DeskConfirmationPage();
		CostCenterApprovalPage costCenterPage = new CostCenterApprovalPage();
		
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
		deskConfirmationPage.selectSupplierAndLoadData(APP.getProperty("SupplierName"));
		Thread.sleep(3000);
		HashMap<String, HashMap<String, String>> itemDetailsInDeskConfirmation = deskConfirmationPage.getItemDetailsAfterLoading();
		deskConfirmationPage.clickOnSubmitBtn();
		Thread.sleep(5000);
		homePage.doLogOut();
		homePage.doLogin(OR.getProperty("CostCenterApprover"), OR.getProperty("otp"));
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("FINANCIAL APPROVAL LIST");
		Thread.sleep(2000);
		deskConfirmationPage.clickOnDeskApprovalRequestBasedOnPRnumber("Single Request", createdReq);
		Thread.sleep(5000);
		HashMap<String, HashMap<String, String>> itemDetailsInCostCenterPage = costCenterPage.getItemDetails();
		Set<String> keySetInCostCenter = itemDetailsInCostCenterPage.keySet();
		
		//getting item details from cost center page and storing it in a new hashmap
		HashMap<String, String> itemDataInCostCenter = new HashMap<String, String>();
		for(String s : keySetInCostCenter){
			itemDataInCostCenter =  itemDetailsInCostCenterPage.get(s);
			System.out.println(itemDataInCostCenter);
		}
		
		//getting item details from Desk confirmation page and storing it in a new hashmap
		Set<String> keySetInDesk = itemDetailsInDeskConfirmation.keySet();
		HashMap<String, String> itemDataInDesk = new HashMap<String, String>();
		for(String s : keySetInDesk){
			itemDataInDesk =  itemDetailsInDeskConfirmation.get(s);
			System.out.println(itemDataInDesk);
		}
		
		//Assertion for all the values displayed in Cost center page
		
		Assert.assertTrue(itemDetailsInCostCenterPage.containsKey(APP.getProperty("ItemName2")));
		Assert.assertTrue(itemDataInCostCenter.get("HSN Code").equals(itemDataInDesk.get("HSN Code")));
		Assert.assertTrue(itemDataInCostCenter.get("Qty").equals(itemDataInDesk.get("Qty")));
		Assert.assertTrue(itemDataInCostCenter.get("Price").equals(itemDataInDesk.get("Price")));
		Assert.assertTrue(itemDataInCostCenter.get("Value").equals(itemDataInDesk.get("Value")));
		Assert.assertTrue(itemDataInCostCenter.get("Discount").equals(itemDataInDesk.get("Discount")));
		Assert.assertTrue(itemDataInCostCenter.get("Tax").equals(itemDataInDesk.get("Tax")));
		Assert.assertTrue(itemDataInCostCenter.get("Net Amt").equals(itemDataInDesk.get("Net Amt")));
		
	}
}

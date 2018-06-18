package com.c2info.RMS_FinancialApproverTCs;

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
import com.c2info.RMS_UIActions.FinancialApprovalPage;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC002_VerifyItemDetailsInFinancialPage extends TestBase{

	public static final Logger log = Logger.getLogger(TC002_VerifyItemDetailsInFinancialPage.class.getName());
	
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
		FinancialApprovalPage fap = new FinancialApprovalPage();
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
		System.out.println(itemDetailsInCostCenterPage);
		Set<String> keySetInCostCenter = itemDetailsInCostCenterPage.keySet();
		
		//getting item details from cost center page and storing it in a new hashmap
		HashMap<String, String> itemDataInCostCenter = new HashMap<String, String>();
		for(String s : keySetInCostCenter){
			itemDataInCostCenter =  itemDetailsInCostCenterPage.get(s);
			System.out.println(itemDataInCostCenter);
		}
		costCenterPage.clickOnApproveButton();
		Thread.sleep(5000);
		homePage.doLogOut();
		homePage.doLogin(OR.getProperty("FinancialApprover"), "3213");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("FINANCIAL APPROVAL LIST");
		Thread.sleep(2000);
		fap.clickOnFinancialApprovalRequestBasedOnPRnumber("Single Request", createdReq);
		Thread.sleep(3000);
		
		HashMap<String, HashMap<String, String>> itemDetailsInFinancialPage = fap.getItemDetails();
		//getting item details from Financial Approval page and storing it in a new hashmap
			Set<String> keySetInFinancialPage = itemDetailsInFinancialPage.keySet();
			HashMap<String, String> itemDataInFinancialPage = new HashMap<String, String>();
			for(String s : keySetInFinancialPage){
				itemDataInFinancialPage =  itemDetailsInFinancialPage.get(s);
				System.out.println(itemDataInFinancialPage);
			}
		
			//Assertion for all the values displayed in Financial Approval page
			
			Assert.assertTrue(itemDetailsInFinancialPage.containsKey("NT Item 2"));
			Assert.assertTrue(itemDataInFinancialPage.get("HSN Code").equals(itemDataInCostCenter.get("HSN Code")));
			Assert.assertTrue(itemDataInFinancialPage.get("Price").equals(itemDataInCostCenter.get("Price")));
			Assert.assertTrue(itemDataInFinancialPage.get("Value").equals(itemDataInCostCenter.get("Value")));		
			Assert.assertTrue(itemDataInFinancialPage.get("Tax").equals(itemDataInCostCenter.get("Tax")));
			Assert.assertTrue(itemDataInFinancialPage.get("Net Amt").equals(itemDataInCostCenter.get("Net Amt")));
			Assert.assertTrue(itemDataInFinancialPage.get("Qty").equals(itemDataInCostCenter.get("Qty")));
			Assert.assertTrue(itemDataInFinancialPage.get("Discount").equals(itemDataInCostCenter.get("Discount")));
	}	
}

package com.c2info.RMS_POGeneratorTCs;

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
import com.c2info.RMS_UIActions.POGenerationPage;

public class TC002_VerifyItemDetailsInPOpage extends TestBase{

	public static final Logger log = Logger.getLogger(TC002_VerifyItemDetailsInPOpage.class.getName());
	
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
		POGenerationPage po = new POGenerationPage();
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
		costCenterPage.clickOnApproveButton();
		Thread.sleep(5000);
		homePage.doLogOut();
		homePage.doLogin(OR.getProperty("DeskConfirmer"), "3213");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("PO GENERATION");
		Thread.sleep(2000);
		po.selectSupplierPOlist(APP.getProperty("SupplierName"));
		HashMap<String, HashMap<String, String>> itemDetailsInPO = po.getItemDetailsAfterLoading();
		
		Set<String> keySetInPOPage = itemDetailsInPO.keySet();
		HashMap<String, String> itemDataInPO = new HashMap<String, String>();
		for(String s : keySetInPOPage){
			itemDataInPO =  itemDetailsInPO.get(s);
			System.out.println(itemDataInPO);
		}
			//Assertion for all the values displayed in Financial Approval page
			
			Assert.assertTrue(itemDetailsInPO.containsKey("NT Item 2"));
			Assert.assertTrue(itemDataInPO.get("HSN Code").equals(itemDataInFinancialPage.get("HSN Code")));
			Assert.assertTrue(itemDataInPO.get("Price").equals(itemDataInFinancialPage.get("Price")));
			Assert.assertTrue(itemDataInPO.get("Value").equals(itemDataInFinancialPage.get("Value").substring(0, itemDataInFinancialPage.get("Value").length()-3)));		
			Assert.assertTrue(itemDataInPO.get("Tax").equals(itemDataInFinancialPage.get("Tax")));
			Assert.assertTrue(itemDataInPO.get("Net Amt").equals(itemDataInFinancialPage.get("Net Amt")));
			Assert.assertTrue(itemDataInPO.get("Qty").equals(itemDataInFinancialPage.get("Qty").substring(0, itemDataInFinancialPage.get("Qty").length()-3)));
			Assert.assertTrue(itemDataInPO.get("Discount").equals(itemDataInFinancialPage.get("Discount")));
	}	
}

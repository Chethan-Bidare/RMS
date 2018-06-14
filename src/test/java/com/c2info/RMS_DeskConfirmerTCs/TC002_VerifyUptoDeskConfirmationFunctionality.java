package com.c2info.RMS_DeskConfirmerTCs;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.ApprovalPage;
import com.c2info.RMS_UIActions.DeskConfirmationPage;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC002_VerifyUptoDeskConfirmationFunctionality extends TestBase{

public static final Logger log = Logger.getLogger(TC002_VerifyUptoDeskConfirmationFunctionality.class.getName());
    
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void verifyDeskConfirmationFunctionality() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		ApprovalPage approvalPage = new ApprovalPage();
		DeskConfirmationPage dcp = new DeskConfirmationPage();
		
		homePage.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch(APP.getProperty("BranchCode"));
		newRequestPage.selectCostCenter("Information Technology");
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequestPage.enterAmount("5000");
		newRequestPage.clickOnAddButton();
		Thread.sleep(3000);
		newRequestPage.clickOnSubmitButton();
		Thread.sleep(3000);
		ArrayList<Integer> prNum = homePage.getPRnoForRequiredSectionInGridView("My Request");
		Collections.sort(prNum);
		System.out.println(prNum);
		int createdRequst = prNum.get(prNum.size()-1);
		String createdReq = String.valueOf(createdRequst);
		System.out.println("Check highest num---"+prNum.get(prNum.size()-1));
		homePage.doLogOut();
		Thread.sleep(5000);
		homePage.doLogin(OR.getProperty("Approver"), "0000");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		Thread.sleep(3000);
		homePage.selectAnOptionFromSubMenu("APPROVAL LIST");
		Thread.sleep(5000);
		approvalPage.clickOnRequestApprovalBasedOnPRnumber("Single Request",createdReq);
		Thread.sleep(3000);
		approvalPage.clickOnApproveButton();
		Thread.sleep(5000);
		homePage.doLogOut();
		Thread.sleep(5000);
		homePage.doLogin(OR.getProperty("DeskConfirmer"), "0000");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		Thread.sleep(3000);
		homePage.selectAnOptionFromSubMenu("DESK CONFIRMATION LIST");
		Thread.sleep(5000);
		dcp.clickOnDeskApprovalRequestBasedOnPRnumber("My Request",createdReq);
		Thread.sleep(5000);
		dcp.selectSupplierAndLoadData(APP.getProperty("SupplierName"));
		Thread.sleep(3000);
		dcp.clickOnSubmitBtn();
		Thread.sleep(5000);
		homePage.doLogOut();
		Thread.sleep(5000);
		homePage.doLogin(OR.getProperty("CostCenterApprover"), "0000");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		Thread.sleep(3000);
		homePage.selectAnOptionFromSubMenu("FINANCIAL APPROVAL LIST");
		Thread.sleep(5000);
		ArrayList<String> prNums = dcp.getPRnumber(); //using this function in Cost center page to get PR numbers
		assertTrue(prNums.contains(createdReq));
	}
	
}

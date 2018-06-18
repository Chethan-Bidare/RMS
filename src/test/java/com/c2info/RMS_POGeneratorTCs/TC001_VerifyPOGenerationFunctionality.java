package com.c2info.RMS_POGeneratorTCs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

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

public class TC001_VerifyPOGenerationFunctionality extends TestBase{

	public static final Logger Log = Logger.getLogger(TC001_VerifyPOGenerationFunctionality.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void verifyUptoCostCenterApprovalFunctionality() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		ApprovalPage approvalPage = new ApprovalPage();
		DeskConfirmationPage dcp = new DeskConfirmationPage();
		CostCenterApprovalPage ccp = new CostCenterApprovalPage();
		FinancialApprovalPage fap = new FinancialApprovalPage();
		POGenerationPage poGenerationPage = new POGenerationPage();
		homePage.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch(APP.getProperty("BranchCode"));
		newRequestPage.selectCostCenter("Information Technology");
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequestPage.enterAmount("25000");
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
		Thread.sleep(3000);
		homePage.doLogin(OR.getProperty("Approver"), "0000");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		Thread.sleep(3000);
		homePage.selectAnOptionFromSubMenu("APPROVAL LIST");
		Thread.sleep(3000);
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
		Thread.sleep(3000);
		dcp.clickOnDeskApprovalRequestBasedOnPRnumber("My Request",createdReq);
		Thread.sleep(5000);
		dcp.selectSupplierAndLoadData(APP.getProperty("SupplierName"));
		Thread.sleep(3000);
		dcp.clickOnSubmitBtn();
		Thread.sleep(5000);
		homePage.doLogOut();
		Thread.sleep(3000);
		homePage.doLogin(OR.getProperty("CostCenterApprover"), "0000");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		Thread.sleep(3000);
		homePage.selectAnOptionFromSubMenu("FINANCIAL APPROVAL LIST");
		Thread.sleep(3000);
		approvalPage.clickOnRequestApprovalBasedOnPRnumber("Single Request",createdReq);
		ccp.clickOnApproveButton();
		Thread.sleep(5000);
		homePage.doLogOut();
		Thread.sleep(3000);
		homePage.doLogin(OR.getProperty("FinancialApprover"), "0000");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		Thread.sleep(3000);
		homePage.selectAnOptionFromSubMenu("FINANCIAL APPROVAL LIST");
		Thread.sleep(5000);
		fap.clickOnFinancialApprovalRequestBasedOnPRnumber("Single Project", createdReq);
		ccp.clickOnApproveButton();
		Thread.sleep(5000);
		homePage.doLogOut();
		Thread.sleep(5000);
		homePage.doLogin(OR.getProperty("DeskConfirmer"), "0000");
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("PO GENERATION");
		Thread.sleep(3000);
		ArrayList<String> poNums = poGenerationPage.getGeneratedPOList();
		String poNumb = poNums.get(poNums.size()-1);
		System.out.println("Last PO Generated = " +poNumb);
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("PO GENERATION");
		Thread.sleep(3000);
		poGenerationPage.selectSupplierPOlist(APP.getProperty("SupplierName"));
		Thread.sleep(3000);
		poGenerationPage.searchData(createdReq);
		poGenerationPage.selectPaymentTerms();
		poGenerationPage.clickOnSubmitButton();
		Thread.sleep(5000);
		ArrayList<String> poNumsAfterPO = poGenerationPage.getGeneratedPOList();
		String poNumbAfterPO = poNumsAfterPO.get(poNumsAfterPO.size()-1);
		System.out.println("Last PO Generated = " +poNumbAfterPO);
		Assert.assertEquals(poNumsAfterPO.size(), poNums.size()+1);
		
	}
}

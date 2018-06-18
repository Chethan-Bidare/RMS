package com.c2info.RMS_HomePageTCs;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.CostCenterApprovalPage;
import com.c2info.RMS_UIActions.DeskConfirmationPage;
import com.c2info.RMS_UIActions.FinancialApprovalPage;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.POGenerationPage;

import org.testng.annotations.Test;
public class TC_002_Check extends TestBase{

	
public static final Logger log = Logger.getLogger(TC_001_VerifyLoginLogOut.class.getName());
    
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("DeskConfirmer"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void check() throws InterruptedException{
		HomePage homePage = new HomePage();
		CostCenterApprovalPage costCenterPage = new CostCenterApprovalPage();
		DeskConfirmationPage deskConfirmationPage = new DeskConfirmationPage();
		FinancialApprovalPage fap = new FinancialApprovalPage();
		POGenerationPage po = new POGenerationPage();
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("PO GENERATION");
		Thread.sleep(5000);
		po.selectSupplierPOlist(APP.getProperty("SupplierName"));
		HashMap<String, HashMap<String, String>> itemDetails = po.getItemDetailsAfterLoading();
		System.out.println(itemDetails);
		
		
	}
}

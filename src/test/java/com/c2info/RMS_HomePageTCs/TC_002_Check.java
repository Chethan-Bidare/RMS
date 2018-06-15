package com.c2info.RMS_HomePageTCs;

import java.io.IOException;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.CostCenterApprovalPage;
import com.c2info.RMS_UIActions.DeskConfirmationPage;
import com.c2info.RMS_UIActions.HomePage;
import org.testng.annotations.Test;
public class TC_002_Check extends TestBase{

	
public static final Logger log = Logger.getLogger(TC_001_VerifyLoginLogOut.class.getName());
    
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("CostCenterApprover"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void check() throws InterruptedException{
		HomePage homePage = new HomePage();
		CostCenterApprovalPage ccp = new CostCenterApprovalPage();
		DeskConfirmationPage dcp = new DeskConfirmationPage();
		homePage.ClickOnMenuOption("TRANSACTIONS");
		homePage.selectAnOptionFromSubMenu("FINANCIAL APPROVAL LIST");
		Thread.sleep(2000);
		dcp.clickOnDeskApprovalRequestBasedOnPRnumber("Single Request", "181");
		Thread.sleep(5000);
		HashMap<String, HashMap<String, String>> itemData = ccp.getItemDetails(); 
		System.out.println(itemData);
		
		
	}
}

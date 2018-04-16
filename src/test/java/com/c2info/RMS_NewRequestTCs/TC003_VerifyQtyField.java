package com.c2info.RMS_NewRequestTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.NewRequestPage;

public class TC003_VerifyQtyField extends TestBase{
	
	public static final Logger log = Logger.getLogger(TC003_VerifyQtyField.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Employee"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		log.info("Login Successfull");
	}
	
	@Test
	public void verifyQtyField() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		homePage.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch("BANGLORE");
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		Thread.sleep(2000);
		newRequestPage.enterQty("2");
		newRequestPage.clickOnAddButton();
		Thread.sleep(2000);
		
	}

}

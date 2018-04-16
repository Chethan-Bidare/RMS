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

public class TC001_VerifyNewRequestFunctionailtyForSingleProduct extends TestBase{
	
public static final Logger log = Logger.getLogger(TC001_VerifyNewRequestFunctionailtyForSingleProduct.class.getName());
    
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
	public void verifyNewRequestFunctionality() throws InterruptedException{
		HomePage homePage = new HomePage();
		NewRequestPage newRequestPage = new NewRequestPage();
		ArrayList<Integer> prnumbers = homePage.getPRnoForRequiredSectionInGridView("My Request");
		Collections.sort(prnumbers);
		int ExpectedprNo = prnumbers.get(prnumbers.size()-1);
		homePage.clickOnNewRequestButton();
		newRequestPage.selectRequestType("Item");
		newRequestPage.selectRefBranch("BANGLORE");
		newRequestPage.selectProductFromAutoSuggestionBox(APP.getProperty("ItemName2"));
		//newRequestPage.enterQty("2");
		//newRequestPage.increaseQty(2);
		//newRequestPage.decreaseQty(2);
		Thread.sleep(2000);
		newRequestPage.clickOnAddButton();
		Thread.sleep(3000);
		newRequestPage.clickOnSubmitButton();
		Thread.sleep(3000);
		ArrayList<Integer> prNum = homePage.getPRnoForRequiredSectionInGridView("My Request");
		Collections.sort(prNum);
		int ActualprNo = prNum.get(prNum.size()-1);
		Assert.assertEquals(ActualprNo, ExpectedprNo+1);
		
	}

}

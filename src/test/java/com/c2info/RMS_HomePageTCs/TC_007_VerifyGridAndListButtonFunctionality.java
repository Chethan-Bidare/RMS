package com.c2info.RMS_HomePageTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;

public class TC_007_VerifyGridAndListButtonFunctionality extends TestBase{

public static final Logger log = Logger.getLogger(TC_007_VerifyGridAndListButtonFunctionality.class.getName());
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		log.info("Login Successfull");
	}
	
	@Test(priority=0)
	public void verifyListViewButtonFunctionality() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.clickOnGridORListView("List");
		Thread.sleep(2000);
		Assert.assertEquals(hp.listViewIsDisplayed(), true);
	}
	
	@Test(priority=1)
	public void verifyGridViewButtonFunctionality() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.clickOnGridORListView("grid");
		Thread.sleep(2000);
		Assert.assertEquals(hp.gridViewIsDisplayed(), true);
	}
}

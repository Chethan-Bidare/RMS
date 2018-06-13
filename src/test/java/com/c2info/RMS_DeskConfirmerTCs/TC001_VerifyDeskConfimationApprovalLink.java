package com.c2info.RMS_DeskConfirmerTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;

public class TC001_VerifyDeskConfimationApprovalLink extends TestBase{

public static final Logger log = Logger.getLogger(TC001_VerifyDeskConfimationApprovalLink.class.getName());
    
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("DeskConfirmer"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void verifyDeskConfirmationLink() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.clickOnPendingNotifications("Desk Approval Pending");
		Thread.sleep(5000);
		Assert.assertEquals(getPageTitle(),APP.getProperty("DeskConfirmationPageTitle"));
	}
}

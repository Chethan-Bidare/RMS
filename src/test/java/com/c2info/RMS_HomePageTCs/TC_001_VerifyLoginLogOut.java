package com.c2info.RMS_HomePageTCs;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;

public class TC_001_VerifyLoginLogOut extends TestBase {
	
    public static final Logger log = Logger.getLogger(TC_001_VerifyLoginLogOut.class.getName());
    WebDriverWait wait = new WebDriverWait(driver, 60);
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		
	}
	
	@Test(priority=0)
	public void verifyLogin(){
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
		Assert.assertEquals(getPageTitle(),APP.getProperty("HomepageTitle"));
	}
	
	
	@Test(priority=1)
	public void verifyLogOut() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.doLogOut();
		Assert.assertEquals(getPageTitle(),APP.getProperty("LoginPageTitle"));
	}
}

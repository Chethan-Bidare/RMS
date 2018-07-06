package com.c2info.RMS_HomePageTCs;

import java.io.IOException;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test ;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;


public class TC_008_VerifyBrokenLinks extends TestBase{

	public static final Logger log = Logger.getLogger(TC_008_VerifyBrokenLinks.class.getName());
	
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		
	}
	
	@Test
	public void verifyBrokenLinks(){
		
		System.out.println(getBrokenLinks());
		
	}
}

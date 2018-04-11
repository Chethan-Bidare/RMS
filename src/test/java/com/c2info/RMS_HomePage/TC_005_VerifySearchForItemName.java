package com.c2info.RMS_HomePage;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;

public class TC_005_VerifySearchForItemName extends TestBase{

public static final Logger log = Logger.getLogger(TC_005_VerifySearchForItemName.class.getName());
    
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Employee"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		log.info("Login Successfull");
	}

	@Test(priority=0)
	public void verifyItemSearchInGridViewForMyRequest() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.clickOnGlobalSearch(APP.getProperty("ItemName"));
		Thread.sleep(3000);
		ArrayList<String> itemNamesAfterSearch = hp.getItemNamesForRequiredSectionInGridView("My Request");
		//System.out.println(itemNamesAfterSearch.listIterator().);
		Assert.assertTrue(itemNamesAfterSearch.listIterator().next().equals(APP.getProperty("ItemName")));
	}
	
	@Test(priority=1)
	public void verifyItemSearchInListViewForMyRequest() throws InterruptedException{
		HomePage hp = new HomePage();
		refreshPage();
		hp.clickOnGridORListView("List");
		hp.clickOnGlobalSearch(APP.getProperty("ItemName"));
		Thread.sleep(3000);
		ArrayList<String> itemNamesAfterSearch = hp.getItemNamesForRequiredSectionInListView("My Request");
		//System.out.println(itemNamesAfterSearch.listIterator().);
		Assert.assertTrue(itemNamesAfterSearch.listIterator().next().equals(APP.getProperty("ItemName")));
	}
}

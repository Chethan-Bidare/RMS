package com.c2info.RMS_HomePageTCs;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;

public class TC_006_VerifySearchForEmployeeName extends TestBase{
	
public static final Logger log = Logger.getLogger(TC_006_VerifySearchForEmployeeName.class.getName());
    
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
	public void verifyEmployeeSearchInGridView() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.clickOnGlobalSearch(APP.getProperty("EmployeeName"));
		Thread.sleep(3000);
		ArrayList<String> employeeNamesAfterSearch = hp.getEmployeeNamesInGridView();
		//System.out.println(itemNamesAfterSearch.listIterator().);
		Assert.assertTrue(employeeNamesAfterSearch.listIterator().next().equals(APP.getProperty("EmployeeName")));
	}
	
	
	@Test(priority=1)
	public void verifyEmployeeSearchInListView() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.clickOnGridORListView("List");
		Thread.sleep(2000);
		hp.clickOnGlobalSearch(APP.getProperty("EmployeeName"));
		Thread.sleep(3000);
		ArrayList<String> employeeNamesAfterSearch = hp.getEmployeeNamesInListView();
		//System.out.println(itemNamesAfterSearch.listIterator().);
		Assert.assertTrue(employeeNamesAfterSearch.listIterator().next().equals(APP.getProperty("EmployeeName")));
	}
}

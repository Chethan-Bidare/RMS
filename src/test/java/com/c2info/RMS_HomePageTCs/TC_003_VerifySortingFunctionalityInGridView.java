package com.c2info.RMS_HomePageTCs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;

public class TC_003_VerifySortingFunctionalityInGridView extends TestBase{

public static final Logger log = Logger.getLogger(TC_003_VerifySortingFunctionalityInGridView.class.getName());
    
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		init();
		log.info("Initializing Setup");
		HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Employee"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		log.info("Login Successfull");
	}
	
	@Test(priority=1)
	public void verifySortAscendingInGridView() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.selectVisibleTextFromSortingDropdown("Order Asce");
		Thread.sleep(2000);
		ArrayList<Integer> AfterSortingInMyRequest = hp.getPRnoForRequiredSectionInGridView("My Request");
		ArrayList<Integer> AfterSortingInProjectwise = hp.getPRnoForRequiredSectionInGridView("Projectwise Request");
		System.out.println(AfterSortingInProjectwise);
		ArrayList<Integer> AfterSortingInApprovalList = hp.getPRnoForRequiredSectionInGridView("Approval List");
		ArrayList<Integer> verifySortedInMyRequest = hp.getPRnoForRequiredSectionInGridView("My Request");
		Collections.sort(verifySortedInMyRequest);
		ArrayList<Integer> verifySortedInProjectwise = hp.getPRnoForRequiredSectionInGridView("Projectwise Request");
		System.out.println(verifySortedInProjectwise);
		Collections.sort(verifySortedInProjectwise);
		ArrayList<Integer> verifySortedInApprovalList = hp.getPRnoForRequiredSectionInGridView("Approval List");
		Collections.sort(verifySortedInApprovalList);
		Assert.assertEquals(AfterSortingInMyRequest, verifySortedInMyRequest);
		Assert.assertEquals(AfterSortingInProjectwise, verifySortedInProjectwise);
		Assert.assertEquals(AfterSortingInApprovalList, verifySortedInApprovalList);
	}
	
	@Test(priority=2)
	public void verifySortDescendingInGridView() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.selectVisibleTextFromSortingDropdown("Order Desc");
		Thread.sleep(2000);
		ArrayList<Integer> AfterSortingInMyRequest = hp.getPRnoForRequiredSectionInGridView("My Request");
		ArrayList<Integer> AfterSortingInProjectwise = hp.getPRnoForRequiredSectionInGridView("Projectwise Request");
		ArrayList<Integer> AfterSortingInApprovalList = hp.getPRnoForRequiredSectionInGridView("Approval List");
		ArrayList<Integer> verifySortedInMyRequest = hp.getPRnoForRequiredSectionInGridView("My Request");
		Collections.sort(verifySortedInMyRequest);
		Collections.reverse(verifySortedInMyRequest);
		ArrayList<Integer> verifySortedInProjectwise = hp.getPRnoForRequiredSectionInGridView("Projectwise Request");
		Collections.sort(verifySortedInProjectwise);
		Collections.reverse(verifySortedInProjectwise);
		ArrayList<Integer> verifySortedInApprovalList = hp.getPRnoForRequiredSectionInGridView("Approval List");
		Collections.sort(verifySortedInApprovalList);
		Collections.reverse(verifySortedInApprovalList);
		Assert.assertEquals(AfterSortingInMyRequest, verifySortedInMyRequest);
		Assert.assertEquals(AfterSortingInProjectwise, verifySortedInProjectwise);
		Assert.assertEquals(AfterSortingInApprovalList, verifySortedInApprovalList);
	
	}

}

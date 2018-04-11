package com.c2info.RMS_HomePage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.HomePage;

public class TC_004_VerifySortingFunctionalityInListView extends TestBase{

public static final Logger log = Logger.getLogger(TC_004_VerifySortingFunctionalityInListView.class.getName());
    
    
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
	public void verifySortAscendingInListView() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.clickOnGridORListView("List");
		hp.selectVisibleTextFromSortingDropdown("Order Asce");
		Thread.sleep(2000);
		ArrayList<Integer> AfterSortingInMyRequest = hp.getPrNoForRequiredSectionInListView("My Request");
		ArrayList<Integer> AfterSortingInProjectwise = hp.getPrNoForRequiredSectionInListView("Projectwise Request");
		System.out.println(AfterSortingInProjectwise);
		ArrayList<Integer> AfterSortingInApprovalList = hp.getPrNoForRequiredSectionInListView("Approval List");
		ArrayList<Integer> verifySortedInMyRequest = hp.getPrNoForRequiredSectionInListView("My Request");
		Collections.sort(verifySortedInMyRequest);
		ArrayList<Integer> verifySortedInProjectwise = hp.getPrNoForRequiredSectionInListView("Projectwise Request");
		System.out.println(verifySortedInProjectwise);
		Collections.sort(verifySortedInProjectwise);
		ArrayList<Integer> verifySortedInApprovalList = hp.getPrNoForRequiredSectionInListView("Approval List");
		Collections.sort(verifySortedInApprovalList);
		Assert.assertEquals(AfterSortingInMyRequest, verifySortedInMyRequest);
		Assert.assertEquals(AfterSortingInProjectwise, verifySortedInProjectwise);
		Assert.assertEquals(AfterSortingInApprovalList, verifySortedInApprovalList);
	}
	
	@Test(priority=2)
	public void verifySortDescendingInListView() throws InterruptedException{
		HomePage hp = new HomePage();
		hp.selectVisibleTextFromSortingDropdown("Order Desc");
		Thread.sleep(2000);
		ArrayList<Integer> AfterSortingInMyRequest = hp.getPrNoForRequiredSectionInListView("My Request");
		ArrayList<Integer> AfterSortingInProjectwise = hp.getPrNoForRequiredSectionInListView("Projectwise Request");
		ArrayList<Integer> AfterSortingInApprovalList = hp.getPrNoForRequiredSectionInListView("Approval List");
		ArrayList<Integer> verifySortedInMyRequest = hp.getPrNoForRequiredSectionInListView("My Request");
		Collections.sort(verifySortedInMyRequest);
		Collections.reverse(verifySortedInMyRequest);
		ArrayList<Integer> verifySortedInProjectwise = hp.getPrNoForRequiredSectionInListView("Projectwise Request");
		Collections.sort(verifySortedInProjectwise);
		Collections.reverse(verifySortedInProjectwise);
		ArrayList<Integer> verifySortedInApprovalList = hp.getPrNoForRequiredSectionInListView("Approval List");
		Collections.sort(verifySortedInApprovalList);
		Collections.reverse(verifySortedInApprovalList);
		Assert.assertEquals(AfterSortingInMyRequest, verifySortedInMyRequest);
		Assert.assertEquals(AfterSortingInProjectwise, verifySortedInProjectwise);
		Assert.assertEquals(AfterSortingInApprovalList, verifySortedInApprovalList);
	
	}

}

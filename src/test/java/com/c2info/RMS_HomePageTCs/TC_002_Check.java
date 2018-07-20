package com.c2info.RMS_HomePageTCs;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import com.c2info.RMS_TestBase.TestBase;
import com.c2info.RMS_UIActions.CostCenterApprovalPage;
import com.c2info.RMS_UIActions.DeskConfirmationPage;
import com.c2info.RMS_UIActions.FinancialApprovalPage;
import com.c2info.RMS_UIActions.HomePage;
import com.c2info.RMS_UIActions.POGenerationPage;

import org.testng.annotations.Test;
public class TC_002_Check extends TestBase{

	
public static final Logger log = Logger.getLogger(TC_001_VerifyLoginLogOut.class.getName());
    /*WebDriverWait wait = new WebDriverWait(driver, 60);*/
    
	@BeforeClass
	public void setup() throws IOException, InterruptedException{
		selectBrowser("chrome");
		getBaseUrl("http://111.93.190.155/eg360test/frontend/web/site/login");
		log.info("Initializing Setup");
		/*HomePage hp = new HomePage();
		hp.doLogin(OR.getProperty("Requestor"),OR.getProperty("otp"));
		hp.waitForHomePagetoLoad();
		*/
	}
	
	@Test
	public void check() throws InterruptedException{
		
		driver.findElement(By.id("mobile")).sendKeys("8547327779");
		driver.findElement(By.id("mob_check")).click();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		JavascriptExecutor jse = (JavascriptExecutor)driver ;
		jse.executeScript("arguments[0].scrollIntoView(true)",driver.findElement(By.xpath(".//div[3]/canvas")));
		Thread.sleep(5000);
		
		Actions action = new Actions(driver);
		/*action.moveToElement(driver.findElement(By.xpath(".//div[3]/canvas")));*/
		
		
		action.moveToElement(driver.findElement(By.xpath(".//div[3]/canvas"))).build().perform();
		Thread.sleep(5000);
		
		
		
	}
}

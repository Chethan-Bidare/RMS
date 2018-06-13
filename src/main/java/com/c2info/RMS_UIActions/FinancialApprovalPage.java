package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.c2info.RMS_TestBase.TestBase;

public class FinancialApprovalPage extends TestBase{

	public static final Logger log = Logger.getLogger(FinancialApprovalPage.class.getName());
	
	/*
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	*/
	
	
	
	
	public FinancialApprovalPage(){
		PageFactory.initElements(driver, this);
	}
	
	public ArrayList<String> getPRnumber(){
		List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[1]"));
		ArrayList<String> prNums = new ArrayList<String>();
		for(WebElement we : row){
			if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("PR No : ","").trim();
				prNums.add(PRnum);
				}
			}
		return prNums ;
	}
	
	
	public void clickOnRequestApprovalBasedOnPRnumber(String prNum){
		List<WebElement> reqs = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[1]"));
		for(WebElement we : reqs){
			if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("PR No : ","").trim();
				if(PRnum.equalsIgnoreCase(prNum)){
					we.click();
					break;
				}
			}
		}
	}
	
	
	
	
}

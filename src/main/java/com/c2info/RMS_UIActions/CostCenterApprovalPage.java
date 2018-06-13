package com.c2info.RMS_UIActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.c2info.RMS_TestBase.TestBase;

public class CostCenterApprovalPage extends TestBase{

	public static final Logger log = Logger.getLogger(CostCenterApprovalPage.class.getName());
	
	
	@FindBy(id="remark")
	WebElement ApproverRemark ;
	
	@FindBy(xpath=".//*[@id='myModal']/div/div/div[3]/button[2]")
	WebElement ApproveOKbuton ;
	/*
	@FindBy(id="")
	WebElement SuccessOkBtn ;
	
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
	
	
	
	public CostCenterApprovalPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnApproveButton() throws InterruptedException{
		List<WebElement> buttons = driver.findElements(By.xpath(".//*/button/b"));
		for( WebElement we : buttons){
			if(we.getText().equalsIgnoreCase("approve")){
				we.click();
				Thread.sleep(2000);
				ApproverRemark.sendKeys("Approved");
				ApproveOKbuton.click();
				break;
			}
		}
	}
}

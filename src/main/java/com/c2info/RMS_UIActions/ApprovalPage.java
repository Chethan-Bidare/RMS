package com.c2info.RMS_UIActions;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.c2info.RMS_TestBase.TestBase;

public class ApprovalPage extends TestBase{

	public static final Logger log = Logger.getLogger(ApprovalPage.class.getName());
	

	
	@FindBy(id="remark")
	WebElement ApproverRemark ;
	
	@FindBy(id="approveBtn")
	WebElement ApproveOKbuton ;
	
	@FindBy(id="modal_ok1")
	WebElement SuccessOkBtn ;
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
	
	*/
	
	public ApprovalPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnRequestApprovalBasedOnPRnumber(String prNum){
		List<WebElement> reqs = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[1]"));
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
	
	
	public void clickOnApproveButton() throws InterruptedException{
		List<WebElement> buttons = driver.findElements(By.xpath(".//*/button/b"));
		for( WebElement we : buttons){
			if(we.getText().equalsIgnoreCase("approve")){
				we.click();
				Thread.sleep(2000);
				ApproverRemark.sendKeys("Approved");
				ApproveOKbuton.click();
				Thread.sleep(5000);
				SuccessOkBtn.click();
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

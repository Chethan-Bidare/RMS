package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.c2info.RMS_TestBase.TestBase;

public class CostCenterApprovalPage extends TestBase{

	public static final Logger log = Logger.getLogger(CostCenterApprovalPage.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
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
				wait.until(ExpectedConditions.elementToBeClickable(ApproverRemark));
				ApproverRemark.sendKeys("Approved");
				wait.until(ExpectedConditions.elementToBeClickable(ApproveOKbuton));
				ApproveOKbuton.click();
				break;
			}
		}
	}
	
	public HashMap<String,HashMap<String,String>> getItemDetails(){
		HashMap<String, HashMap<String,String>> itemDetails = new HashMap<String, HashMap<String,String>>();
		List<WebElement> items = driver.findElements(By.xpath(".//*[@id='items-value']/table/tbody/tr"));
		System.out.println(items.size());
		for(int i=1 ; i<items.size(); i++){
			String itemName = driver.findElement(By.xpath(".//*[@id='items-value']/table/tbody/tr["+i+"]/td[1]")).getText();
			List<WebElement> cols = driver.findElements(By.xpath(".//*[@id='items-value']/table/tbody/tr["+i+"]/td"));
			HashMap<String, String> tempData = new HashMap<String, String>();
			for(int j=2;j<= cols.size(); j++){
				String text = driver.findElement(By.xpath(".//*[@id='items-value']/table/tbody/tr["+i+"]/td["+j+"]")).getText();
				text = text.trim().toString();
				String header = driver.findElement(By.xpath(".//*[@id='items-value']/table/thead/tr/th["+j+"]")).getText();
				header = header.trim().toString();
				tempData.put(header, text);
			}
			itemDetails.put(itemName, tempData);
		}
		return itemDetails;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

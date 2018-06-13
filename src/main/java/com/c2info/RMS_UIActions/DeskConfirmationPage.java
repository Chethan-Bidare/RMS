package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.c2info.RMS_TestBase.TestBase;

public class DeskConfirmationPage extends TestBase{

	public static final Logger log = Logger.getLogger(DeskConfirmationPage.class.getName());
	
	
	@FindBy(id="select2-empname-container")
	WebElement Supplierbox ;
	
	@FindBy(xpath="html/body/span/span/span[1]/input")
	WebElement SupplierEntry ;
	
	@FindBy(id="btn_load_sup")
	WebElement LoadDataBtn ;
	
	@FindBy(id="btn_submit")
	WebElement SubmitBtn ;
	
	@FindBy(id="btn_priceupdate_ok")
	WebElement Updatebtn ;
	
	@FindBy(id="btn_rate")
	WebElement OKbtn ;
	/*
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	*/
	
	
	
	
	
	public DeskConfirmationPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnDeskApprovalRequestBasedOnPRnumber(String RequestType,String PRnumber){
		
		if(RequestType.equalsIgnoreCase("My Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[1]"));	
			for(WebElement we : row){
				if(we.isDisplayed()==true){
					String PRnum = we.getText();
					PRnum = PRnum.replaceAll("PR No : ","").trim();
					if(PRnum.equalsIgnoreCase(PRnumber)){
						we.click();
						break;
					}
				}
			}
		}
		else if(RequestType.equalsIgnoreCase("Projectwise Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel1']/div/div/a/div/div/div[2]/p[1]"));
			for(WebElement we : row){
				if(we.isDisplayed()==true){
					String PRnum = we.getText();
					PRnum = PRnum.replaceAll("Group Id:", "");
					PRnum = PRnum.trim();
					if(PRnum.equalsIgnoreCase(PRnumber)){
						we.click();
					}
				}
			}
		}
	}
	
	public ArrayList<String> getPRnumber(){
		List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[1]"));
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
	
	public void selectSupplierAndLoadData(String suppName) throws InterruptedException{
		Supplierbox.click();
		SupplierEntry.sendKeys(suppName);
		Thread.sleep(2000);
		SelectFromAutoSuggestionSearch(suppName);
		Thread.sleep(5000);
		LoadDataBtn.click();
		Thread.sleep(2000);
	}
	
	public void clickOnSubmitBtn() throws InterruptedException{
		SubmitBtn.click();
		Thread.sleep(5000);
		Updatebtn.click();
		Thread.sleep(5000);
		OKbtn.click();
	}
}

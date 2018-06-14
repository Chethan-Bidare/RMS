package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.c2info.RMS_TestBase.TestBase;

public class POGenerationPage extends TestBase{

	
	public static final Logger log = Logger.getLogger(POGenerationPage.class.getName());
	
	
	@FindBy(id="selectall")
	WebElement SelectAllCheckBox ;
	
	@FindBy(id="btn_submit")
	WebElement SubmitButton ;
	
	@FindBy(id="payment_terms1")
	WebElement PaymentTerms ;
	
	@FindBy(id="terms-duration1")
	WebElement Duration ;
	
	@FindBy(id="percentage1")
	WebElement Percentage ;
	
	@FindBy(xpath=".//*[@id='setup_filter']/label/input")
	WebElement SearchField ;
	
	@FindBy(id="setup1_next")
	WebElement GeneratedPOListNextButton ;
	
	@FindBy(className="paginate_button next disabled")
	WebElement GeneratedPOListNextButtonDisabled ;
	
	@FindBy(id="btn_priceupdate_ok")
	WebElement OKbtn ;
	
	
	
	public POGenerationPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void selectSupplierPOlist(String suppName){
		List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='setup']/tbody/tr"));
		
		for(int i=1 ; i<=rows.size(); i++){
			String text = driver.findElement(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td[2]")).getText();
			if(text.contains(suppName)){
				driver.findElement(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td[2]")).click();
				break ;
			}
		}
	}
	
	public ArrayList<String> getRefIDs(){
		List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='setup']/tbody/tr"));
		ArrayList<String> refIDs = new ArrayList<String>();
		for(int i=1 ; i<=rows.size(); i++){
			String text = driver.findElement(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td[2]")).getText();
			text = text.trim();
			refIDs.add(text);
		}
		return refIDs ;
	}
	
	
	public void selectPaymentTerms(){
		Select select = new Select(PaymentTerms);
		select.selectByIndex(1);
		Duration.sendKeys("10");
		Percentage.sendKeys("100");
	}
	
	public void clickOnSubmitButton() throws InterruptedException{
		SubmitButton.click();
		Thread.sleep(5000);
		OKbtn.click();
	}
	
	public void searchData(String searchText){
		SearchField.sendKeys(searchText);
	}
	
	public ArrayList<String> getGeneratedPOList() throws InterruptedException{
		ArrayList<String> poNumbers = new ArrayList<String>();
		List<WebElement> pages = driver.findElements(By.xpath(".//*[@id='setup1_paginate']/span/a"));
		
		try {
			for(int i=1; i<=pages.size();i++){
				driver.findElement(By.xpath(".//*[@id='setup1_paginate']/span/a["+i+"]")).click();
				Thread.sleep(3000);
				List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='setup1']/tbody/tr/td[2]"));
				Thread.sleep(3000);
				for(WebElement we : rows){
					String text = we.getText().trim().toString();
					poNumbers.add(text);
				}
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Stale element");
			
		}
		System.out.println(poNumbers);
		return poNumbers;
	}
}

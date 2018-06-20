package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	
	public void clickOnFinancialApprovalRequestBasedOnPRnumber(String RequestType,String PRnumber){
			
			if(RequestType.equalsIgnoreCase("Single Request")){
				List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[1]"));	
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
				List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel3']/div/div/a/div/div/div[2]/p[1]"));
				for(WebElement we : row){
					if(we.isDisplayed()==true){
						String PRnum = we.getText();
						PRnum = PRnum.replaceAll("Project Id:", "");
						PRnum = PRnum.trim();
						if(PRnum.equalsIgnoreCase(PRnumber)){
							we.click();
						}
					}
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
	
	public String getStatusBasedOnPRnumber(String PRnumber,String RequestType){
		String status = null ;
		if(RequestType.equals("My Request")){
			List<WebElement> prNums = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[1]"));
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[3]"));
			
			HashMap<String, String> prNumWithStatus = new HashMap<String, String>();
			for(int i=0,j=0; i<prNums.size() && j<row.size(); i++,j++){
				String prNum = prNums.get(i).getText();
				prNum = prNum.replaceAll("PR No : ","").trim();
				
				String Status = row.get(j).getText();
				Status = Status.replaceAll("Status : ","").trim();
				prNumWithStatus.put(prNum, Status);
			}
			
			if(prNumWithStatus.containsKey(PRnumber)){
				 status = prNumWithStatus.get(PRnumber);
			}
			else{
				System.out.println("PR number not found");
			}
			return status ;
		}
		
		else if(RequestType.equals("Projectwise Request")){
			List<WebElement> prNums = driver.findElements(By.xpath("//*[@id='theCarousel3']/div/div/a/div/div/div[2]/p[1]"));
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel3']/div/div/a/div/div/div[2]/p[3]"));
			HashMap<String, String> prNumWithStatus = new HashMap<String, String>();
			for(int i=0,j=0; i<prNums.size() && j<row.size(); i++,j++){
				String prNum = prNums.get(i).getText();
				prNum = prNum.replaceAll("PR No : ","").trim();
				String Status = row.get(j).getText();
				Status = Status.replaceAll("Status : ","").trim();
				prNumWithStatus.put(prNum, Status);
				
			}
			if(prNumWithStatus.containsKey(PRnumber)){
				 status = prNumWithStatus.get(PRnumber);
			}
			else{
				System.out.println("PR number not found");
			}
			return status ;
		}
		
		return status ;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

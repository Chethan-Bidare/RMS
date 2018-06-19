package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.HashMap;
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
	
	public void clickOnRequestApprovalBasedOnPRnumber(String approvalType,String prNum){
		 
		if(approvalType.equalsIgnoreCase("Single Request")){
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
		else if(approvalType.equalsIgnoreCase("Project Request")){
			List<WebElement> reqs = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[1]"));
			for(WebElement we : reqs){
				if(we.isDisplayed()==true){
					String PRnum = we.getText();
					System.out.println(PRnum);
					PRnum = PRnum.replaceAll("Project Id: ","").trim();
					if(PRnum.equalsIgnoreCase(prNum)){
						we.click();
						break;
					}
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
	
	public HashMap<String,ArrayList<String>> getItemNameAndData(){
		
		HashMap<String,ArrayList<String>> itemNamesAndData = new HashMap<String, ArrayList<String>>();
		List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='setup']/tbody/tr"));
		for(int i=1; i<=rows.size(); i++){
			List<WebElement> cols = driver.findElements(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td"));
			String key = driver.findElement(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td[1]")).getText();
			key = key.trim().toString();
			ArrayList<String> dataSet = new ArrayList<String>();
			for(int j=2;j<=cols.size(); j++){
				if(j==2){
					String text;
					try {
						text = driver.findElement(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td["+j+"]")).getText();
					} catch (Exception e) {
						text = driver.findElement(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td["+j+"]/input")).getAttribute("value");
					}
					text = text.trim().toString();
					dataSet.add(text);
				}
				else{
					String text = driver.findElement(By.xpath(".//*[@id='setup']/tbody/tr["+i+"]/td["+j+"]")).getText();
					text = text.trim().toString();
					dataSet.add(text);
				}
			}
			itemNamesAndData.put(key, dataSet);
		}
		return itemNamesAndData ;
	}
	
	
	
	public String getStatusBasedOnPRnumber(String PRnumber,String RequestType){
		String status = null ;
		if(RequestType.equals("My Request")){
			List<WebElement> prNums = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[1]"));
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[3]"));
			
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
			List<WebElement> prNums = driver.findElements(By.xpath("//*[@id='theCarousel1']/div/div/a/div/div/div[2]/p[1]"));
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel1']/div/div/a/div/div/div[2]/p[3]"));
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

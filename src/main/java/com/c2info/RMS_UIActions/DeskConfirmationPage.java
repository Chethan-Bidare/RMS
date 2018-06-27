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

public class DeskConfirmationPage extends TestBase{

	public static final Logger log = Logger.getLogger(DeskConfirmationPage.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
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
	@FindBy(id="item_description1-1")
	WebElement description ;
	
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
	
	
	
	
	
	public DeskConfirmationPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void clickOnDeskApprovalRequestBasedOnPRnumber(String RequestType,String PRnumber){
		
		if(RequestType.equalsIgnoreCase("Single Request")){
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
					PRnum = PRnum.replaceAll("Project Id:", "");
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
		Thread.sleep(10000);
		Supplierbox.click();
		SupplierEntry.sendKeys(suppName);
		Thread.sleep(2000);
		SelectFromAutoSuggestionSearch(suppName);
		wait.until(ExpectedConditions.elementToBeClickable(LoadDataBtn));
		LoadDataBtn.click();
		Thread.sleep(2000);
	}
	
	public void clickOnSubmitBtn() throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
		SubmitBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(Updatebtn));
		Updatebtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(OKbtn));
		OKbtn.click();
	}
	
	
	public HashMap<String,ArrayList<String>> getItemNameAndData(){
		HashMap<String, ArrayList<String>> itemNameAndData = new HashMap<String, ArrayList<String>>();
		List<WebElement> itemNameData = driver.findElements(By.xpath("//table/tbody/tr"));
		for(int i=1; i<=itemNameData.size(); i++){
			String itemName = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td[2]")).getText();
			ArrayList<String> dataSet = new ArrayList<String>();
			for(int j=3; j<=5; j++){
				String text = driver.findElement(By.xpath("//table/tbody/tr["+i+"]/td["+j+"]")).getText();
				text = text.trim().toString();
				dataSet.add(text);
			}
			itemNameAndData.put(itemName, dataSet);
		}
		
		return itemNameAndData ;
	}
	
	
	public HashMap<String,HashMap<String,String>> getItemDetailsAfterLoading(){
		HashMap<String, HashMap<String,String>> itemDetails = new HashMap<String, HashMap<String,String>>();
		List<WebElement> rows = driver.findElements(By.xpath(".//*[@id='table1']/tbody/tr"));
		
		for(int i=1; i<rows.size(); i++){
			String itemName = driver.findElement(By.xpath(".//*[@id='table1']/tbody/tr["+i+"]/td[1]/input")).getAttribute("value");
			itemName = itemName.trim().toString();
			
			List<WebElement> cols = driver.findElements(By.xpath(".//*[@id='table1']/tbody/tr["+i+"]/td"));
			HashMap<String, String> dataSets = new HashMap<String, String>();
			
			for(int j=2; j<=cols.size()-2; j++){
				if(j==4){
					System.out.println("td[4] skipped");
				}
				else{
					String header = driver.findElement(By.xpath(".//*[@id='table1']/thead/tr[1]/th["+j+"]")).getText();
					header = header.trim().toString();
					
					String data = driver.findElement(By.xpath(".//*[@id='table1']/tbody/tr["+i+"]/td["+j+"]/input")).getAttribute("value");
					data = data.trim().toString();
					
					dataSets.put(header, data);
				}
			}
			itemDetails.put(itemName, dataSets);
		}
		return itemDetails;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

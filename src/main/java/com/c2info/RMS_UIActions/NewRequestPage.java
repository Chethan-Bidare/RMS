package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.c2info.RMS_TestBase.TestBase;

public class NewRequestPage extends TestBase{
	
	public static final Logger log = Logger.getLogger(NewRequestPage.class.getName());
	
	@FindBy(id="request_type")
	WebElement RequestType ;
	
	@FindBy(id="ref_branch")
	WebElement ReferenceBranch ;
	
	@FindBy(id="category-id")
	WebElement Category ;
	
	
	@FindBy(id="product_name")
	WebElement ProductSearch ;
	
	@FindBy(id="btn-submit")
	WebElement SubmitBtn ;
	
	@FindBy(id="btn-draft")
	WebElement DraftBtn ;
	
	@FindBy(id="btn-cancel")
	WebElement ClearAllBtn ;
	
	@FindBy(id="number")
	WebElement QtyEdit ;
	
	@FindBy(id="plus")
	WebElement QtyIncrease ;
	
	@FindBy(id="minus")
	WebElement QtyDecrease ;
	
	@FindBy(id="product-details")
	WebElement ProductDetails ;
	
	@FindBy(id="product-add-btn")
	WebElement AddBtn ;
	
	@FindBy(id="product-cancel-btn")
	WebElement CancelBtn ;
	
	@FindBy(className="close")
	WebElement CloseAddListPopUp ;
	
	@FindBy(xpath=".//h5[@class='product-name']")
	WebElement ProductName;
	
	@FindBy(xpath="//*[@class='toggle btn btn-primary']")
	WebElement RequestFor ;
	
	@FindBy(id="btn_ok")
	WebElement SubmitOKBtn ;
	
	@FindBy(id="btn_success")
	WebElement SubmitSuccessOkBtn ;
	
	
	
	@FindBy(id="cost_center")
	WebElement CostCenter ;
	/*
	@FindBy(id="number")
	WebElement QtyEdit ;
	
	@FindBy(id="number")
	WebElement QtyEdit ;
	
	@FindBy(id="number")
	WebElement QtyEdit ;
	*/ 
	 
	
	public NewRequestPage(){
		PageFactory.initElements(driver, this);
	}
	
	public void selectRequestType(String requestType){
		Select select = new Select(this.RequestType);
		select.selectByVisibleText(requestType);
		log.info(requestType +"selected from the Request Type dropdown");
	}
	
	public void selectRequestFor(String requestFor){
		if(requestFor.equalsIgnoreCase("others")){
			RequestFor.click();
		}
		else{
			log.info("By default Request is For Self");
		}
	}

	public void selectRefBranch(String refBr){
		Select select = new Select(ReferenceBranch);
		select.selectByValue(refBr);
		log.info(refBr +"selected from the Ref Branch dropdown");
	}
	
	public void selectCostCenter(String costCenterName){
		Select select = new Select(CostCenter);
		select.selectByVisibleText(costCenterName);
	}
	
	public void selectCategory(String category){
		Select select = new Select(Category);
		select.selectByVisibleText(category);
		log.info(category +"selected from the Category dropdown");
	}
	
	public void selectProductFromAutoSuggestionBox(String productName) throws InterruptedException{
		ProductSearch.sendKeys(productName);
		Thread.sleep(3000);
		List<WebElement> searchList = driver.findElements(By.tagName("li"));
		for(WebElement we : searchList){
			String itemName = we.getText();
			System.out.println(itemName);
			if(itemName.contains(productName)){
				we.click();
				log.info("Clicked on the searched Product Name : "+productName);
			}
			else{
				log.info(productName+" not found in Search List");
			}
		}
		
	}
	
	public void enterQty(String Qty){
		QtyEdit.clear();
		QtyEdit.sendKeys(Qty);
	}
	
	public void increaseQty(int qty){
		for(int i=1;i<=qty;i++){
			QtyIncrease.click();
		}
	}
	
	public void decreaseQty(int qty){
		for(int i=1;i<=qty;i++){
			QtyDecrease.click();
		}
	}
	
	public void clickOnAddButton(){
		AddBtn.click();
	}
	
	
	public ArrayList<String> getItemNamesAddedToCart(){
		ArrayList<String> itemNames = new ArrayList<String>();
		List<WebElement> itemList = driver.findElements(By.id("item-name"));
		for(WebElement we : itemList){
			String item = we.getText().trim().toString();
			itemNames.add(item);
		}
		return itemNames;
	}
	
	/*public ArrayList<Integer> getQtyForItemsAddedToCart(){
		
	}*/
	
	public void clickOnSubmitButton() throws InterruptedException{
		SubmitBtn.click();
		Thread.sleep(2000);
		SubmitOKBtn.click();
		Thread.sleep(3000);
		SubmitSuccessOkBtn.click();
		Thread.sleep(2000);
		log.info("Clicked on Submit button");
	}
	
	public void clickOnDraftButton(){
		DraftBtn.click();
		log.info("Clicked on Save As Draft button");
	}
	
	public void clickOnClearAllButton(){
		ClearAllBtn.click();
		log.info("Clicked on Clear All button");
	}
	
	
}

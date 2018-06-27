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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.c2info.RMS_TestBase.TestBase;

public class NewRequestPage extends TestBase{
	
	public static final Logger log = Logger.getLogger(NewRequestPage.class.getName());
	WebDriverWait wait = new WebDriverWait(driver, 60);
	
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
	
	@FindBy(xpath=".//*[@id='myModal1']/div/div/div[2]/div[1]/div[2]/div/h5[2]/span/img[2]")
	WebElement IncreaseQtyOnUpdate ;
	
	@FindBy(xpath=".//*[@id='myModal1']/div/div/div[2]/div[1]/div[2]/div/h5[2]/span/img[1]")
	WebElement DecreaseQtyOnUpdate ;
	
	@FindBy(id="product-add-btn1")
	WebElement AddBtnOnUpdate ;
	
	
	@FindBy(id="amount")
	WebElement Price ;
	
	
	@FindBy(id="amount1")
	WebElement PriceOnUpdate ;
	/*
	@FindBy(id="amount1")
	WebElement PriceOnUpdate ;
	
	@FindBy(id="amount1")
	WebElement PriceOnUpdate ;
	
	@FindBy(id="amount1")
	WebElement PriceOnUpdate ;
	
	@FindBy(id="amount1")
	WebElement PriceOnUpdate ;
	
	@FindBy(id="amount1")
	WebElement PriceOnUpdate ;
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
		log.info(refBr +" selected from the Ref Branch dropdown");
	}
	
	public void selectCostCenter(String costCenterName){
		Select select = new Select(CostCenter);
		select.selectByVisibleText(costCenterName);
		log.info(costCenterName +" selected from the Cost Center dropdown");
	}
	
	public void selectCategory(String category){
		Select select = new Select(Category);
		select.selectByVisibleText(category);
		log.info(category +" selected from the Category dropdown");
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
		wait.until(ExpectedConditions.elementToBeClickable(QtyEdit));
		QtyEdit.clear();
		QtyEdit.sendKeys(Qty);
		log.info("Qty entered in the Qty field : "+Qty);
	}
	
	public void enterAmount(String price){
		wait.until(ExpectedConditions.elementToBeClickable(Price));
		Price.clear();
		Price.sendKeys(price);
		log.info("Price entered in the Price field : "+price);
	}
	
	public void enterAmountOnUpdate(String price){
		PriceOnUpdate.clear();
		PriceOnUpdate.sendKeys(price);
		log.info("Price entered in the Price field : "+price);
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
	
	public void increaseQtyOnUpdate(int qty){
		for(int i=1;i<=qty;i++){
			IncreaseQtyOnUpdate.click();
		}
	}
	
	public void decreaseQtyOnUpdate(int qty){
		for(int i=1;i<=qty;i++){
			DecreaseQtyOnUpdate.click();
		}
	}
	
	public void clickOnAddButton(){
		wait.until(ExpectedConditions.elementToBeClickable(AddBtn));
		AddBtn.click();
		log.info("Clicked on Add button");
	}
	
	public void clickOnAddButtonOnUpdate(){
		wait.until(ExpectedConditions.elementToBeClickable(AddBtnOnUpdate));
		AddBtnOnUpdate.click();
		log.info("Clicked on Add button");
	}
	
	
	public ArrayList<String> getItemNamesAddedToCart(){
		ArrayList<String> itemNames = new ArrayList<String>();
		List<WebElement> itemList = driver.findElements(By.id("item-name"));
		for(WebElement we : itemList){
			String item = we.getText().trim().toString();
			itemNames.add(item);
		}
		log.info("Item Names are stored in an Array list");
		return itemNames;
	}
	
	/*public ArrayList<Integer> getQtyForItemsAddedToCart(){
		
	}*/
	
	public void clickOnSubmitButton() throws InterruptedException{
		wait.until(ExpectedConditions.elementToBeClickable(SubmitBtn));
		SubmitBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(SubmitOKBtn));
		SubmitOKBtn.click();
		wait.until(ExpectedConditions.elementToBeClickable(SubmitSuccessOkBtn));
		SubmitSuccessOkBtn.click();
		Thread.sleep(5000);
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
	
	public HashMap<String, Integer> getItemNamesWithQtyInMyRequestPage(){
		HashMap<String, Integer> itemNamesWithQty = new HashMap<String,Integer>();
		List<WebElement> noOfItems = driver.findElements(By.xpath(".//*[@id='items-value']/div"));
		
		for(int i=1;i<=noOfItems.size();i++){
			String tempItem = driver.findElement(By.xpath(".//*[@id='items-value']/div["+i+"]/div[2]")).getText();
			tempItem = tempItem.trim().toString();
			
			String tempQty = driver.findElement(By.xpath(".//*[@id='items-value']/div["+i+"]/div[3]")).getText();
			tempQty = tempQty.trim().toString();
			int tempqty = Integer.parseInt(tempQty);
			itemNamesWithQty.put(tempItem, tempqty);
		}
		log.info("Item Names and Qty are stored in a hash map");
		return itemNamesWithQty ;
	}
	
	public HashMap<String, Double> getItemNamesWithPriceInMyRequestPage(){
		HashMap<String, Double> itemNamesWithPrice = new HashMap<String,Double>();
		List<WebElement> noOfItems = driver.findElements(By.xpath(".//*[@id='items-value']/div"));
		
		for(int i=1;i<=noOfItems.size();i++){
			String tempItem = driver.findElement(By.xpath(".//*[@id='items-value']/div["+i+"]/div[2]")).getText();
			tempItem = tempItem.trim().toString();
			
			String tempPrice = driver.findElement(By.xpath(".//*[@id='items-value']/div["+i+"]/div[4]/p")).getText();
			tempPrice = tempPrice.trim().toString();
			double tempprice = Double.parseDouble(tempPrice);
			itemNamesWithPrice.put(tempItem, tempprice);
		}
		log.info("Item Names and Qty are stored in a hash map");
		return itemNamesWithPrice ;
	}
	
	
	public void increaseQtyBy2ForAllItemsAfterAdding() throws InterruptedException{
		List<WebElement> noOfItems = driver.findElements(By.xpath(".//*[@id='items-value']/div"));
		log.info("Fetching the no of items added");
		for(int i=1;i<=noOfItems.size();i++){
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='items-value']/div["+i+"]/div[2]")).click();
			Thread.sleep(2000);
			increaseQtyOnUpdate(2);
			log.info("Increasing the Qty +"+2+" for the item number : "+i);
			clickOnAddButtonOnUpdate();
		}
	}
	
	public void decreaseQtyBy2ForAllItemsAfterAdding() throws InterruptedException{
		List<WebElement> noOfItems = driver.findElements(By.xpath(".//*[@id='items-value']/div"));
		log.info("Fetching the no of items added");
		for(int i=1;i<=noOfItems.size();i++){
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='items-value']/div["+i+"]/div[2]")).click();
			Thread.sleep(2000);
			decreaseQtyOnUpdate(2);
			log.info("Decreasing the Qty +"+2+" for the item number : "+i);
			clickOnAddButtonOnUpdate();
		}
	}
	
	public void updateAmountField() throws InterruptedException{
		List<WebElement> noOfItems = driver.findElements(By.xpath(".//*[@id='items-value']/div"));
		log.info("Fetching the no of items added");
		for(int i=1;i<=noOfItems.size();i++){
			Thread.sleep(2000);
			driver.findElement(By.xpath(".//*[@id='items-value']/div["+i+"]/div[2]")).click();
			Thread.sleep(2000);
			enterAmountOnUpdate("10000");
			log.info("Amount updated for the item number : "+i);
			clickOnAddButtonOnUpdate();
		}
	}
	
	
	
	
}

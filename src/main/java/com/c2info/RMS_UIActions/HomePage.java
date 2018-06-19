package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.c2info.RMS_TestBase.TestBase;

public class HomePage extends TestBase{
	
	public static final Logger log = Logger.getLogger(HomePage.class.getName());
	
	WebDriverWait wait = new WebDriverWait(driver, 45);
	
	@FindBy(id="user_name")
	WebElement UserNumber ;
	
	@FindBy(id="sendotp_btn")
	WebElement SendOTPBtn ;
	
	@FindBy(id="password")
	WebElement OTP ;
	
	@FindBy(id="verify_btn")
	WebElement VerifyBtn ;
	
	@FindBy(id="resend_btn")
	WebElement ResendBtn ;
	
	@FindBy(id=".//*[@class='glyphicon glyphicon-log-out']")
	WebElement logout ;
	
	@FindBy(id="modal_ok")
	WebElement logoutOKBtn ;
	
	@FindBy(id="sort")
	WebElement Sort ;
	
	
	@FindBy(id="grid_icon")
	WebElement grid ;
	
	@FindBy(id="list_icon")
	WebElement list ;
	
	@FindBy(id="request_search")
	WebElement GlobalSearch ;
	
	@FindBy(id="generalbtn")
	WebElement NewRequestBtn ;
	/*
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;
	
	@FindBy(id="")
	WebElement abc ;*/
	
	
	public HomePage(){
		PageFactory.initElements(driver, this);
	}

	public void doLogin(String UserNum,String otp){
		UserNumber.sendKeys(UserNum);
		log.info("User Mobile Number entered "+UserNum);
		SendOTPBtn.click();
		log.info("Clicked on Send OTP button");
		OTP.sendKeys(otp);
		log.info("OTP entered"+otp);
		VerifyBtn.click();
		log.info("Clicked on Verify button");
	}
	
	public void doLogOut() throws InterruptedException{
		List<WebElement> AutoSuggestionItemList = driver.findElements(By.tagName("li"));
		for(WebElement we : AutoSuggestionItemList){
			if(we.getText().contains("LOGOUT")){
				we.click();
				Thread.sleep(5000);
				logoutOKBtn.click();
				break ;
			}
		}
		log.info("Clicked on LogOut button");
	}
	
	public void waitForHomePagetoLoad(){
		wait.until(ExpectedConditions.titleContains("My Requests"));
	}
	
    public void ClickOnMenuOption(String MenuName){

			List<WebElement> AutoSuggestionItemList = driver.findElements(By.tagName("li"));
			for(WebElement we : AutoSuggestionItemList){
				if(we.getText().contains(MenuName)){
					we.click();
					log.info("Clicked on the Menu option : "+MenuName);
				}
			}
		
	  }
     
    public void clickOnGlobalSearch(String SearchText){
    	GlobalSearch.clear();
    	GlobalSearch.sendKeys(SearchText);
    	log.info("Search data entered in global Search");
    }

	public void selectAnOptionFromSubMenu(String SubMenuName){
		try {
			List<WebElement> SubMenu = driver.findElements(By.xpath(".//a[@class='submenu']"));
			for(WebElement we: SubMenu){
				if(we.getText().contains(SubMenuName)){
					we.click();
					log.info("Clicked on the Sub Menu option : "+SubMenuName);
				}
			}
		} catch (StaleElementReferenceException e) {
			e.printStackTrace();
		}
	}

	public void selectVisibleTextFromSortingDropdown(String text){
		Select select = new Select(Sort);
		select.selectByVisibleText(text);
		log.info("Clicked on Sorting Type : "+text);
	}
	
	public void clickOnGridORListView(String ViewType){
		if(ViewType.equalsIgnoreCase("Grid")){
			grid.click();
			log.info("Clicked on "+ViewType+" view");
		}
		else if(ViewType.equalsIgnoreCase("List")){
			list.click();
			log.info("Clicked on "+ViewType+" view");
		}
	}
	
	public void clickOnPendingNotifications(String linkText){
		List<WebElement> list = driver.findElements(By.xpath("//a/h4"));
		for(WebElement we : list){
			String tempText = we.getText();
			tempText = tempText.trim().toString();
			
			if(tempText.contains(linkText)){
				we.click();
				break ;
			}		
		}
	}
	
	public ArrayList<Integer> getPRNo(){
		List<WebElement> text =  driver.findElements(By.xpath(".//*[@class='head-content gridContent']/p"));
		ArrayList<Integer> PrNum = new ArrayList<Integer>();
		for(WebElement we : text){
			if(we.getText().contains("PR No :")){
				String PRNO = we.getText();
				PRNO = PRNO.substring(7).trim();
				int prNo = Integer.parseInt(PRNO);
				PrNum.add(prNo);
				
			}
		}
		log.info("List Of PR Numbers are fetched"+PrNum);
	    return PrNum ;
	}
	
	public ArrayList<Integer> getPRnoForRequiredSectionInGridView(String RequestType){
		
		if(RequestType.equalsIgnoreCase("My Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[1]"));
			ArrayList<Integer> PrNum = new ArrayList<Integer>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("PR No : ","").trim();
				int Pr = Integer.parseInt(PRnum);
				PrNum.add(Pr);
				}
			
			}
			log.info("Fetching the list of PR Numbers for "+RequestType+" in Grid view : "+PrNum);
			return PrNum ;
		}
		else if(RequestType.equalsIgnoreCase("Projectwise Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel1']/div/div/a/div/div/div[2]/p[1]"));
			ArrayList<Integer> PrNum = new ArrayList<Integer>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("Group Id:", "");
				PRnum = PRnum.trim();
				int Pr = Integer.parseInt(PRnum);
				PrNum.add(Pr);
				}
			}
			log.info("Fetching the list of PR Numbers for "+RequestType+" in Grid view : "+PrNum);
			return PrNum ;
		}
		else if(RequestType.equalsIgnoreCase("Approval List")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[1]"));
			ArrayList<Integer> PrNum = new ArrayList<Integer>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("PR No : ","").trim();
				int Pr = Integer.parseInt(PRnum);
				PrNum.add(Pr);
				}
			}
			log.info("Fetching the list of PR Numbers for "+RequestType+" in Grid view : "+PrNum);
			return PrNum ;
		}
		return null ;
	}
	
	
	public ArrayList<Integer> getPrNoForRequiredSectionInListView(String RequestType){
		
		if(RequestType.equalsIgnoreCase("My Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='collapse1']/div/div/a/div/div[2]/div/p[1]"));
			ArrayList<Integer> PrNum = new ArrayList<Integer>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("PR No : ","").trim();
				int Pr = Integer.parseInt(PRnum);
				PrNum.add(Pr);
				}
			}
			log.info("Fetching the list of PR Numbers for "+RequestType+" in List view :" +PrNum);
			return PrNum ;
		}
		else if(RequestType.equalsIgnoreCase("Projectwise Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='collapse2']/div/div/a/div/div[2]/div/p[1]"));
			ArrayList<Integer> PrNum = new ArrayList<Integer>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("Group Id:", "");
				PRnum = PRnum.trim();
				int Pr = Integer.parseInt(PRnum);
				PrNum.add(Pr);
				}
			}
			log.info("Fetching the list of PR Numbers for "+RequestType+" in List view :"+PrNum);
			return PrNum ;
		}
		else if(RequestType.equalsIgnoreCase("Approval List")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='collapse3']/div/div/a/div/div[2]/div/p[1]"));
			ArrayList<Integer> PrNum = new ArrayList<Integer>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String PRnum = we.getText();
				PRnum = PRnum.replaceAll("PR No : ","").trim();
				int Pr = Integer.parseInt(PRnum);
				PrNum.add(Pr);
				}
			}
			log.info("Fetching the list of PR Numbers for "+RequestType+" in List view : "+PrNum);
			return PrNum ;
		}
		return null;
	}

	
	public ArrayList<String> getItemNamesForRequiredSectionInGridView(String RequestType){
		
		if(RequestType.equalsIgnoreCase("My Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[2]"));
			ArrayList<String> ItemNames = new ArrayList<String>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String ItemName = we.getText();
				ItemName = ItemName.replaceAll("Item : ","").trim();
				ItemNames.add(ItemName);
				}
			
			}
			log.info("Fetching the list of Item Names for "+RequestType+" in grid view : "+ItemNames);
			return ItemNames ;
		}
		else if(RequestType.equalsIgnoreCase("Projectwise Request")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel1']/div/div/a/div/div/div[2]/p[2]"));
			ArrayList<String> ItemNames = new ArrayList<String>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String ItemName = we.getText();
				ItemName = ItemName.replaceAll("Project Name : ","").trim();
				ItemNames.add(ItemName);
				}
			
			}
			log.info("Fetching the list of Item Names for "+RequestType+" in grid view : "+ItemNames);
			return ItemNames ;
		}
		else if(RequestType.equalsIgnoreCase("Approval List")){
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel2']/div/div/a/div/div/div[2]/p[2]"));
			ArrayList<String> ItemNames = new ArrayList<String>();
			for(WebElement we : row){
				if(we.isDisplayed()==true){
				String ItemName = we.getText();
				ItemName = ItemName.replaceAll("Item : ","").trim();
				ItemNames.add(ItemName);
				}
			
			}
			log.info("Fetching the list of Item Names for "+RequestType+" in grid view"+ItemNames);
			return ItemNames ;
		}
		return null ;
	}
	
	
	public ArrayList<String> getItemNamesForRequiredSectionInListView(String RequestType){
	
	if(RequestType.equalsIgnoreCase("My Request")){
		List<WebElement> row = driver.findElements(By.xpath("//*[@id='collapse1']/div/div/a/div/div[2]/div/p[2]"));
		ArrayList<String> ItemNames = new ArrayList<String>();
		for(WebElement we : row){
			if(we.isDisplayed()==true){
			String ItemName = we.getText();
			ItemName = ItemName.replaceAll("Item : ","").trim();
			ItemNames.add(ItemName);
			}
		
		}
		log.info("Fetching the list of Item Names for "+RequestType+" in List view"+ItemNames);
		return ItemNames ;
	}
	else if(RequestType.equalsIgnoreCase("Projectwise Request")){
		List<WebElement> row = driver.findElements(By.xpath("//*[@id='collapse2']/div/div/a/div/div[2]/div/p[2]"));
		ArrayList<String> ItemNames = new ArrayList<String>();
		for(WebElement we : row){
			if(we.isDisplayed()==true){
			String ItemName = we.getText();
			ItemName = ItemName.replaceAll("Project Name : ","").trim();
			ItemNames.add(ItemName);
			}
		
		}
		log.info("Fetching the list of Item Names for "+RequestType+" in List view"+ItemNames);
		return ItemNames ;
	}
	else if(RequestType.equalsIgnoreCase("Approval List")){
		List<WebElement> row = driver.findElements(By.xpath("//*[@id='collapse3']/div/div/a/div/div[2]/div/p[2]"));
		ArrayList<String> ItemNames = new ArrayList<String>();
		for(WebElement we : row){
			if(we.isDisplayed()==true){
			String ItemName = we.getText();
			ItemName = ItemName.replaceAll("Item : ","").trim();
			ItemNames.add(ItemName);
			}
		
		}
		log.info("Fetching the list of Item Names for "+RequestType+" in List view"+ItemNames);
		return ItemNames ;
	}
	return null ;
}
	
	
	public ArrayList<String> getEmployeeNamesInListView(){
		List<WebElement> text =  driver.findElements(By.xpath(".//*[@id='marbtm']/a/div/div/div[1]/p[3]/span"));
		ArrayList<String> employeeNames = new ArrayList<String>();
		for(WebElement we : text){
		if(we.getText().contains("Chethan")){
			String employeeName = we.getText();
			employeeName = employeeName.trim();
			employeeNames.add(employeeName);
			
		}
	}
		log.info("Fetching the list of Employee Names in List view : "+employeeNames);
    return employeeNames ;
}	
	
	public ArrayList<String> getEmployeeNamesInGridView(){
		List<WebElement> text =  driver.findElements(By.xpath("//*/div/div/a/div/div/div[2]/p[3]/span"));
		ArrayList<String> employeeNames = new ArrayList<String>();
		for(WebElement we : text){
			if(we.isDisplayed()==true){
		if(we.getText().contains("Chethan")){
			String employeeName = we.getText();
			employeeName = employeeName.trim();
			employeeNames.add(employeeName);
		}
		}
	}
		log.info("Fetching the list of Employee Names in Grid view : "+employeeNames);
    return employeeNames ;
}	
	
	
/*	public ArrayList<String> getStatusOfPRInGridView(String statusName){
		
	}
*/	
	public boolean gridViewIsDisplayed(){
		WebElement carousel = driver.findElement(By.id("theCarousel"));
		if(carousel.isDisplayed()==true){
			log.info("Returned true when checked for Grid view");
			return true ;
		}
		else{
			log.info("Returned false when checked for Grid view");
			return false ;
		}
	}
	
	public boolean listViewIsDisplayed(){
		WebElement list = driver.findElement(By.id("collapse1"));
		if(list.isDisplayed()==true){
			log.info("Returned true when checked for List view");
			return true ;
		}
		else{
			log.info("Returned False when checked for List view");
			return false ;
		}
	}
	
	
	public void clickOnNewRequestButton(){
		NewRequestBtn.click();
		log.info("Clicked on New Request button");
	}
	
	
	
	
	
	public String getStatusBasedOnPRnumber(String PRnumber,String RequestType){
		String status = null ;
		if(RequestType.equals("My Request")){
			List<WebElement> prNums = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[1]"));
			List<WebElement> row = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[3]"));
			List<WebElement> row1 = driver.findElements(By.xpath("//*[@id='theCarousel']/div/div/a/div/div/div[2]/p[4]"));
			HashMap<String, String> prNumWithStatus = new HashMap<String, String>();
			for(int i=0,j=0,k=0; i<prNums.size() && j<row.size() && k<row1.size(); i++,j++,k++){
				String prNum = prNums.get(i).getText();
				prNum = prNum.replaceAll("PR No : ","").trim();
				if(row.get(j).getText().contains("Status")){
				String Status = row.get(j).getText();
				Status = Status.replaceAll("Status : ","").trim();
				prNumWithStatus.put(prNum, Status);
				}
				else if(row1.get(k).getText().contains("Status")){
					String Status = row1.get(k).getText();
					Status = Status.replaceAll("Status : ","").trim();
					prNumWithStatus.put(prNum, Status);
				}
				
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
			List<WebElement> row1 = driver.findElements(By.xpath("//*[@id='theCarousel1']/div/div/a/div/div/div[2]/p[4]"));
			HashMap<String, String> prNumWithStatus = new HashMap<String, String>();
			for(int i=0,j=0,k=0; i<prNums.size() && j<row.size() && k<row1.size(); i++,j++,k++){
				String prNum = prNums.get(i).getText();
				prNum = prNum.replaceAll("PR No : ","").trim();
				if(row.get(j).getText().contains("Status")){
				String Status = row.get(j).getText();
				Status = Status.replaceAll("Status : ","").trim();
				prNumWithStatus.put(prNum, Status);
				}
				else if(row1.get(k).getText().contains("Status")){
					String Status = row1.get(k).getText();
					Status = Status.replaceAll("Status : ","").trim();
					prNumWithStatus.put(prNum, Status);
				}
				
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
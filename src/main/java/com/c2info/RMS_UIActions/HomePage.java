package com.c2info.RMS_UIActions;

import java.util.ArrayList;
import java.util.Collections;
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
	/*
	@FindBy(id="")
	WebElement abc ;
	
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
		SendOTPBtn.click();
		OTP.sendKeys(otp);
		VerifyBtn.click();
	}
	
	public void doLogOut(){
		SelectFromAutoSuggestionSearch(": CSQUARE");
		logoutOKBtn.click();
	}
	
	public void waitForHomePagetoLoad(){
		wait.until(ExpectedConditions.titleContains("My Requests"));
	}
	
    public void ClickOnMenuOption(String MenuName){
		
		//WebElement AutoSuggestion = driver.findElement(By.id("ui-id-1"));
		//if(AutoSuggestion.isDisplayed()==true){
			List<WebElement> AutoSuggestionItemList = driver.findElements(By.tagName("li"));
			for(WebElement we : AutoSuggestionItemList){
				if(we.getText().contains(MenuName)){
					we.click();
				}
			}
		
	  }
     
    public void clickOnGlobalSearch(String SearchText){
    	GlobalSearch.clear();
    	GlobalSearch.sendKeys(SearchText);
    }

	public void selectAnOptionFromSubMenu(String SubMenuName){
		List<WebElement> SubMenu = driver.findElements(By.xpath(".//a[@class='submenu']"));
		for(WebElement we: SubMenu){
			if(we.getText().contains(SubMenuName)){
				we.click();
			}
		}
	}

	public void selectVisibleTextFromSortingDropdown(String text){
		Select select = new Select(Sort);
		select.selectByVisibleText(text);
	}
	
	public void clickOnGridORListView(String ViewType){
		if(ViewType.equalsIgnoreCase("Grid")){
			grid.click();
		}
		else if(ViewType.equalsIgnoreCase("List")){
			list.click();
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
		System.out.println(PrNum);
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
	
    return employeeNames ;
}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
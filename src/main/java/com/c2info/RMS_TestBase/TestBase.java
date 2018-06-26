package com.c2info.RMS_TestBase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.c2info.RMS_ExcelReader.ExcelReader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestBase {

	
	public static final Logger log = Logger.getLogger(TestBase.class.getName());
	public static WebDriver driver ;

	public static ExtentReports extent ;
	public static ExtentTest Test ;
	public static ITestResult Result ;
	public Properties OR = new Properties();
	public Properties APP = new Properties();
	
	
	public void LoadfromORproperties() throws IOException{
		File path = new File(System.getProperty("user.dir")+"//src//main//java//com//c2info//RMS_Config//OR.properties");
		FileInputStream fis = new FileInputStream(path);
		OR.load(fis);
	}
	
	public void LoadfromAPPproperties() throws IOException{
		File path = new File(System.getProperty("user.dir")+"//src//main//java//com//c2info//RMS_Config//APP.properties");
		FileInputStream fis = new FileInputStream(path);
		APP.load(fis);
	}
	
	static{
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("DD_MM_YYYY_HH_MM_SS");
		extent = new ExtentReports(System.getProperty("user.dir")+"//src//main//java//com//c2info//RMS_Reports//"+formatter.format(calendar.getTime())+".html",false);
	}
	
	public String[][] ReadExcel(String SheetName,String ExcelName){
		String path = System.getProperty("user.dir")+"//src//main//java//com//c2info//RMS_Data//TestData.xlsx";
		ExcelReader excel = new ExcelReader(path);
		String[][] TestData = excel.getdatafromSheet(SheetName, ExcelName);
		return TestData;
		
	}
	
	public void init() throws IOException{
		LoadfromORproperties();
		LoadfromAPPproperties();
		selectBrowser(OR.getProperty("BrowserName"));
		waitForElementToLoad();
		clearHistory();
		getBaseUrl(OR.getProperty("URL"));
		PropertyConfigurator.configure(System.getProperty("user.dir")+"//log4j.properties");
		
		
	}
	
	public void selectBrowser(String BrowserName){
		if(BrowserName.equalsIgnoreCase("firefox")){
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"//Drivers//geckodriver.exe");
			driver = new FirefoxDriver();
			waitForElementToLoad();
		
		}
		else if(BrowserName.equalsIgnoreCase("chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"//Drivers//chromedriver.exe");
			driver = new ChromeDriver();
			waitForElementToLoad();
			
		}
	}
	
	public void getBaseUrl(String BaseUrl){
		driver.get(BaseUrl);
		try {
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void waitForElementToLoad(){
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
	}
	
	public void waitforPageToLoad(){
		driver.manage().timeouts().pageLoadTimeout(60,TimeUnit.SECONDS);
	}
	
	public void clearHistory(){
		driver.manage().deleteAllCookies();
	}
	
	public void closeBrowser(){
		driver.close();
	}
	
	public void refreshPage(){
		driver.navigate().refresh();
	}
	
	public String getPageTitle(){
		String pageTitle = driver.getTitle();
		return pageTitle ;
	}
	
	public String getScreenshot(String methodName){
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formatter = new SimpleDateFormat("DD_MM_YYYY_HH_MM_SS");
		
		try {
			File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			String ReportDirectory = System.getProperty("user.dir")+"//src//main//java//com//c2info//RMS_Screenshots//";
			String destination = ReportDirectory+"_"+methodName+"_"+formatter.format(calendar.getTime())+".png";
			File destFile = new File(destination);
			FileUtils.copyFile(srcFile, destFile);
			return destination ;
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
	}
	
	public void SelectFromAutoSuggestionSearch(String Name){
		
		//WebElement AutoSuggestion = driver.findElement(By.id("ui-id-1"));
		//if(AutoSuggestion.isDisplayed()==true){
			List<WebElement> AutoSuggestionItemList = driver.findElements(By.tagName("li"));
			for(WebElement we : AutoSuggestionItemList){
				if(we.getText().contains(Name)){
					we.click();
				}
			}
		
	}
	
	
	
	public boolean isFileDownloaded(String fileName,String downloadPath){
		boolean flag = false ;
		File file = new File(downloadPath);
		File[] dir = file.listFiles();
		
		for(int i=0; i<dir.length; i++){
			if(dir[i].getName().equalsIgnoreCase(fileName)){
				return flag = true ;
			}
		}
		return flag ;
		
	}
	
	public ArrayList<String> getBrokenLinks(){
		List<WebElement> list = driver.findElements(By.tagName("a"));
		System.out.println("Total Number of Links : "+list.size());
		ArrayList<String> brokenLinks = new ArrayList<String>();
		for(WebElement we : list){
			
			if(we != null){
			String url = we.getAttribute("href");
			if(url != null && ! url.contains("javascript")){
				verifyUrlStatus(url);
			}
			else{
				brokenLinks.add(url);
				System.out.println("Broken Link : "+url);
			}
			}
		}
		return brokenLinks ;
	}
	
	
	public void verifyUrlStatus(String url){
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpGet request = new HttpGet(url);
		
		try {
			HttpResponse response = client.execute(request);
			
			if(response.getStatusLine().getStatusCode()==200){
				System.out.println("Valid Link : "+url);
			}
			else{
				System.out.println("Broken Link : "+url);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public void getResult(ITestResult Result){
		if(Result.getStatus()==ITestResult.SUCCESS){
			Test.log(LogStatus.PASS, Result.getName()+" Test is Passed");
		}
		else if(Result.getStatus()==ITestResult.FAILURE){
			Test.log(LogStatus.FAIL, Result.getName()+" Test is Failed");
			Test.log(LogStatus.FAIL, Test.addScreenCapture(getScreenshot(Thread.currentThread().getStackTrace()[1].getMethodName())));
		}
		else if(Result.getStatus()==ITestResult.SKIP){
			Test.log(LogStatus.SKIP, Result.getName()+" Test is Skipped");
		}
		else if(Result.getStatus()==ITestResult.STARTED){
			Test.log(LogStatus.INFO, Result.getName()+" Test is Started");
		}
	}
	
	
	
	
	@BeforeMethod()
	public void beforeMethod(Method Result){
		Test = extent.startTest(Result.getName());
		Test.log(LogStatus.INFO, Result.getName()+" Test is Started");
	}
	
	@AfterMethod()
	public void afterMethod(ITestResult Result){
		getResult(Result);
	}
	@AfterClass(alwaysRun=true)
	public void endTest(){
		driver.close();
		extent.endTest(Test);
		extent.flush();
	}
}

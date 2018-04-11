package com.c2info.RMS_Listeners;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.c2info.RMS_TestBase.TestBase;

public class Listeners extends TestBase implements ITestListener{

	public void onFinish(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailure(ITestResult arg0) {
		
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
		
		String arg1 = arg0.getName();
		
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String ReportDirectory = System.getProperty("user.dir")+"//src//main//java//com//c2info//RMS_Screenshots//" ;
			File destFile = new File(ReportDirectory+"//FailureScreenshots//"+arg1+ "_"+formater.format(calendar.getTime())+".png") ;
			FileUtils.copyFile(srcFile, destFile);
			
			
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"' ><img src = '"+destFile.getAbsolutePath()+"' hieght='100' width='100' /></a>");
			
			
			
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	public void onTestSkipped(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestStart(ITestResult arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onTestSuccess(ITestResult arg0) {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("YYYY_MM_DD_HH_MM_SS");
		
		String arg1 = arg0.getName();
		
		try {
			File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			
			String ReportDirectory = System.getProperty("user.dir")+"//src//main//java//com//c2info//RMS_Screenshots//" ;
			File destFile = new File(ReportDirectory+"//SuccessScreenshots//"+arg1+ "_"+formater.format(calendar.getTime())+".png") ;
			FileUtils.copyFile(srcFile, destFile);
			
			
			Reporter.log("<a href='"+destFile.getAbsolutePath()+"' ><img src = '"+destFile.getAbsolutePath()+"' hieght='100' width='100' /></a>");
			
			
			
		} catch (WebDriverException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

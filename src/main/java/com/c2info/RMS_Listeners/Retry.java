package com.c2info.RMS_Listeners;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import org.testng.Reporter;

public class Retry implements IRetryAnalyzer{

	
	public static final Logger log = Logger.getLogger(Retry.class.getName());
	int retryCount = 0;
	int maxretryCount = 2;
	
	
	public boolean retry(ITestResult result) {
		if(retryCount<maxretryCount){
			log("Retrying test "+result.getName() + "with status "+ getResultStatusName(result.getStatus())+" for the "+(retryCount+1));
			retryCount++ ;
			return true ;
		}
		return false;
	}
	
	public String getResultStatusName(int status){
		String ResultName = null ;
		
		if(status==1)
			ResultName = "SUCCESS";
			
	    if(status==2)
				ResultName = "FAILURE";
	    
	    if(status==3)
	    	ResultName = "SKIP";
		
		return ResultName;
		
	}
	
	public void log(String data){
		log.info(data);
		Reporter.log(data);
		
	}

}

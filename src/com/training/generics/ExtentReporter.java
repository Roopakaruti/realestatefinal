package com.training.generics;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReporter{
	
	
	
	public void generateReportsP(String actual, String file) {
		
		ExtentReports extent;
		ExtentTest test;
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\"+file+".html", true);
		test = extent.startTest("test1");
		test.log(LogStatus.PASS, "Test Case Passed..." +actual);
		
		extent.endTest(test);       
		extent.flush();
		extent.close();
	} 
	
	
public void generateReportsF(String actual, String file) {
		
		ExtentReports extent;
		ExtentTest test;
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\"+file+".html", true);
		test = extent.startTest("test1");
		
		test.log(LogStatus.FAIL, "Test Case Failed..." +actual);
		extent.endTest(test);       
		extent.flush();
		extent.close();
	} 
	

}

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.training.dataproviders.RETC064DataProvider;
import com.training.generics.ScreenShot;
import com.training.pom.LoginTest_POM;
import com.training.pom.RETC_032_POM;

import com.training.pom.RETC_064_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_064Test {

	
	
	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RETC_064_POM retc064POM;
	private static Properties properties;
	private ScreenShot screenShot;
	
	private static ExtentReports extent;
	private ExtentTest logger;

	
//Log4j code
	Logger log= Logger.getLogger(RETC_064_POM.class);
	

	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RTC_064_Extentreport.html");
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginTest_POM(driver); 
		retc064POM=new RETC_064_POM(driver);
		baseUrl = properties.getProperty("baseURL1");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	
	

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	
		
	@Test(dataProvider = "excel-inputs", dataProviderClass = RETC064DataProvider.class)
	
	
	public void sendEnquiryTest(String yourName,String email,String subject,String message) throws InterruptedException {
	
		logger = extent.startTest("Send Enquiry for Donec quis property");
		
		logger.log(LogStatus.INFO, "Click on Login/Register Link from Home page");
		
		loginPOM.clickLoginLink();
		
		logger.log(LogStatus.PASS, "Login screen is opened");
		
		logger.log(LogStatus.INFO, "We are going to enter username and password");
		
		loginPOM.sendUserName("Om1@mail.com");
		loginPOM.sendPassword("omkar213");
		
		
		logger.log(LogStatus.PASS, "Login credentials has been filled");
		
		logger.log(LogStatus.INFO, "Click on SignIn button");
		loginPOM.clickSignInBtn();
		
		try {
			
			String expected="My Profile – Real Estate";
			String actualString=loginPOM.pagetitle();
			assertEquals(actualString, expected) ;
	     logger.log(LogStatus.PASS, "We are at customer home page");
			
		}
	     
	     catch(Error e) {
	    	log.error("Login is failed");
	    	 logger.log(LogStatus.FAIL, "Login is failed");
	 		
	     }
	    
		logger.log(LogStatus.INFO, "Mouse Over on Plots");
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  retc064POM.clickonPlots();
		  
		  logger.log(LogStatus.PASS, "Plots section is highlighted");
		  
		  
		  logger.log(LogStatus.INFO, "Select Donec Quis property");
		  retc064POM.selectProperty();
		  
		  logger.log(LogStatus.PASS, "Donec Quis Property page is displayed");
		  
		  logger.log(LogStatus.INFO, "Fill enquiries form details");
		  
		  retc064POM.sendEnquiry(yourName,email,subject, message);
		  
		  logger.log(LogStatus.PASS, "Enquiry form is filled");
		  
		  screenShot.captureScreenShot("RTC_064_FormFilled");
		  
		  logger.log(LogStatus.INFO, "Click on Send button");
		  
		  retc064POM.clickSendBtn();
		  
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  screenShot.captureScreenShot("RTC_064_EnquirySentMessage");
		 
		  if (retc064POM.ispresent()) {
			  
		  
		  try {
			  String actualMsg=retc064POM.enquiryMsg();
			  String expectMsg="Thanks you for your message. It has been sent.";
		
			  assertEquals(actualMsg, expectMsg);
			 
			 logger.log(LogStatus.PASS, "Send Enquiry Message is displayed as expected");
			  } 
		  
			  catch(Error e) {
				  

					 log.error("Send Enquiry Message is not displayed as expected");
					logger.log(LogStatus.FAIL, "Send Enquiry Message is not displayed as expected");
				
			  } 
		  }
		  else {
			  

				  
				  log.error("Check the inputs provided in form");
				logger.log(LogStatus.FAIL, "Check the inputs provided in form");
			  }
			  
			  extent.endTest(logger);
			  extent.flush();
		  }
}
			  
		  
		
		  
		  


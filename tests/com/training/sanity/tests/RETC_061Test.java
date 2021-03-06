package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.training.dataproviders.RETC061DataProvider;
import com.training.generics.ScreenShot;
import com.training.pom.LoginTest_POM;
import com.training.pom.RETC_061_POM;
import com.training.pom.RegisterTest_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_061Test {

	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RegisterTest_POM registerPOM;
	private RETC_061_POM retc061POM;
	private static Properties properties;
	private ScreenShot screenShot;
	private static ExtentReports extent;
	private ExtentTest logger;
	
	//Log4j code
		Logger log= Logger.getLogger(RETC_061_POM.class);

	
	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RTC_061_Extentreport.html");
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginTest_POM(driver); 
		retc061POM=new RETC_061_POM(driver);
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	
	

	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	
	
		
	@Test(dataProvider = "excel-inputs", dataProviderClass = RETC061DataProvider.class)
	
	
	public void validRegisterTest(String emailId,String firstname,String lastname) throws InterruptedException {
	
		logger = extent.startTest("Registration Test");
		
		logger.log(LogStatus.INFO, "Click on Login/Register Link from Home page");
		
		loginPOM.clickLoginLink();
		
		logger.log(LogStatus.PASS, "Login screen is opened");
		
		logger.log(LogStatus.INFO, "Click on Register Link from Login page");
		
		loginPOM.clickRegisterLinkBtn();
		
		logger.log(LogStatus.PASS, "Regsitration page is opened");
		try {
			
		 boolean actual = loginPOM.registerBtnVisible();
	     assertEquals(actual, true);
	     logger.log(LogStatus.PASS, "We are at Registration page");
			
		}
	     
	     catch(Error e) {
	    	log.error("Registration is failed");
	    	 logger.log(LogStatus.FAIL, "We are not reached to Registration page");
	 		
	     }
	    
		
		
		
		logger.log(LogStatus.INFO, "Fill the details");
		
		retc061POM.sendUserDetails(emailId, firstname, lastname);
		logger.log(LogStatus.PASS, "Details are filled correctly");
		

		screenShot.captureScreenShot("RETC_061Test_RegistrationDetails");
		
		
		
		
		logger.log(LogStatus.INFO, "Click on Register button");
		
		retc061POM.clickRegisterBtn();
		
	try {
       
        
     boolean actual = retc061POM.SignInVisible();
    assertTrue(actual);
     logger.log(LogStatus.PASS, "Registration is sucessfull");
		
	}
     
     catch(Error e) {
    	log.error("Registration is failed");
    	 logger.log(LogStatus.FAIL, "Registration is not sucessfull");
 		
     }
     extent.endTest(logger);
     extent.flush();
	}
	
	
	
}

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import com.training.generics.ScreenShot;
import com.training.pom.LoginTest_POM;
import com.training.pom.RETC_032_POM;

import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_032_Test {
	
	
	
	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RETC_032_POM retc032POM;
	private static Properties properties;
	private ScreenShot screenShot;
	//private ExtentReporter report;
	private static ExtentReports extent;
	private ExtentTest logger;

	
//Log4j code
	Logger log= Logger.getLogger(RETC_032_POM.class);
	
	
	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RTC_064_Extentreport.html");


	}
	
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginTest_POM(driver); 
		retc032POM=new RETC_032_POM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	
	
	
//Login to the admin home
	
	
	@Test(priority=1)
	
	public void login() {
		logger = extent.startTest("Login to Admin HomePage Test");
		
		logger.log(LogStatus.INFO, "We are going to enter username and password");
		
		loginPOM.sendUserName("admin");
		logger.log(LogStatus.PASS, "username entered");
		loginPOM.sendPassword("adminuser@12345");
		logger.log(LogStatus.PASS, "Password entered");
		screenShot.captureScreenShot("RTC_032_Login");
		loginPOM.clickSignInBtn();
		logger.log(LogStatus.PASS, "Login button is clicked");
		screenShot.captureScreenShot("RTC_032_AdminDashBoardPage");
		loginPOM.clickrealEstateLink();
		logger.log(LogStatus.PASS, "RealEstate link is clicked");
		
		String s=driver.getTitle();
		System.out.println(s);
		screenShot.captureScreenShot("RTC_032_HomePage");
		try {
		String expected="Real Estate";
		String actualString=loginPOM.pagetitle();
		
		assertEquals(actualString, expected) ;
		logger.log(LogStatus.PASS, "We are at admin home page now");
		} catch (Error e) {
		log.error("Title of page is not displayed as expected");
		logger.log(LogStatus.FAIL, "Title of page is not displayed as expected");
		}
		extent.endTest(logger);
		extent.flush();

			
		}
		
		

	 //Send the Enquiry, related New Launch properties
	
	
  @Test(priority=2)
  public void sendEnquiry() {
	  logger = extent.startTest("Send Equiry Test");
	  logger.log(LogStatus.INFO, "Mouse Over on NewLaunch");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  retc032POM.clickNewLaunch();
	  screenShot.captureScreenShot("RTC_032_NewLaunch");
	  logger.log(LogStatus.PASS, "New Launch Properties displayed");
	  logger.log(LogStatus.INFO, "Select Nulam Property");
	  log.info("New launch property highlighted");
	  retc032POM.selectProperty();
	  logger.log(LogStatus.PASS, "Nulam Property selected");
	  screenShot.captureScreenShot("RTC_032_NulamSelected");
	  log.info("Nulam property selected");
	  logger.log(LogStatus.INFO, "Fill enquiries form details");
	  
	  retc032POM.sendEnquiry("Om", "om1@mail.com", "Apt", "when it is available");
	
	  logger.log(LogStatus.PASS, "Enquiry form is filled");
	  log.info("Enquiry details entered");
	  screenShot.captureScreenShot("RTC_032_FormFilled");
	  logger.log(LogStatus.INFO, "Click on Send button");
	  retc032POM.clickSendBtn();
	  log.info("Send button is clicked");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  screenShot.captureScreenShot("RTC_032_SendEnquiryMsg");
	  
	  try {
	  String actualMsg=retc032POM.enquiryMsg();
	  String expectMsg="Thanks you for your message. It has been sent.";
	  
	 assertEquals(actualMsg, expectMsg, "Thanks you for your message. It has been sent.");
	  log.info("Send Enquiry Message is displayed as expected");
	 logger.log(LogStatus.PASS, "Send Enquiry Message is displayed as expected");
	  }
	  catch(Error e) {
			 log.error("Send Enquiry Message is not displayed as expected");
			logger.log(LogStatus.FAIL, "Send Enquiry Message is not displayed as expected");
		 }
	  extent.endTest(logger);
	  extent.flush();
  }
	
  
  //Calculate the Loan Amount
	 @Test(priority=3)
	  
	  public void calcMortage() {
		 
		 logger = extent.startTest("Calculate Mortage Test");
		 logger.log(LogStatus.INFO, "Fill all the details asked in Mortage calculator form");
	 log.info("All the details filled");
	 
	  retc032POM.MortageClac("40000", "2000", "5", "2");
	  screenShot.captureScreenShot("RTC_032_MortageFormFilled");
	  logger.log(LogStatus.PASS, "All the details are filled correctly");
	  log.info("Click on Calculate button");
	  logger.log(LogStatus.INFO, "Click on Calculate button");
	  retc032POM.clickCalc();
	  screenShot.captureScreenShot("RTC_032_MortageCalcMsg");
	  
	  try {
	 String expectMsg="Monthly Payment: ";
	 String actualMsg=retc032POM.mortageCalcMsg();
	  
	 
	assertTrue(actualMsg.contains(expectMsg),"Monthly Payment: ...");
	log.info("Mortage Calucation message is dispalyed as expected"); 
	logger.log(LogStatus.PASS, "Mortage Calucation message is dispalyed as expected"); 
	  
	
	  }
	 catch(Error e) {
		 log.error("Mortage Calucation message is not dispalyed as expected");
		logger.log(LogStatus.FAIL, "Mortage Calucation message is not dispalyed as expected");
	 }
	  extent.endTest(logger);
	  extent.flush();

	}
	 
	 //Close down the Url

	  @Test(priority=4)
	  public void tearDown() throws Exception {
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
	  

}

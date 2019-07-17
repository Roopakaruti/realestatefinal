package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.LoginTest_POM;
import com.training.pom.RETC_031_POM;
import com.training.pom.RETC_034_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_034_Test {
	private WebDriver driver;
	public WebDriverWait wait;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RETC_031_POM retc031POM;
	private RETC_034_POM retc034POM;
	private static Properties properties;
	private ScreenShot screenShot;
	//private ExtentReporter report;
	private static ExtentReports extent;
	private ExtentTest logger;

	
//Log4j code
	Logger log= Logger.getLogger(RETC_031_POM.class);
	
	

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RTC_034_Extentreport.html");


	}
	
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginTest_POM(driver); 
		retc031POM=new RETC_031_POM(driver);
		retc034POM=new RETC_034_POM(driver);
		baseUrl = properties.getProperty("baseURL1");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	

	@Test(priority=1)
	
	public void login() {
		logger = extent.startTest("Login to Customer HomePage Test");
		logger.log(LogStatus.INFO, "Click on Login/Register Link");
		
		loginPOM.clickLoginLink();
		logger.log(LogStatus.INFO, "We are going to enter username and password");
		log.info("Provide username and password");
		loginPOM.sendUserName("om1@mail.com");
		logger.log(LogStatus.PASS, "username entered");
		loginPOM.sendPassword("omkar213");
		logger.log(LogStatus.PASS, "Password entered");
		screenShot.captureScreenShot("RTC_034_LoginDetails");
		logger.log(LogStatus.INFO, "CLick on SignIn button");
		log.info("Click on SignIn button");
		loginPOM.clickSignInBtn();
		logger.log(LogStatus.PASS, "Login button is clicked");
		screenShot.captureScreenShot("RTC_034_Customer Homepage");
		
		try {
		String expected="My Profile – Real Estate";
		String actualString=loginPOM.pagetitle();
		assertEquals(actualString, expected) ;
		logger.log(LogStatus.PASS, "We are at Customer Home page");
		}
		
		catch(Error e) {
			log.error("Check the Login details");
			logger.log(LogStatus.FAIL, "Login Test is failed,check the login credentials");
		}
		
		extent.endTest(logger);
		extent.flush();
	}
		
	@Test(priority=2)
	
	  public void ContactforCommercialProp() throws InterruptedException {
			
			logger=extent.startTest("Open the properties in Commercial and contact through DropUs link");
			logger.log(LogStatus.INFO, "Go to Commercial link");
			log.info("Click on Commercial tab");
			retc034POM.commercialClick();
			screenShot.captureScreenShot("RTC_034_Cmmercial");
			
			logger.log(LogStatus.PASS, "Commercial Link is opened");
			logger.log(LogStatus.INFO, "Search for Nullam properties in Find your Home section");
			log.info("Search for Nullam properties");
			
			retc034POM.enablesearch("Nullam hendrerit apartment");
			screenShot.captureScreenShot("RTC_034_Cmmercial_FindYourHome");
			logger.log(LogStatus.PASS, "Search box is enabled");
			logger.log(LogStatus.INFO, "Search for Nullam properties in search box");
			log.info("Search for Nullam properties");
			
			retc034POM.searchProp("Nullam hendrerit apartment");
			
			screenShot.captureScreenShot("RTC_034_Cmmercial_Searchbox");
			logger.log(LogStatus.PASS, "Nullam property new window opened");
			
			
		
			
			
			screenShot.captureScreenShot("RTC_034_NullamPropertyWindow");
			
			retc034POM.childwindowSwitch();
			
			
			
			try {
				String expected="http://realestatem1.upskills.in/nullam-hendrerit-apartments/";
				String actual=retc034POM.postnavigateNullam();
				
				assertEquals(actual, expected);
				
				logger.log(LogStatus.PASS, "We are at Nullam property window");
			}
			catch(Error e) {
				log.error("We are not naviagted to Nullam property window" );
				logger.log(LogStatus.FAIL, "We are not naviagted to Nullam property window");
			}
			//retc034POM.parentwindowSwitch();
			logger.log(LogStatus.INFO, "Click on DropUs Link");
			log.info("Click on DropUs Link");
			screenShot.captureScreenShot("RTC_034_Cmmercial_DropUsLink");
			
			retc034POM.dropUsLink();
			logger.log(LogStatus.PASS, "Contact Form is opened");
			screenShot.captureScreenShot("RTC_034_Cmmercial_ContactForm");
			logger.log(LogStatus.INFO, "Fill all details in Contact Form");
			log.info("Provide all info in Contact form");
			retc034POM.contactForm("Om", "om1@mail.com", "Availablity", "When the apt is available to occupy");
			logger.log(LogStatus.PASS, "Contact Form is filled");
			screenShot.captureScreenShot("RTC_034_Cmmercial_ContactFormFilled");
			logger.log(LogStatus.INFO, "Click on Send  button");
			log.info("Click on Send button");
			retc034POM.sendBtnClick();
			screenShot.captureScreenShot("RTC_034_Cmmercial_ContactFormSent");
			try {
				String expected="Thanks you for your message. It has been sent.";
				String actual=retc034POM.postSendContactForm();
				assertEquals(actual, expected);
				logger.log(LogStatus.PASS, "Contact Form is successfully sent");
			}
			catch(Error e) {
				log.error("Contact Form is not sent");
				logger.log(LogStatus.FAIL, "Contact Form is not sent");
			}
			extent.endTest(logger);
			extent.flush();
}
	
	 @Test(priority=3)
	  public void tearDown() throws Exception {
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
}

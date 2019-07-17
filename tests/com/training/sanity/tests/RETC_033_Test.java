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
import com.training.pom.RETC_033_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_033_Test {


	
	private WebDriver driver;
	public WebDriverWait wait;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RETC_031_POM retc031POM;
	private RETC_033_POM retc033POM;
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
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RTC_033_Extentreport.html");


	}
	//Broser setup
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginTest_POM(driver); 
		retc031POM=new RETC_031_POM(driver);
		
		retc033POM=new RETC_033_POM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	//Login to admin home page

	@Test(priority=1)
	
	public void login() {
		logger = extent.startTest("Login to Admin HomePage Test");
		
		logger.log(LogStatus.INFO, "We are going to enter username and password");
		
		loginPOM.sendUserName("admin");
		logger.log(LogStatus.PASS, "username entered");
		loginPOM.sendPassword("adminuser@12345");
		logger.log(LogStatus.PASS, "Password entered");
		screenShot.captureScreenShot("RTC_033_Login");
		loginPOM.clickSignInBtn();
		logger.log(LogStatus.PASS, "Login button is clicked");
		
		
		String s=driver.getTitle();
		System.out.println(s);
		screenShot.captureScreenShot("RTC_033_AdminDashBoardPage");
		try {
		String expected="Dashboard ‹ Real Estate — WordPress";
		String actualString=loginPOM.pagetitle();
		
		assertEquals(actualString, expected) ;
		logger.log(LogStatus.PASS, "We are at Admin Dashboard page");
		} catch (Error e) {
		log.error("Title of page is not displayed as expected");
		logger.log(LogStatus.FAIL, "Title of page is not displayed as expected");
		}
		extent.endTest(logger);
		extent.flush();

			
		}
		
	
	
	//Add new Property method
	  
	
	
	@Test(priority=2)
	
  public void addNewProperty() throws InterruptedException {
		
		logger=extent.startTest("Adding New Property");
		logger.log(LogStatus.INFO, "Go to Properties link");
		log.info("Click on Properties tab");
		retc031POM.propertyLink();
		logger.log(LogStatus.PASS, "Properties page is opened");
		
		logger.log(LogStatus.INFO, "Click on Add New link");
		log.info("Click on Add New link");
		retc031POM.clickaddNew();
		logger.log(LogStatus.PASS, "Add Property form is opened");
		
		logger.log(LogStatus.INFO, "Fill the Property Title info");
		
		log.info("Provide Title of the property ");
		retc031POM.addTitleInfo("4New", "4New");
		logger.log(LogStatus.PASS, "Added property title info");
		
		
		logger.log(LogStatus.INFO, "Click on publish button");
		
		
		log.info("Click on publish ");
		Thread.sleep(5000);
		retc031POM.clickPublishBtn();
	
		//driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		Thread.sleep(5000);
		//WebDriverWait wait=new WebDriverWait(driver, 20);
		screenShot.captureScreenShot("RETC_033_PostPublishMessage");
		logger.log(LogStatus.PASS, "Successfully added the property");
		
		
		  
		  logger.log(LogStatus.INFO, "Click on All properties link");
		  log.info("Click on all properties "); retc033POM.gotoAllProp();
		  screenShot.captureScreenShot("RETC_033_All properties");
		  
		
		  try {
		  
		  boolean expected= true; boolean actual=retc033POM.findprop();
		  
		  assertEquals(actual, expected); logger.log(LogStatus.
		  PASS,"Newly launch property is there in ALL properties successfully"); }
		  
		  catch(Error e) {
		  
		  log.error("New Property is not able to find in All properties section");
		  logger.log(LogStatus.
		  FAIL,"Property is not able to find in All properties section"); }
		 
		extent.endTest(logger);
		extent.flush();
		}
	
	
	//Close the browser
	
	@Test(priority=3)
	  public void tearDown() throws Exception {
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
		
		
		
}


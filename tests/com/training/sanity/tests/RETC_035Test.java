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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ScreenShot;
import com.training.pom.LoginTest_POM;
import com.training.pom.RETC_035_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_035Test {
  
	
	
	
	
	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RETC_035_POM retc035POM;
	private static Properties properties;
	private ScreenShot screenShot;
	//private ExtentReporter report;
	private static ExtentReports extent;
	private ExtentTest logger;

	
//Log4j code
	Logger log= Logger.getLogger(RETC_035_POM.class);
	
	
	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RTC_035_Extentreport.html");


	}
	
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginTest_POM(driver); 
		retc035POM=new RETC_035_POM(driver);
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}
	
	
	
	
//Login to the admin home
	
	
	@Test(priority=1,dataProvider="dp")
	
	public void login(String uname,String pass) {
		logger = extent.startTest("Login to Admin HomePage Test");
		
		logger.log(LogStatus.INFO, "We are going to enter username and password");
		
		loginPOM.sendUserName(uname);
		logger.log(LogStatus.PASS, "username entered");
		loginPOM.sendPassword(pass);
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
		
	
	//Add New User method
	
	 @Test(priority=2)
	  public void addUser() {
		  logger = extent.startTest("Add New User Test");
		  logger.log(LogStatus.INFO, "Click on Users link");
			
		  retc035POM.clickUserLink();
		  logger.log(LogStatus.PASS, "Users Page is opened Successfully");
		  
		  logger.log(LogStatus.INFO, "Click on Add New link");
		  
		  retc035POM.addUserLink();
		  logger.log(LogStatus.PASS, "Add New user page opened successfully");
		  
		  logger.log(LogStatus.INFO, "Fill the details of the New User");
		  
		  retc035POM.fillDetails("Janvi", "Janvi@mail.com", "Janvi", "Mehta", "www.google.com");
		  logger.log(LogStatus.PASS, "Details are added");
		  
		  logger.log(LogStatus.INFO, "Click on Show Password");
		  
		  retc035POM.clickPwd();
		  logger.log(LogStatus.PASS, "Random password is showed");
		  
		  logger.log(LogStatus.INFO, "Set the Password");
		  
		  retc035POM.setPwd("janvi@123");
		  logger.log(LogStatus.PASS, "Password is set");
		  
		  logger.log(LogStatus.INFO, "Confirm the password");
		  
		  retc035POM.confirmchkbox();
		  logger.log(LogStatus.PASS, "Confirm password is checked");
		  
		  logger.log(LogStatus.INFO, "Select the role");
		  
		  retc035POM.selectRole();
		  logger.log(LogStatus.PASS, "Role is selected");
		  
		  logger.log(LogStatus.INFO, "Click on Add User button");
		  retc035POM.addUser();
		  screenShot.captureScreenShot("RETC_035Test_New User created");
		  
		  try {
			  String expected="New user created. Edit user";
			  String actual=retc035POM.addUserMsg();
			  
			  assertEquals(actual, expected);
			  logger.log(LogStatus.PASS, "New User has been created successfully");
			  
		  }
		  catch(Error e) {
			  log.error("New User is not created");
			  logger.log(LogStatus.FAIL, "New User has not been created ");
		  }
		  
		  logger.log(LogStatus.INFO, "Go to Users-Agent section");
		  
		  
		  
		  retc035POM.alluserAgentClick();
		  screenShot.captureScreenShot("RETC_035Test_New User Showed");
		  
		  try {
			assertTrue(retc035POM.userpresent());
			logger.log(LogStatus.PASS, "User is present");
		  }catch(Error e)
		  {
			  logger.log(LogStatus.FAIL, "User is not present in Agent list");
		  }
		  
		  extent.endTest(logger);
		  extent.flush();
		  

	 
	}
	 //close the browser
	 @Test(priority=3)
	  public void tearDown() throws Exception {
			 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.quit();
		}
	 
	 
	 //Data provider for login
	 @DataProvider(name="dp")
	 public Object[][] getDataFromDataprovider()
	 {
	
	 return new Object[][]
	 {
	 {"admin", "adminuser@12345"},
	
	 };

	 }


}
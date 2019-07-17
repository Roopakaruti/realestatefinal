package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
import com.training.pom.RETC_061_POM;
import com.training.pom.RETC_065_POM;
import com.training.pom.RegisterTest_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class RETC_065Test {

	
	private WebDriver driver;
	public WebDriverWait wait;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RETC_031_POM retc031POM;
	private RETC_033_POM retc033POM;
	private RETC_065_POM retc065POM;
	private static Properties properties;
	private ScreenShot screenShot;
	//private ExtentReporter report;
	private static ExtentReports extent;
	private ExtentTest logger;

	
//Log4j code
	Logger log= Logger.getLogger(RETC_065_POM.class);

	@BeforeSuite
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
		extent = new ExtentReports("C:\\Users\\IBM_ADMIN\\Desktop\\SE_PROJECT\\RTC_065_Extentreport.html");


	}
	//Broser setup
	
	@BeforeTest
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		
		loginPOM = new LoginTest_POM(driver); 
		retc031POM=new RETC_031_POM(driver);
		retc065POM=new RETC_065_POM(driver);
		
		
		baseUrl = properties.getProperty("baseURL");
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
	}

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
	
	@Test(priority=2)
	
	  public void addNewCategory() throws InterruptedException {
			
			logger=extent.startTest("Adding New Category");
			logger.log(LogStatus.INFO, "Click on Posts link and go to categories page");
			retc065POM.clickPost();
			retc065POM.clickcategory();
			logger.log(LogStatus.PASS, "We are at categories page");
			
			logger.log(LogStatus.INFO, "CLick on Add new category link and fill the form");
			
			
			
		
			retc065POM.addCategory("LakeView", "Lakeview"," New Launch having view of Lake");
			
			logger.log(LogStatus.PASS, "Form is filled with appropriate details");
			
			screenShot.captureScreenShot("RETC_065Test_Category");
			
			logger.log(LogStatus.INFO, "CLick on Add new category button");
			
			retc065POM.ClickAddNewCatgry();
			
			logger.log(LogStatus.PASS, "Catgeory is successfully added");
			
			
			logger.log(LogStatus.INFO, "Navigate to all posts page");
			
			retc065POM.clickAllPost();
			
			logger.log(LogStatus.PASS, "Navigated to All posts page");
			
			logger.log(LogStatus.INFO, "Click Add new post");
			
			retc065POM.clickAddNew();
			
			logger.log(LogStatus.PASS, "Post form is displayed");
			
			
			logger.log(LogStatus.INFO, "Fill the details");
			
			retc065POM.fillPostDetails("New Launch", "New Launch in Home");
			
			logger.log(LogStatus.PASS, "Details are added");
			
			logger.log(LogStatus.INFO, "Select newly added category");
			retc065POM.selectCategry();
			logger.log(LogStatus.PASS, "Successfully selected the catgpry");
			
			
			logger.log(LogStatus.INFO, "Click on Publish button");
			retc065POM.clickPublish();
			
			screenShot.captureScreenShot("RETC_065Test_postpublished");
			
			try {
				String expected="Post published. View post";
				String actualMsg=retc031POM.viewPostMsg();
				
				assertEquals(actualMsg, expected);
				assertTrue(retc065POM.viewPostlinkPresent());
				logger.log(LogStatus.PASS, "Property is published successfully");
				}
			
			catch(Error e) {
			log.error("post is not published");
			logger.log(LogStatus.FAIL, "Post is not published");
			}
			
			logger.log(LogStatus.INFO, "Click on Logout button");
			
			retc065POM.clickLogout();
			logger.log(LogStatus.PASS, "Successfully logged out");
	extent.endTest(logger);
	extent.flush();		
	}
	
@Test(priority=3)
	
	public void loginAgain() throws InterruptedException {
		logger = extent.startTest("Check the Newly added post showed on admin blog section");
		
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
		logger.log(LogStatus.PASS, "We are at admin home page now");
		
		logger.log(LogStatus.INFO, "Click on Blog link");
		retc065POM.clickBlog();
		logger.log(LogStatus.PASS, "Blog section is opened");
		
		logger.log(LogStatus.INFO, "Check newly added post is displayed or not");
		
		
		
		
		try {
		boolean actual=retc065POM.verifynewBlogpost();
		
		
		assertTrue(actual);
		logger.log(LogStatus.PASS, "New blog post is displayed");
		} catch (Error e) {
		log.error("No new new blog post displayed");
		logger.log(LogStatus.FAIL, "No new new blog post displayed under Admin Blog section");
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
	 
			
		
}

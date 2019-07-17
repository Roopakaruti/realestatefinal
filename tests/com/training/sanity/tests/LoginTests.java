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

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.training.generics.ExtentReporter;
import com.training.generics.ScreenShot;
import com.training.pom.LoginTest_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class LoginTests {

	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private ExtentReporter report;
	
	
	Logger log= Logger.getLogger(LoginTest_POM.class);

	@BeforeClass
	public static void setUpBeforeClass() throws IOException {
		properties = new Properties();
		FileInputStream inStream = new FileInputStream("./resources/others.properties");
		properties.load(inStream);
	}

	@BeforeMethod
	public void setUp() throws Exception {
		driver = DriverFactory.getDriver(DriverNames.CHROME);
		loginPOM = new LoginTest_POM(driver); 
		baseUrl = properties.getProperty("baseURL");
		
		report= new ExtentReporter();
		
		screenShot = new ScreenShot(driver); 
		// open the browser 
		driver.get(baseUrl);
		log.info("Launching the Browser");
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		Thread.sleep(1000);
		driver.quit();
	}
	@Test
	public void validLoginTest() throws InterruptedException {
		
		
		
		
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("om1@mail.com");
		loginPOM.sendPassword("omkar213");
		
		screenShot.captureScreenShot("LoginDetailsPage1");
		
		loginPOM.clickSignInBtn();
		
		Thread.sleep(500);
		
		String expected="My Profile – Real Estate1";
		String actualString=loginPOM.pagetitle();
		
		screenShot.captureScreenShot("LoginDetailsPage2");
		
		try {
		
		
		
			assertEquals(actualString, expected) ;
			report.generateReportsP(actualString, "LogionTestPassReport");
		}
		
		catch(Error e) {
		
			log.error("Login Failed");
			report.generateReportsF(actualString, "LogionTestFailReport");
		}
		
		}
		
		
		
	
	
	
	
	
}

package com.training.sanity.tests;

import static org.testng.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.training.generics.ExtentReporter;
import com.training.generics.ScreenShot;
import com.training.pom.LoginTest_POM;
import com.training.pom.MyProfileTest_POM;
import com.training.pom.PasswordRecTest_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ChangePhoneNumTest {

	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private PasswordRecTest_POM pwdrecPOM;
	private MyProfileTest_POM myprofilePOM;
	private static Properties properties;
	private ScreenShot screenShot;
	private ExtentReporter report;

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
		pwdrecPOM=new PasswordRecTest_POM(driver);
		myprofilePOM=new MyProfileTest_POM(driver);
		report=new ExtentReporter();
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
	
	@Test
	public void changePhoneNum() {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("om1@mail.com");
		loginPOM.sendPassword("omka213");
		loginPOM.clickSignInBtn();
		
		screenShot.captureScreenShot("LoginScreen");
		
		
		
		boolean actualName=myprofilePOM.validateUser();	
		
		
		assertEquals(actualName, true);
		
		myprofilePOM.changePhone("1115667889");
	
		
	
		String actualMsg=myprofilePOM.successMsg();
		
		screenShot.captureScreenShot("Myprofilepage1");
		
		
		String expectMsg="Your profile has been updated.";
		
		screenShot.captureScreenShot("Myprofilepage2");
		
		try {
			
			
			assertEquals(actualMsg, expectMsg);
			report.generateReportsP(actualMsg, "ChangePhonePassReport");
			
		}
		
		catch(Error e) {
			assertEquals(actualMsg, expectMsg);
			report.generateReportsP(actualMsg, "ChangePhoneFailReport");
		}
		
		
		
		
		
	}
	
}

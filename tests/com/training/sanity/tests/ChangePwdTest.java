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
import com.training.pom.ChangePwdTest_POM;
import com.training.pom.LoginTest_POM;
import com.training.pom.MyProfileTest_POM;
import com.training.pom.PasswordRecTest_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ChangePwdTest {
	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private PasswordRecTest_POM pwdrecPOM;
	private MyProfileTest_POM myprofilePOM;
	private ChangePwdTest_POM changepwdPOM;
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
		changepwdPOM=new ChangePwdTest_POM(driver);
		report= new ExtentReporter();
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
	public void changePhoneNum() throws InterruptedException {
		loginPOM.clickLoginLink();
		loginPOM.sendUserName("paru@mail.com");
		loginPOM.sendPassword("paru@123");
		loginPOM.clickSignInBtn();
		
		screenShot.captureScreenShot("LoginScreen");
		
		boolean actualName=myprofilePOM.validateUser();
		
		assertEquals(actualName, true);
		
		screenShot.captureScreenShot("Myprofilepage1");
		
		myprofilePOM.changePwdlinkClick();
		changepwdPOM.changepwd("paru@123", "paru@321", "paru@321");
		
		screenShot.captureScreenShot("ChangePasswordPage1");

		
		String expectedMsg="Your password has been updated.";
		screenShot.captureScreenShot("ChangePasswordPage2");
		String actualMsg=changepwdPOM.successMsg();
		
		
		try {
		assertEquals(actualMsg, expectedMsg);
		report.generateReportsP(actualMsg, "ChangePwdPassReport");
		}
		
		
		catch(Error e) {
			assertEquals(actualMsg, expectedMsg);
			report.generateReportsF(actualMsg, "ChangePwdFailReport");
		}
		
	}	
	
}

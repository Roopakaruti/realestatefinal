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
import com.training.pom.PasswordRecTest_POM;
import com.training.pom.RegisterTest_POM;
import com.training.utility.DriverFactory;
import com.training.utility.DriverNames;

public class ResetPwdTest {
	private WebDriver driver;
	private String baseUrl;
	private LoginTest_POM loginPOM;
	private RegisterTest_POM registerPOM;
	private PasswordRecTest_POM pwdrecPOM;
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
		registerPOM=new RegisterTest_POM(driver);
		pwdrecPOM=new PasswordRecTest_POM(driver);
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
	public void passwordRecTest()  {
		
		loginPOM.clickLoginLink();
		
		screenShot.captureScreenShot("LostPasswordLink");
		
		loginPOM.clickLostPwdLink();
	
		pwdrecPOM.sendEmailId("kala@mail.com");
		screenShot.captureScreenShot("BeforeResetBtnClick");
		
	    pwdrecPOM.clickResetPwd();
	    screenShot.captureScreenShot("AfterResetBtnClick");
	    
	    String actualmsg=pwdrecPOM.afterResetMessage();
	    String expectedmsg= "The email could not be sent."+
	    		"Possible reason: your host may have disabled the mail() function.";
		
	    
	  
		
try {
			
			
	  assertEquals(actualmsg, expectedmsg);
			report.generateReportsP(actualmsg, "ResetPwdPassReport");
			
		}
		
		catch(Error e) {
			  assertEquals(actualmsg, expectedmsg);
			report.generateReportsF(actualmsg, "ResetPwdFailReport");
		}
	    
		
	}
}
      
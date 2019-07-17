package com.training.pom;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class PasswordRecTest_POM {
	
		private WebDriver driver; 
		
		public PasswordRecTest_POM(WebDriver driver) {
			this.driver = driver; 
			PageFactory.initElements(driver, this);
		}
		
	
		
		
		
		@FindBy(css="input#user_login")
		private WebElement emailId;
		
		@FindBy(css="input.lostpassword-button")
		private WebElement resetBtn;
		
		@FindBy(css="body#error-page")
		private WebElement errorMsg;
		
		
		public void sendEmailId(String emailId) {
		this.emailId.sendKeys(emailId);
			
		}
		
		
		
		public void clickResetPwd() {
			this.resetBtn.click();
			
		}
		
		public String afterResetMessage() {
			String actual=this.errorMsg.getText();
			return actual;
			
		}
		
}

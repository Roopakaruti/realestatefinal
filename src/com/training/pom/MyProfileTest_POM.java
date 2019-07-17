package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class MyProfileTest_POM {
  
	private WebDriver driver; 
	
	public MyProfileTest_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="phone")
	private WebElement phone;
	
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement savechangesBtn;
	
	@FindBy(css="div[class*='success']")
	private WebElement message;
	
	@FindBy(css="input#first-name")
	private WebElement firstName;
	
	@FindBy(css="i[class*='sl sl-icon-lock']")
	private WebElement changePwdBtn;
	
	

	
	
	
	

	
	public void changePhone(String phone) {
		this.phone.clear();
		this.phone.sendKeys(phone);
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", this.savechangesBtn);
		this.savechangesBtn.click();
	}
	
	
	public boolean validateUser() {
		boolean name=this.firstName.isDisplayed();
		return name;
		
	}
	
	public String successMsg() {
		
		String actualMsg=this.message.getText();
		return actualMsg;
		
		
	}
	
	public void changePwdlinkClick() {
	
		this.changePwdBtn.click();
	}
	
}

package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

public class LoginTest_POM {
	
	WebDriver driver;
	
	
	
	public LoginTest_POM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	


@FindBy(css="a.sign-in")
private WebElement loginLink; 

@FindBy(css="input#user_login")
private WebElement userName;

@FindBy(css="input#user_pass")
private WebElement passWord;

@FindBy(xpath="//input[@name='login']")
private WebElement signinBtn; 

@FindBy(xpath="//a[text()='Real Estate']")
private WebElement realEstateHome; 


@FindBy(xpath="//a[text()='Register']")
private WebElement registerLinkBtn;

@FindBy(xpath="//a[text()=' Lost Your Password?']")
private WebElement lostPwd;




public void sendUserName(String userName) {
	//this.userName.clear();
	this.userName.sendKeys(userName);
}

public void sendPassword(String password) {
	//this.password.clear(); 
	
	this.passWord.sendKeys(password);
	 
}

public void clickLoginLink() {
	this.loginLink.click();
}


public void clickSignInBtn() {
	
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].scrollIntoView(true);", this.signinBtn);
		 */
	this.signinBtn.click(); 
}

public void clickRegisterLinkBtn() {
	this.registerLinkBtn.click();
}

public void clickLostPwdLink() {
	this.lostPwd.click();
}

public void clickrealEstateLink() {
	this.realEstateHome.click();
}

public String pagetitle() {
	String title=this.driver.getTitle();
	
	return title;
}


public boolean registerBtnVisible() {
	
	boolean actual=this.registerLinkBtn.isDisplayed();
	
	return actual;
}

public String getUsername() {
	return this.userName.getAttribute("value");

}

public String getPassword() {
	return this.passWord.getAttribute("value");
}
}
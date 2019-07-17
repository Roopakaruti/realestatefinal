package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RETC_061_POM {

WebDriver driver;
	
	public RETC_061_POM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	




@FindBy(css="input#email")
private WebElement emailId; 

@FindBy(css="input#first-name")
private WebElement firstName;

@FindBy(css="input#last-name")
private WebElement lastName; 


@FindBy(css="input.register-button")
private WebElement registerBtn; 

@FindBy(css="//div[@class='notification success closeable']")
private WebElement successMsg;

@FindBy(xpath="//input[@name='login']")
private WebElement signInBtn;



public void sendUserDetails(String emailId,String firstName,String lastName) {
	
	
	this.emailId.clear();
	this.emailId.sendKeys(emailId);
	this.firstName.clear();
	this.firstName.sendKeys(firstName);
	this.lastName.clear(); 
	this.lastName.sendKeys(lastName); 
}







public void clickRegisterBtn() {
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", this.registerBtn);
	
	this.registerBtn.click(); 
}

public String successMsg() {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", this.successMsg);
	
	String msg= this.successMsg.getText();
	return msg;
	
	
	
	
}

public boolean SignInVisible() {
	
	boolean actual=this.signInBtn.isDisplayed();
	
	return actual;
}

}
	

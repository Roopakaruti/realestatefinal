package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RETC_063_POM {


WebDriver driver;
	
	public RETC_063_POM(WebDriver driver) {
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

@FindBy(xpath="//div[@class='notification error closeable']")
private WebElement failMsg;




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

public String failMsg() {
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("arguments[0].scrollIntoView(true);", this.failMsg);
	
	String msg= this.failMsg.getText();
	return msg;
	
	
	
	
}



}
	

	


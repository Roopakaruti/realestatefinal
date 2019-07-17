package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RETC_064_POM {
	
WebDriver driver;
	
	public RETC_064_POM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	
@FindBy(xpath="//a[text()='Plots ']")
private WebElement plotsLinks;

@FindBy(xpath="//a[text()='Donec quis']")
private WebElement donecquisProp;

@FindBy(css="input[name=your-name]")
private WebElement yourName;

@FindBy(css="input[name=your-email]")
private WebElement yourEmail;


@FindBy(css="input[name=your-subject]")
private WebElement yourSubject;


@FindBy(css="textarea[name=your-message]")
private WebElement yourMessage;



@FindBy(xpath="//input[@class='wpcf7-form-control wpcf7-submit']")
private WebElement sendBtn; 



@FindBy(css="div.wpcf7-response-output.wpcf7-display-none.wpcf7-mail-sent-ng")
private WebElement enqsendMsg;



public void clickonPlots() {
	 Actions action = new Actions(driver);
	 
       action.moveToElement(this.plotsLinks).perform();

}
public void selectProperty() {
	 Actions action = new Actions(driver);
	 action.moveToElement(this.donecquisProp);
	 
       action.click().perform();
	
	 
}

public void sendEnquiry(String yourName,String yourEmail,String yourSubject,String yourMessage  ) {
	this.yourName.sendKeys(yourName);
	this.yourEmail.sendKeys(yourEmail);
	this.yourSubject.sendKeys(yourSubject);
	this.yourMessage.sendKeys(yourMessage);
}

public void clickSendBtn() {
	driver.switchTo().defaultContent();
	JavascriptExecutor js = (JavascriptExecutor) driver;	
	js.executeScript("window.scrollBy(0, 250)", "");
	this.sendBtn.click(); 
}

public String enquiryMsg() {
	 String msg=this.enqsendMsg.getText();
	 return msg;
}


public boolean ispresent() {
	boolean msg=this.enqsendMsg.isDisplayed();
return msg;
}

}

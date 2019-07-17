package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.generics.ScreenShot;

public class RETC_032_POM {
	
	
	WebDriver driver;
	WebDriverWait wait;
	public ScreenShot screenShot=new ScreenShot(driver);
	 
	 
	 public RETC_032_POM(WebDriver driver) {
			this.driver = driver; 
		
			PageFactory.initElements(driver, this);
		}
	 
	 @FindBy(css="li#menu-item-354")
	 private WebElement newLaunchLink; 
	 
	 @FindBy(xpath="//a[text()='Nullam hendrerit Apartments']")
	 private WebElement xaptlink;
	 
	 @FindBy(css="button.slick-next.slick-arrow")
	 private WebElement arrowBtn;
	 
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
	 
	 @FindBy(id="amount")
	 private WebElement salePrice;
	 
	 @FindBy(id="downpayment")
	 private WebElement downPayment;
	 
	 @FindBy(id="years")
	 private WebElement years;
	 
	 @FindBy(id="interest")
	 private WebElement interest;
		
	 @FindBy(css="button.button.calc-button")
	 private WebElement calculateBtn;
	 
	 @FindBy(css="div.notification.success")
	 private WebElement clacSuccessMsg;
	 
	 //Monthly Payment: 
	 
	 
	 public void clickNewLaunch() {
		 Actions action = new Actions(driver);
		 
	        action.moveToElement(this.newLaunchLink).perform();
	 
	       
	 
	       
		 
	 }
	 
	 public void selectProperty() {
		 Actions action = new Actions(driver);
		 action.moveToElement(this.xaptlink);
		 
	        action.click().perform();
		 
		 this.arrowBtn.click();
		 
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
	 
	 public void MortageClac(String salePrice,String downPayment,String years,String interest) {
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0, 250)", "");
		 this.salePrice.sendKeys(salePrice);
		 this.downPayment.sendKeys(downPayment);
		 this.years.sendKeys(years);
		 this.interest.sendKeys(interest);
		 
	 }

	 public void clickCalc() {
		 this.calculateBtn.click();
	 }
	 
	 public String  mortageCalcMsg() {
		
		 String msg= this.clacSuccessMsg.getText();
		
		 return msg;
	 }
}



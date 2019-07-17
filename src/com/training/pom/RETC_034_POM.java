package com.training.pom;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.trianing.waits.WaitTypes;

public class RETC_034_POM {

	static WebDriver driver;
	WebDriverWait wait;
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 
	public RETC_034_POM(WebDriver driver) {
		this.driver = driver; 
	
		PageFactory.initElements(driver, this);
	}
	
@FindBy(xpath="//a[text()='Commercial ']")
private WebElement commercialLink;


@FindBy(id="keyword_search")
private WebElement entaddress;

@FindBy(xpath="//button[@class='button fullwidth']")
private WebElement searchBtn;

@FindBy(xpath="//input[@class='orig']")
private WebElement searchbox;

@FindBy(css="a.button.fullwidth.margin-top-20")
private WebElement dropusLink;

@FindBy(xpath="//input[@name='name']")
private WebElement yourName;

@FindBy(xpath="//input[@name='email']")
private WebElement emailId;

@FindBy(xpath="//input[@name='subject']")
private WebElement subject;

@FindBy(xpath="//textarea[@name='id:comments']")
private WebElement message;

@FindBy(xpath="//input[@class='wpcf7-form-control wpcf7-submit']")
private WebElement sendBtn;

@FindBy(xpath="//div[@class='wpcf7-response-output wpcf7-display-none wpcf7-mail-sent-ng']")
private WebElement postsubmitcontactFormMsg;

@FindBy(xpath="//div[@class='asl_content']//h3//a[contains(text(),'Nullam hendrerit')]")
private WebElement propName;

	/*
	 * @FindBy(
	 * xpath="//h3[@class='desc-headline' and text()='Nullam hendrerit Apartments - Overview ']"
	 * ) private WebElement propNameonPropPage;
	 */

	public void commercialClick() {
		this.commercialLink.click();
	}
	
	public void enablesearch(String entaddress) {
		this.entaddress.sendKeys(entaddress);
		this.searchBtn.click();
		
	}
	
	public void searchProp(String searchbox) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0, 250)", "");
		
		 
		 
		 
		 this.searchbox.sendKeys(searchbox);
		Thread.sleep(2000);
		 
		 //driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		 Actions action=new Actions(driver);
		 action.moveToElement(this.propName).click().build().perform();
		 
		Thread.sleep(5000);
		 
		 //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		/*
		 * driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 * this.searchbox.sendKeys(Keys.ARROW_DOWN);
		 * driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 * this.searchbox.sendKeys(Keys.ENTER);
		 */
		
	}
	
	public void childwindowSwitch() throws InterruptedException
	{       
		
		
		Set<String> widnowIDs = driver.getWindowHandles(); 
		Iterator<String> itr = widnowIDs.iterator();  
		String childWindow = itr.next(); 
		driver.switchTo().window(childWindow);
		
	
	}

	public void parentwindowSwitch() throws InterruptedException
	{           
		Set<String> widnowIDs = driver.getWindowHandles(); 
		Iterator<String> itr = widnowIDs.iterator();  
		String mainWindow = itr.next();  
		driver.switchTo().window(mainWindow);	
	}

	
	
	
	
	public void dropUsLink() {
		this.dropusLink.click();
	}
	
	public void contactForm(String yourName,String emailId,String subject,String message ) {
		
		this.yourName.sendKeys(yourName);
		this.emailId.sendKeys(emailId);
		this.subject.sendKeys(subject);
		this.message.sendKeys(message);
		
	}
	
	public void sendBtnClick() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("window.scrollBy(0, 250)", "");
		this.sendBtn.click();
	}

	
	public String postSendContactForm() {
		String msg=this.postsubmitcontactFormMsg.getText();
		return msg;
	}
	
	
	public String postnavigateNullam() {
	
	 String msg1=this.driver.getCurrentUrl();
	 return msg1;
	}
}
	


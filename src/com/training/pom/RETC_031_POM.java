package com.training.pom;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.generics.ScreenShot;

public class RETC_031_POM {

	WebDriver driver;
	WebDriverWait wait;
	
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 
	public RETC_031_POM(WebDriver driver) {
		this.driver = driver; 
	
		PageFactory.initElements(driver, this);
	}
	

@FindBy(xpath="//div[text()='Properties']")
private WebElement propertiesLink; 

@FindBy(css="a.page-title-action")
private WebElement addNewLink; 

@FindBy(css="input#title")
private WebElement titleProperty;

@FindBy(css="textarea#content")
private WebElement textbox;

//Scroll till this element

@FindBy(xpath="//label[text()='Property Type']")
private WebElement propertytype;


@FindBy(css="textarea#_price")
private WebElement pricetextbox;

@FindBy(css="input#_price_per")
private WebElement priceperSq;

@FindBy(css="a#ui-id-2")
private WebElement maindetailsTab ;

@FindBy(css="input#_status")
private WebElement status ;


@FindBy(css="input#_location")
private WebElement location ;

@FindBy(css="input#_possession")
private WebElement possession ;


@FindBy(css="a#ui-id-3")
private WebElement locationTab ;


@FindBy(css="input#_friendly_address")
private WebElement address;


@FindBy(css="input#_address")
private WebElement autooptnglemap;

@FindBy(css="input#_address")
private WebElement texttoSelect;

@FindBy(css="input#_geolocation_lat")
private WebElement latitude;


@FindBy(css="input#_geolocation_long")
private WebElement longitude;

@FindBy(css="a#ui-id-4")
private WebElement detailsTab;


@FindBy(css="input#_storage_room")
private WebElement storage_room;


@FindBy(css="input#in-region-57")
private WebElement centralBngChkbox;


@FindBy(css="input#publish")
private WebElement publishBtn;

@FindBy(xpath="//*[contains(text(),'Post published.')]")
private WebElement publishMsg;


@FindBy(xpath="//a[text()='View post']")
private WebElement viewpostLink;




public void propertyLink() {
	this.propertiesLink.click(); 
	
}

public void clickaddNew() {
	 ScreenShot screenShot1=new ScreenShot(driver);
	screenShot1.captureScreenShot("screen1");
	 this.addNewLink.click();
}



public void addTitleInfo(String titleProperty, String textbox ) {
	this.titleProperty.sendKeys(titleProperty); 
	this.textbox.sendKeys(textbox);
	//screenShot.captureScreenShot("AddNewProperty2");
	
}


public void addPriceInfo(String pricetextbox,String priceperSq) {
	this.pricetextbox.sendKeys(pricetextbox);
	this.priceperSq.sendKeys(priceperSq);
	//screenShot.captureScreenShot("AddNewProperty3");
}

public void addMainDetails(String status,String location,String possession) {
this.maindetailsTab.click();
this.status.sendKeys(status); 
this.location.sendKeys(location);
this.possession.sendKeys(possession);

}

public void addLocation(String address,String textToSelect,String latitude,String longitude) throws InterruptedException  {
	this.locationTab.click();
	this.address.sendKeys(address);
	this.selectOptionWithText(textToSelect);
	this.latitude.sendKeys(latitude); 
	this.longitude.sendKeys(longitude);	
}

public void addDetails(String storage_room ) {
	this.detailsTab.click();
	this.storage_room.sendKeys(storage_room);
	
		
}

public void clickCentralBngChkbox() {
	this.centralBngChkbox.click(); 
	//screenShot.captureScreenShot("AddNewProperty7");
}
	

public void clickPublishBtn() {
	Actions actions = new Actions(driver);
	actions.moveToElement(this.publishBtn).click().build().perform();
	//screenShot.captureScreenShot("PostPublishMessage");
		
}





public void selectOptionWithText(String textToSelect) throws InterruptedException {
	
	this.texttoSelect.sendKeys(textToSelect);
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
   this.texttoSelect.sendKeys(Keys.ARROW_DOWN);
   this.texttoSelect.sendKeys(Keys.ARROW_DOWN);
   this.texttoSelect.sendKeys(Keys.ENTER);
		
}

public String viewPostMsg() {
	String msg=this.publishMsg.getText();
	return msg;
}

public boolean viewPostlinkPresent() {
	boolean b=this.viewpostLink.isDisplayed();
	return b;
}








	
}

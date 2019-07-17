package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.training.generics.ScreenShot;

public class RETC_033_POM {
	
	WebDriver driver;
	WebDriverWait wait;
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 
	public RETC_033_POM(WebDriver driver) {
		this.driver = driver; 
	
		PageFactory.initElements(driver, this);
	}
	



@FindBy(xpath="//a[text()='All Properties']")
private WebElement allproperties;

@FindBy(xpath="//a[text()='3New']")
private WebElement propLaunched;


public void gotoAllProp() {
	Actions actions = new Actions(driver);
	actions.moveToElement(this.allproperties).click().build().perform();
	
}
	

public boolean findprop() {
	boolean actual=this.propLaunched.isDisplayed();
	return actual;
}
}

package com.training.pom;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RETC_035_POM {

	static WebDriver driver;
	
	WebDriverWait wait;
	
	JavascriptExecutor js = (JavascriptExecutor) driver;
	 
	 
	public RETC_035_POM(WebDriver driver) {
		this.driver = driver; 
	
		PageFactory.initElements(driver, this);
	}
	
	
	
@FindBy(xpath="//div[@class='wp-menu-name' and text()='Users']")
private WebElement usersLink;

@FindBy(xpath="//a[@class='page-title-action']")
private WebElement addNewLink;

@FindBy(id="user_login")
private WebElement userlogin;

@FindBy(id="email")
private WebElement email;

@FindBy(id="first_name")
private WebElement firstname;


@FindBy(id="last_name")
private WebElement lastname;


@FindBy(id="url")
private WebElement url;

@FindBy(xpath="//button[text()='Show password']")
private WebElement showpwdBtn;


@FindBy(id="pass1-text")
private WebElement pwdtext;

@FindBy(xpath="//input[@class='pw-checkbox']")
private WebElement confirmchkbox;

@FindBy(id="role")
private WebElement role;


@FindBy(id="createusersub")
private WebElement addUser;

@FindBy(xpath="//p[text()='New user created. ']")
private WebElement usercreateMsg;

@FindBy(xpath="//a[text()='suma']")
private WebElement user;

@FindBy(xpath="//a[@class='wp-first-item current']")
private WebElement alluserslink;

@FindBy(xpath="//li[@class='agent']")
private WebElement agentink;

public void clickUserLink() {
	this.usersLink.click();
}

public void addUserLink() {
	this.addNewLink.click();
}


public void fillDetails(String userlogin,String email,String firstname,String lastname,String url) {
	this.userlogin.sendKeys(userlogin);
	this.email.sendKeys(email);
	this.firstname.sendKeys(firstname);
	this.lastname.sendKeys(lastname);
	this.url.sendKeys(url);
}

public void clickPwd() {
	this.showpwdBtn.click();
	
}

public void setPwd(String pwdtext) {
	this.pwdtext.clear();
	this.pwdtext.sendKeys(pwdtext);
}

public void confirmchkbox() {
	this.confirmchkbox.click();
}

public void selectRole() {
	
	Select dropdown= new Select(this.role); 
	dropdown.selectByVisibleText("Agent");
	
}

public void addUser() {
	this.addUser.click();
}

public String addUserMsg() {
	String msg=this.usercreateMsg.getText();
	return msg;
	
}

public void alluserAgentClick() {
	this.alluserslink.click();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	this.agentink.click();
	 JavascriptExecutor js = (JavascriptExecutor) driver;
	 js.executeScript("window.scrollBy(0,500)");
}



public boolean userpresent() {
	boolean msg=this.user.isDisplayed();
	return msg;
}

}


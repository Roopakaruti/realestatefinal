package com.training.pom;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RETC_065_POM {


WebDriver driver;
	
	public RETC_065_POM(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	
	}
	
	
	
	
@FindBy(id="menu-posts")	
private WebElement postlink;
	
@FindBy(xpath="(//ul/li/a[text()='Categories'])[1]")
private WebElement categories;

@FindBy(id="tag-name")
private WebElement name;

@FindBy(id="tag-slug")
private WebElement slug;

@FindBy(id="tag-description")
private WebElement description;

@FindBy(id="submit")
private WebElement addNewCatgry;

@FindBy(xpath="//li/a[@class='wp-first-item' and text()='All Posts']")
private WebElement allpostlink;

@FindBy(xpath="//a[@class='page-title-action']")
private WebElement addNewPost;

@FindBy(name="post_title")
private WebElement title;

@FindBy(id="content")
private WebElement content;

@FindBy(xpath="(//ul//li/label[@class='selectit']/input[@name='post_category[]'])[4]")
private WebElement selectCatg;


@FindBy(id="publish")
private WebElement publishBtn;

@FindBy(xpath="//*[contains(text(),'Post published.')]")
private WebElement publishMsg;

@FindBy(id="wp-admin-bar-my-account")
private WebElement admin;

@FindBy(xpath="//li/a[text()='Log Out']")
private WebElement logout;

@FindBy(xpath="//ul/li[@id='menu-item-617']")
private WebElement blogLink;

@FindBy(xpath="(//div/h3/a[text()='New Launch'])[1]")
private WebElement latestblogpostlink;


@FindBy(xpath="//a[text()='View post']")
private WebElement viewpostLink;


public void clickPost() {
	this.postlink.click();
}

public void clickcategory() {
	this.categories.click();
}

public void addCategory(String name,String slug,String description) {
	this.name.sendKeys(name);
	this.slug.sendKeys(slug);
	this.description.sendKeys(description);
}




public void ClickAddNewCatgry() {
	this.addNewCatgry.click();
}

public void clickAllPost() {
	this.allpostlink.click();
}

public void clickAddNew() {
	this.addNewPost.click();
}
public void fillPostDetails(String title,String content) {
	this.title.sendKeys(title);
	this.content.sendKeys(content);
}

public void selectCategry() {
	this.selectCatg.click();
}


public void clickPublish() throws InterruptedException {
	
	driver.switchTo().defaultContent();
	Thread.sleep(2000);
	
	//driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	Actions actions = new Actions(driver);
	actions.moveToElement(this.publishBtn).click().build().perform();
}

public String postPublishMsg() {
	String msg=this.publishMsg.getText();
	return msg;
}

public void clickLogout() {
	 Actions action = new Actions(driver);
	 
     action.moveToElement(this.admin).build().perform();
     
     this.logout.click();
}

public void clickBlog() throws InterruptedException {
//	driver.switchTo().defaultContent();
//	Thread.sleep(2000);
 this.blogLink.click();
}

public boolean verifynewBlogpost() {
	boolean btrue= this.latestblogpostlink.isDisplayed();
	return btrue;
}

public boolean viewPostlinkPresent() {
	boolean b=this.viewpostLink.isDisplayed();
	return b;
}


}


package com.training.pom;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ChangePwdTest_POM {
	
private WebDriver driver; 
private WebElement element;
	

	public ChangePwdTest_POM(WebDriver driver) {
		this.driver = driver; 
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="input[name='current_pass']")
	private WebElement curr_pwd;
	
	
	@FindBy(css="input[name='pass1']")
	private WebElement new_pwd1;
	
	
	@FindBy(css="input[name='pass2']")
	private WebElement new_pwdrepeat;
	

	@FindBy(css="input#wp-submit")
	private WebElement savechangesBtn;
	
	@FindBy(css="div[class*='success']")
	private WebElement message;
	
	

	
	
	/*
	 * public void scroll(WebDriver driver,WebElement element ) throws
	 * InterruptedException {
	 * 
	 * JavascriptExecutor js = (JavascriptExecutor) driver;
	 * js.executeScript("arguments[0].scrollIntoView(true);", element);
	 * Thread.sleep(500); }
	 */
	 
	 
	
	
	public void changepwd(String curr_pwd,String new_pwd1,String new_pwdrepeat) throws InterruptedException {
		this.curr_pwd.sendKeys(curr_pwd);
		this.new_pwd1.sendKeys(new_pwd1);
		this.new_pwdrepeat.sendKeys(new_pwdrepeat);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", this.savechangesBtn);
		 
		
		Thread.sleep(500);
		
		
		this.savechangesBtn.click();
		}
	
	

	public String successMsg() {
		
		String actualMsg=this.message.getText();
		return actualMsg;
		}
	
	
	
	
}





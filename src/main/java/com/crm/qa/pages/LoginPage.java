package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;


public class LoginPage extends TestBase {
	
	//Page Factory - OR
	@FindBy(xpath = "//input[@name='username']")
	WebElement username;
	
	@FindBy(xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy(xpath = "//input[@type='submit']")  // customise xptah using chrome 
	WebElement loginbutton;
	
	@FindBy(xpath = "//button[contains(text(),'Sign Up')]")
	WebElement signUpBtn;
	
	@FindBy(xpath = "//img[contains(@class,'img-responsive')]")
	WebElement crmLogo;
	
	// Initilise page objects
	// Create constructor
	public LoginPage() {
		PageFactory.initElements(driver, this); // initilise page factory objects 
	}
	
	//Actions
	public String validateLoginPageTitle() {
		return driver.getTitle();
	}
	
	public boolean validateCRMImage() {
		return crmLogo.isDisplayed();
	}
	
	public HomePage login(String uname, String pwd) throws InterruptedException {
				
		if(username.isDisplayed()) {
			username.sendKeys(uname);
		}
		if(password.isDisplayed()) {
			password.sendKeys(pwd);
		}
		if(loginbutton.isEnabled()) {
			loginbutton.click();
		}
		
		return new HomePage();
	}

}

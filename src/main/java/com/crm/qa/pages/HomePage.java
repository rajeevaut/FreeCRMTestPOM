package com.crm.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class HomePage extends TestBase {
	
	@FindBy(xpath="//td[contains(text(),'User: rajeev gupta')]")
	WebElement userNemeLabel;
	
	@FindBy(xpath="//a[contains(text(),'Contacts')]")
	WebElement contactLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//a[contains(text(),'Deals')]")
	WebElement dealsLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	// Initilise page objects
		// Create constructor
	public HomePage() {
			PageFactory.initElements(driver, this); // initilise page factory objects 
	}
	
	//ACTIONS
	
	public String verifyHomePageTile() {
		 return driver.getTitle();
		
	}
	
	public boolean verifyCorrectUserName() {
		return userNemeLabel.isDisplayed();
	}
	public ContactsPage clickOnContactsLink() {
		
		contactLink.click();
		// this method will return new contact page object	
		return new ContactsPage();
	}
	
	public DealsPage clickOnDealsPage() {
		dealsLink.click();
		return new DealsPage();
	}
	
	public TaskPage clickOnTaskPage() {
		tasksLink.click();
		return new TaskPage();
	}
	
	public void clickOnNewContactLink() {
		Actions action = new Actions(driver);
		action.moveToElement(contactLink).build().perform();
		newContactLink.click();
	}
	
	
		
}

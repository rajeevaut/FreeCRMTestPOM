package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class ContactsPageTest extends TestBase{
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	String sheetname = "Contacts";
	//constructor
	public ContactsPageTest() {
		super();
	}
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		// first login to reach in HomePage
		initilization(); 
		testUtil = new TestUtil();
		contactsPage = new ContactsPage();
		loginPage = new LoginPage();  // constructor of login page class
		homePage = loginPage.login(prop.getProperty("username"),prop.getProperty("password") );
		
		// Click on contacts link to reach in contact page and 
		testUtil.swithToFrame();
		contactsPage = homePage.clickOnContactsLink();
		
	}
	
	@Test(priority=1)
	public void verifyContactsPageLabelTest() {
		Assert.assertTrue(contactsPage.verifyContactsLabel(),"Contacts label is missing in the page");
	}
	
	@Test(priority=2)
	public void selectContactsTest() {
		contactsPage.selectContactsByName("Manish Gupta");
		
	}
	@Test(priority=3)
	public void selectMultipleContactsTest() {
		contactsPage.selectContactsByName("Manish Gupta");
		contactsPage.selectContactsByName("Rakesh Gupta");
	}
	
	@DataProvider
	public Object[][] getCRMTestData() {
		Object data[][]=TestUtil.getTestData(sheetname);
		return data;
	}
	
	/*@Test(priority=4 , dataProvider="getCRMTestData")
	public void validateCreateNewContact(String title,String fname,String lname, String compname) {
		homePage.clickOnNewContactLink();
		//contactsPage.createNewContact("Mr.", "tom", "peter", "google");
		contactsPage.createNewContact(title, fname, lname, compname);
	}*/
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}

package com.crm.qa.testcases;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {

	// Note: Test cases should be independent
	//before each test case launch browser and login , after each test case close the browser
	
	LoginPage loginPage;
	HomePage homePage;
	TestUtil testUtil;
	ContactsPage contactsPage;
	//constructor 
	public HomePageTest() {
		// call superclass constructor by super keyword 
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
		
	}
		
	@Test(priority=1)
	public void verifyHomePageTitleTest() {
		//Note-always write Test at last with method name to indicate this method belongs to test
		String homePageTitle= homePage.verifyHomePageTile();
		Assert.assertEquals(homePageTitle, "CRMPRO","Home Page Title Not Matched");
	}
	
	@Test(priority=2)
	public void verfiyUserNameTest() {
		testUtil.swithToFrame();
		Assert.assertTrue(homePage.verifyCorrectUserName());
	}
	
	@Test(priority=3)
	public void verifyContactsLinkTest() {
		testUtil.swithToFrame();
		contactsPage = homePage.clickOnContactsLink();
	}
	
	@AfterMethod
	public void teardown() {
		driver.quit();
	}
}

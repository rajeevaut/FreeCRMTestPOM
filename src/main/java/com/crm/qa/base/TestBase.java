package com.crm.qa.base;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;



public class TestBase {
	public static WebDriver driver;
	public static Properties prop;
	public  static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;
	//**** Log4J Logger 
	public static Logger log = Logger.getLogger(TestBase.class);
	//constructor
	public TestBase() {
		try {
			prop = new Properties();
			
			FileInputStream ip = new FileInputStream("E:\\Selenium\\FreeCRMTest\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(ip);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void initilization() {
		
		
		String browserName = prop.getProperty("browsername");
		String headlesstesting = prop.getProperty("headless");
		log.info("***"+headlesstesting);
		
		log.info("****************************** Starting Test Case execution  *****************************************");
		if(headlesstesting.equals("Yes")) {
			/*Important Features:
			-phantomJSdriver internally uses ghost driver
			-ghostdriver is used as JSON wire protocol -- HTTP REST calls
			-headless browser testing:
			-no browser will be launched
			-testing is happening behind the scene
			-its very fast
			-it directly interacts with your app HTML DOM
			*/
			System.setProperty("phantomjs.binary.path", "E:\\Selenium\\drivers\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
			driver = new PhantomJSDriver();
		}else {
			
			if(browserName.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "E:\\Selenium\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
				
			}else if(browserName.equals("FF")) {
				System.setProperty("webdriver.gecko.driver", "E:\\Selenium\\geckodriver\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
		}

		/// Implement Web driver fire event - Weblistener 
		e_driver = new EventFiringWebDriver(driver);
		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		
		
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		
		driver.get(prop.getProperty("url"));
		
		
		
	}
}

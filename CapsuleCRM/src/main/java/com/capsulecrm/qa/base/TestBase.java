package com.capsulecrm.qa.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;


import com.capsulecrm.qa.listeners.WebEventListener;
import com.capsulecrm.qa.util.ElementsUtil;
import com.capsulecrm.qa.util.TestUtil;



//Base class Defintion
public class TestBase {

	public static Logger TEST_LOG = LogManager.getLogger(TestBase.class);


	public static WebDriver driver;
	public Properties prop;
	String propertyPath ="./src/main/java/com/capsulecrm/qa/config/config.properties";
	String appURL;
	String browserType;
	public static EventFiringWebDriver e_driver;
	public static WebEventListener eventListener;

	// Constructor Defintion
	public TestBase() {
		// Config.properties file reading
		try {
			FileInputStream fis = new FileInputStream(propertyPath);
			prop = new Properties();
			prop.load(fis);
			fis.close();

		} catch (IOException e) {

			System.out.println("Couldn't load Property file");
		}

	}


	// Launching browser and navigating to url
	public void initialization() {

		appURL = prop.getProperty("url");
		browserType = prop.getProperty("browser");
		browserType = browserType.toLowerCase();

		if (browserType.equalsIgnoreCase("chrome")) 
			driver = initChromeBrowser();
		else if(browserType.equalsIgnoreCase("ff"))
			driver = initFirefoxBrowser();
		else if(browserType.equalsIgnoreCase("edge"))
			driver = initEdgeBrowser();
		else{
			System.out.println("Browser value entered is invalid, Launching chrome  ");
			driver = initChromeBrowser();
		}


		e_driver = new EventFiringWebDriver(driver);
		eventListener = new WebEventListener();
		e_driver.register(eventListener);
		driver = e_driver;
		System.out.println("Navigate to url");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(ElementsUtil.PAGE_LOAD_TIME, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(ElementsUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(appURL);

	}

	private WebDriver initChromeBrowser(){
		System.out.println("Launching Chrome Browser ");
		System.setProperty("webdriver.chrome.driver", TestUtil.getDriverPath(browserType));
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("-incognito");
		options.addArguments("chrome.switches","--disable-extensions");
		driver = new ChromeDriver(options);
		return driver;
	}

	private WebDriver initFirefoxBrowser(){
		System.out.println("Launching Firefox browser ");
		System.setProperty("webdriver.gecko.driver", TestUtil.getDriverPath(browserType));
		driver = new FirefoxDriver();
		return driver;
	}


	private WebDriver initEdgeBrowser(){
		System.out.println("Launching edge browser ");

		System.setProperty("webdriver.ie.driver" , TestUtil.getDriverPath(browserType));
		driver = new EdgeDriver();
		return driver;
	}

}

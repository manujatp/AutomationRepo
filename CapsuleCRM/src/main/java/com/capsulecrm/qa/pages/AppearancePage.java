package com.capsulecrm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;
import com.capsulecrm.qa.util.TestUtil;

public class AppearancePage extends TestBase {

	WebDriver driver;

	@FindBy(xpath ="//h1[contains(@class,'settings-page-header')]")
	private WebElement _h1LblElement;

	@FindBy(id= "appearance:uploadDecorate:logoImage")
	private WebElement _chooseFileLinkElement;


	@FindBy(xpath= "//span[@class='input ']//img")
	private WebElement _currentLogoElement;

	@FindBy(xpath= "//a[contains(@class,'btn-primary singlesubmit')]")
	private WebElement _saveBtnElement;

	//Constructor
	public AppearancePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//	// Getting header of Appearance Page
	public String getAppearanceHeader() {
		return _h1LblElement.getText();
	}
	
	//Adding Logo by sendKeys and exe by AutoIT
	public void addLogo(String fileName) {

		ElementsUtil.setLogoByExplicitWait(_chooseFileLinkElement, driver, fileName);
		//Adding Logo By exe file
		/*	if(TestUtil.OS_NAME.toUpperCase().contains("WINDOWS"))
		TestUtil.setLogoByExe(_chooseFileLinkElement, driver, System.getProperty("user.dir")+"/src/main/resources/data/FileUploadScriptW.exe");
		else if(TestUtil.OS_NAME.toUpperCase().contains("MAC"))
			TestUtil.setLogoByExe(_chooseFileLinkElement, driver, System.getProperty("user.dir")+"/src/main/resources/data/FileUploadScriptM.exe");
		 */
		ElementsUtil.clickByExplicitWait(_saveBtnElement, driver);
		TestUtil.staticWait();
	}

	public String getCurrentLogo() {
		return _currentLogoElement.getAttribute("src");
	}

	public AccountSettingPage backToAccountSetting() {
		return new AccountSettingPage(driver);
	}

}

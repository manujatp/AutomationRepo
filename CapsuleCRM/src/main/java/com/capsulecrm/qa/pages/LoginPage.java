package com.capsulecrm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;



public class LoginPage extends TestBase {

	WebDriver driver;

	@FindBy(id ="login:usernameDecorate:username")
	private WebElement _usernameTxtElemnt;

	@FindBy(id ="login:passwordDecorate:password")
	private WebElement _passwordTxtElemnt;

	@FindBy(id ="login:login")
	private WebElement _loginBtnElemnt;

	//Constructor
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);	

	}

	// Getting the title of Login Page
	public String getTitle() {
		return driver.getTitle();
	}

	// Login to CapsuleCrm
	public HomePage Login(String username, String password) {
		ElementsUtil.setByExplicitWait(_usernameTxtElemnt, driver, username);
		ElementsUtil.setByExplicitWait(_passwordTxtElemnt, driver, password);
		ElementsUtil.clickByExplicitWait(_loginBtnElemnt, driver);
		TEST_LOG.info("***********Login to CapsuleCRM*************");
		return new HomePage(driver);
	}
}

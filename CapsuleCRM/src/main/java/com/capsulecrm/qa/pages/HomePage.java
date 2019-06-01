package com.capsulecrm.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;

public class HomePage extends TestBase {

	WebDriver driver;

	@FindBy(xpath ="//a[contains(@class,'nav-bar-item nav-bar-item-parties ember-view')]")
	private WebElement _peopleLinkElement;


	@FindBy(xpath ="//a[contains(@class,'nav-bar-item nav-bar-item-cases ember-view')]")
	private WebElement _casesLinkElement;


	@FindBy(xpath ="//span[contains(@class,'nav-bar-username')]")
	private WebElement _userNameLinkElement;

	@FindBy(xpath ="//a[text()='Account Settings']")
	private WebElement _accountSettingLinkElement;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Getting the title of Home Page
	public String getTitle() {
		return driver.getTitle();
	}


	public PeoplenOrgPage clickPeopleLink() {
		ElementsUtil.clickByExplicitWait(_peopleLinkElement, driver);
		return new PeoplenOrgPage(driver);
	}

	public CasesPage clickCasesLink() {
		ElementsUtil.clickByExplicitWait(_casesLinkElement, driver);
		return new CasesPage(driver);

	}

	public AccountSettingPage clickAccountSettingLink() {
		ElementsUtil.clickByExplicitWait(_userNameLinkElement, driver);
		ElementsUtil.performClick(_accountSettingLinkElement, driver);
		return new AccountSettingPage(driver);


	}


}

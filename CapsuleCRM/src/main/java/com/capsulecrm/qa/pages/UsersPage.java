package com.capsulecrm.qa.pages;

import java.util.List;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;


public class UsersPage extends TestBase {

	WebDriver driver;

	@FindBy(xpath ="//h2[contains(@class,'settings-page-header')]")
	private WebElement _h2LblElement;

	@FindBy(xpath = "//a[contains(@class,'action btn-primary btn-primary-clear')]")
	private WebElement _addNewUserBtnElement;

	@FindBy(id = "register:firstnameDecorate:firstName")
	private WebElement _firstNameUserTxtElement;

	@FindBy(id = "register:lastNameDecorate:lastName")
	private WebElement _lastNameUserTxtElement;

	@FindBy(id = "register:emailDecorate:email")
	private WebElement _emailUserTxtElement;

	@FindBy(id = "register:usernameDecorate:username")
	private WebElement _userNameTxtElement;

	@FindBy(id = "register:save")
	private WebElement _inviteUserBtnElement;


	@FindBy(xpath = "//table[@id = 'j_id128:users']//tbody//tr//td[2]")
	private List<WebElement> _userNamesList;

	//Constructor
	public UsersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Getiing header
	public String getUserPageHeader() {
		return _h2LblElement.getText();
	}

	//Adding new User
	public void AddNewUser(String fname, String lname, String email, String uname) throws InterruptedException {

		if ((_userNamesList.size())<2){

			ElementsUtil.clickByExplicitWait(_addNewUserBtnElement, driver);
			ElementsUtil.setByExplicitWait(_firstNameUserTxtElement, driver, fname);
			ElementsUtil.setByExplicitWait(_lastNameUserTxtElement, driver, lname);
			ElementsUtil.setByExplicitWait(_emailUserTxtElement, driver, email);
			ElementsUtil.setByExplicitWait(_userNameTxtElement, driver, uname);
			ElementsUtil.clickByExplicitWait(_inviteUserBtnElement, driver);
			ElementsUtil.hardWait();
		}
		else
			System.out.println("Can't add new users, you already have 2 users which is the maximum on this plan");
	}


	//Validating added user
	public void UserValidation(String uname) {

		ElementsUtil.validateItemInList(_userNamesList, uname);

	}

	public AccountSettingPage backToAccountSetting() {
		return new AccountSettingPage(driver);
	}

}
package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;


public class PeoplenOrgPage extends TestBase {
	WebDriver driver;

	@FindBy(xpath ="//a[contains(@class,'nav-bar-item nav-bar-item-dashboard ember-view')]")
	private WebElement _homeLinkElement;


	@FindBy(xpath ="//a[text()='Add Person']")
	private WebElement _addPersonBtnElement;


	@FindBy(id ="party:fnDecorate:fn")
	private WebElement _firstNameTxtElement;

	@FindBy(id ="party:lnDecorate:ln")
	private WebElement _lastNameTxtElement;

	@FindBy(id ="party:roleDecorate:jobTitle")
	private WebElement _jobTitleTxtElement;

	@FindBy(id ="party:orgDecorate:org")
	private WebElement _OrganisationTxtElement;

	@FindBy(id ="party:tagsDecorate:tagComboBox")
	private WebElement _tagCombBoxElement;

	@FindBy(id ="party:tagsDecorate:j_id187")
	private WebElement _addTagsBtnElement;

	@FindBy(id ="party:j_id325:0:phnDecorate:number")
	private WebElement _phnoTxtElement;

	@FindBy(id ="party:j_id342:0:emlDecorate:nmbr")
	private WebElement _emailTxtElement;

	@FindBy(id ="party:save")
	private WebElement _saveBtnElement;

	@FindBy(xpath ="//span[contains(@class,'party-details-title')]")
	private WebElement _personNameTitleElement;

	@FindBy(xpath ="//table[@class='simple-table list-results-table with-hover-effect']//tbody//tr//td[3]//a[1]")
	private List<WebElement> _personNameList;

	//Constructor
	public PeoplenOrgPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	//Adding new person
	public void AddPerson(String title,String firstname,String lastname,String jobtitle,String org,String tag,String phno,String email) {

		ElementsUtil.clickByExplicitWait(_addPersonBtnElement, driver);
		Select _titleDropDwn = new Select(driver.findElement(By.name("party:j_id108:j_id116")));
		_titleDropDwn.selectByVisibleText(title);


		ElementsUtil.setByExplicitWait(_firstNameTxtElement, driver, firstname);
		ElementsUtil.setByExplicitWait(_lastNameTxtElement, driver, lastname);
		ElementsUtil.setByExplicitWait(_jobTitleTxtElement, driver, jobtitle);
		ElementsUtil.setByExplicitWait(_OrganisationTxtElement, driver, org);
		ElementsUtil.setByExplicitWait(_tagCombBoxElement, driver, tag);
		ElementsUtil.clickByExplicitWait(_addTagsBtnElement, driver);
		ElementsUtil.setByExplicitWait(_phnoTxtElement, driver, phno);
		ElementsUtil.setByExplicitWait(_emailTxtElement, driver, email);
		ElementsUtil.setByExplicitWait(_firstNameTxtElement, driver, firstname);
		ElementsUtil.clickByExplicitWait(_saveBtnElement, driver);
	}

	//Validating added person
	public void validatePersonName(String personname) {

		ElementsUtil.validateItemInList(_personNameList, personname);
	}

	public HomePage navigateToHome() {

		ElementsUtil.clickByExplicitWait(_homeLinkElement, driver);
		return new HomePage(driver);
	}



}

package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;


public class TagsPage extends TestBase {

	WebDriver driver;


	@FindBy(xpath ="//h2[contains(@class,'settings-page-header')]")
	private WebElement _h2LblElement;


	@FindBy(xpath = "//a[text()='Add new Tag']")
	private WebElement _addNewCategoryBtnElement;

	@FindBy(id = "j_id177:tagNameDecorate:tagName")
	private WebElement _tagTxtElement;

	@FindBy(xpath = "//input[@id='j_id177:j_id210']")
	private WebElement _saveBtnElement;


	@FindBy(xpath = "//table[@id = 'j_id125:tags']//tbody//tr//td[1]//span[@style]//a")
	private List<WebElement> _tagList;

	//Constructor
	public TagsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Getting page header
	public String getTagPageHeader() {
		return _h2LblElement.getText();
	}


	//Adding new tag
	public void AddTag(String tagname) {
		ElementsUtil.clickByExplicitWait(_addNewCategoryBtnElement, driver);
		ElementsUtil.setByExplicitWait(_tagTxtElement, driver, tagname);
		ElementsUtil.clickByExplicitWait(_saveBtnElement, driver);
	}

	//Validating the added tag
	public void TagValidation(String tagname) {
		ElementsUtil.validateItemInList(_tagList, tagname);;
	}

	public AccountSettingPage backToAccountSetting() {
		return new AccountSettingPage(driver);
	}
}

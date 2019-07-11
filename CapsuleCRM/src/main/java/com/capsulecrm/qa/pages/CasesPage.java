package com.capsulecrm.qa.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.capsulecrm.qa.base.TestBase;
import com.capsulecrm.qa.util.ElementsUtil;

public class CasesPage {

	WebDriver driver;

	@FindBy(xpath ="//a[contains(@class,'nav-bar-item nav-bar-item-dashboard ember-view')]")
	private WebElement _homeLinkElement;

	@FindBy(xpath ="//a[text()='Add Case']")
	private WebElement _addCaseBtnElement;

	@FindBy(xpath ="//input[@class ='search-select-input ember-view']")
	private WebElement _searchPersonElement;
	
	@FindBy(xpath ="//span[@class='search-select-option-party']//span[@class='search-select-option-text']")
	private List<WebElement> _searchPersonListElement;
	//span[@class='search-select-option-party']//span[@class='search-select-option-text']

	@FindBy(xpath ="//input[@class='form-input-text']")
	private WebElement _caseNameTxtElement;

	@FindBy(xpath ="//div[@class='form-field  full-width']//textarea")
	private WebElement _descrCaseTxtElement;

	@FindBy(id ="tagsDecorate:tagComboBox")
	private WebElement _tagCaseTxtElement;

	@FindBy(id ="tagsDecorate:j_id191")
	private WebElement _addTagsCaseBtnElement;

	@FindBy(id ="save")
	private WebElement _saveCaseBtnElement;

	@FindBy(xpath ="//table[@class='simple-table list-results-table with-hover-effect']//tbody//tr[2]//td[2]//a[1]")
	private List<WebElement> _titleCaseListElement;

	@FindBy(xpath ="//table[@class='simple-table list-results-table with-hover-effect']//tbody//tr[2]//td[3]//span")
	private List<WebElement> _statusCaseListElement;


	public CasesPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Adding new case
	public void AddCase(String personname, String casename, String description, String tag) {

		ElementsUtil.clickByExplicitWait(_addCaseBtnElement, driver);
		ElementsUtil.performSet(_searchPersonElement,_searchPersonListElement, driver, personname);
		ElementsUtil.setByExplicitWait(_caseNameTxtElement, driver, casename);
		ElementsUtil.setByExplicitWait(_descrCaseTxtElement, driver, description);
		ElementsUtil.setByExplicitWait(_tagCaseTxtElement, driver, tag);
		ElementsUtil.clickByExplicitWait(_saveCaseBtnElement, driver);
	}

	//Validating the case
	public void ValidateCase(String personname, String casename) {
		boolean flag = false;
		for(int i=0; i<_titleCaseListElement.size(); ++i)
			if(_titleCaseListElement.get(i).getText().contains(personname) && _titleCaseListElement.get(i).getText().contains(casename) ) {
				_statusCaseListElement.get(i).getText().equalsIgnoreCase("Open");
				flag = true;
				break;
			}
		if(flag)
			TestBase.TEST_LOG.info("Case "+casename+ " is added for the person "+personname+ " and its status is Open");
		else
			TestBase.TEST_LOG.error("Case "+casename+ " is not added for the person "+personname);

	}


	public HomePage navigateToHome() {

		ElementsUtil.clickByExplicitWait(_homeLinkElement, driver);
		return new HomePage(driver);
	}



}
